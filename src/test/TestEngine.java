package test;

import engine.rule.Definition;
import engine.rule.Engine;
import static engine.rule.Rules.OWNER;
import static engine.rule.Rules.ROLE;
import engine.Authorization;
import junit.framework.TestCase;
import org.junit.Test;
import static test.CustomAction.EDIT;
import static test.CustomAction.SUPER_SECURE;
import static test.CustomRules.ADMIN;
import static test.CustomRules.OBJECT_LOCKED;
import static test.CustomRules.*;

public class TestEngine extends TestCase {


   @Test
   public void test2() {
      Authorization.setEngine(new Engine(
              new Definition() {{

                 forAction(EDIT, UserVO.class)
                         .deny(ALL);


                 forAction(EDIT, LearningObjectVO.class)
                         .deny(OBJECT_LOCKED)
                         .allow(ADMIN)
                         .allow(ROLE("contributor"))
                         .allow(OWNER);


                 forAction(SUPER_SECURE)
                         .deny(OBJECT_LOCKED)
                         .allow(ADMIN, OWNER);

              }}
      ));


      UserVO admin = new UserVO("admin", "admin");
      UserVO guest = new UserVO("guest", "guest");
      UserVO contributor = new UserVO("contributor", "contributor");
      LearningObjectVO guestLo = new LearningObjectVO(guest);
      LearningObjectVO guestLoLocked = new LearningObjectVO(guest);
      guestLoLocked.setLocked(true);
      LearningObjectVO adminLoLocked = new LearningObjectVO(admin);
      adminLoLocked.setLocked(true);
      LearningObjectVO adminLo = new LearningObjectVO(admin);

      assertTrue(Authorization.can(admin, EDIT, guestLo));
      assertTrue(Authorization.can(guest, EDIT, guestLo));
      assertTrue(Authorization.can(admin, EDIT, adminLo));
      assertFalse(Authorization.can(guest, EDIT, adminLo));
      assertTrue(Authorization.can(contributor, EDIT, adminLo));

      assertFalse(Authorization.can(admin, EDIT, guestLoLocked));

      assertFalse(Authorization.can(admin, SUPER_SECURE, guestLo));
      assertFalse(Authorization.can(guest, SUPER_SECURE, guestLo));
      assertTrue(Authorization.can(admin, SUPER_SECURE, adminLo));
      assertFalse(Authorization.can(guest, SUPER_SECURE, adminLo));


           
//      System.out.println(Authorization.why(admin, EDIT, guestLo));
//      System.out.println(Authorization.why(admin, EDIT, guestLoLocked));
//      System.out.println(Authorization.why(guest, EDIT, adminLoLocked));
//
//      System.out.println(Authorization.why(guest, SUPER_SECURE, adminLoLocked));
//
//      System.out.println("------------");
//      System.out.println(Authorization.why(admin, SUPER_SECURE, guestLo));
//      System.out.println(Authorization.why(guest, SUPER_SECURE, guestLo));
//      System.out.println(Authorization.getAvailableActions(guest, adminLo));
//      System.out.println(Authorization.getAvailableActions(guest, guestLo));
//      System.out.println(Authorization.getAvailableActions(admin, guestLo));
//      System.out.println(Authorization.getAvailableActions(admin, adminLo));


      assertTrue(Authorization.can(admin, EDIT, guestLo));


   }


}
