package test;

import engine.Action;
import engine.Engine;
import engine.Translator;
import engine.User;
import engine.rule.Definition;
import static engine.rule.Operator.NOT;
import engine.rule.Rule;
import static engine.rule.Rules.TRUE;
import junit.framework.TestCase;
import org.junit.Test;
import static test.CustomAction.*;
import static test.CustomRules.ADMIN;
import static test.CustomRules.OBJECT_SHOULD_BE_A_STRING;

import static java.util.Arrays.asList;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: Administrateur
 * Date: 17 déc. 2009
 * Time: 21:15:15
 * To change this template use File | Settings | File Templates.
 */
/*
* A very simple authorization system in java
*  no external dependency
* 
* Supports I18n via the Translator ( just need to implement the Translator interface )
*
* Usage :
*
* Define the rule for your engine

import static engine.rule.Operator.*;
import static test.CustomRules.*;
import static test.CustomAction.*;

   Engine myEngine = new Engine(
              Translator.DEFAULT,
              new Definition() {{
                 setRule(READ, TRUE);
                 setRule(EDIT, OBJECT_SHOULD_BE_A_STRING);
                 setRule(DELETE, ADMIN, OBJECT_SHOULD_BE_A_STRING);
                 setRule(IDENTICAL, new IdenticalRule());
              }}
      );
then you can do
myEngine.can(user, CustomAction.EDIT)) => return true or false;
myEngine.isAuthorized(user, CustomAction.EDIT)) => return true or throw an exception;
myEngine.why(user, CustomAction.EDIT)) => return the reason / reasons why the user could not do this action;
myEngine.getAvailableActions(user) => list;


*
*
*
* */
public class TestEngine extends TestCase {

   @Test
   public void test() {

      Engine myEngine = new Engine(
              Translator.DEFAULT,
              new Definition() {{
                 setRule(CustomAction.READ, CustomRules.TRUE);
                 setRule(CustomAction.EDIT, OBJECT_SHOULD_BE_A_STRING);
                 setRule(DELETE, ADMIN, OBJECT_SHOULD_BE_A_STRING);
                 setRule(CustomAction.IDENTICAL, new IdenticalRule());
                 setRule(CustomAction.UPDATE,
                         new Rule("Your username must contain \"super\"") {
                            public boolean can(User user, Object object) {
                               UserVO uservo = (UserVO) user;
                               return uservo.getName().contains("super");
                            }
                         },
                         OBJECT_SHOULD_BE_A_STRING);
                 setRule(NOT, CustomAction.GUEST, ADMIN);

              }}
      );
      final Action CANCEL = new Action(){};
      final Rule CANCELLABLE = new Rule(){
         public boolean can(User user, Object object) {
            return false;
         }
      };
      final Rule OBJECT_SHOULD_BE_A_INTEGER = new Rule(){
         public boolean can(User user, Object object) {
            return object instanceof Integer;
         }
      };
        Engine myEngine2 = new Engine(
             // Translator.DEFAULT,
              new Definition() {{
                 setRule(CANCEL, CANCELLABLE , OBJECT_SHOULD_BE_A_INTEGER);
                 setRule(READ, TRUE);
                 setRule(EDIT, OBJECT_SHOULD_BE_A_STRING);
                 setRule(DELETE, ADMIN, OBJECT_SHOULD_BE_A_STRING);
                 setRule(IDENTICAL, new IdenticalRule());
              }}
      );

//      Engine myEngine2 = new Engine(
//             // Translator.DEFAULT,
//              new Definition() {{
//                 setRule(READ, TRUE);
//                 setRule(EDIT, OBJECT_SHOULD_BE_A_STRING);
//                 setRule(DELETE, ADMIN, OBJECT_SHOULD_BE_A_STRING);
//                 setRule(IDENTICAL, new IdenticalRule());
//              }}
//      );

      UserVO admin = new UserVO("intrallect", "admin");
      UserVO guest = new UserVO("mathieu", "guest");
      UserVO french = new UserVO("theFrench", "admin", Locale.FRENCH);
      System.out.println("admin = " + myEngine.getActionsAccess(admin, "something"));
      System.out.println("french = " + myEngine.getActionsAccess(french, "something"));
      System.out.println("french = " + myEngine.getAvailableActions(french, "something"));
      assertTrue(myEngine.can(admin, CustomAction.READ));
      assertTrue(myEngine.can(guest, CustomAction.READ, null));
      assertTrue(myEngine.can(null, CustomAction.READ, "something"));

      assertTrue(myEngine.can(null, CustomAction.EDIT, "something"));
      assertFalse(myEngine.can(admin, CustomAction.EDIT, 15));
      assertEquals(asList("The Object passed should be a String"), myEngine.why(admin, CustomAction.EDIT, 15));
      assertFalse(myEngine.can(null, CustomAction.EDIT, 15));
      assertEquals(asList("The Object passed should be a String"), myEngine.why(admin, CustomAction.EDIT, 15));

      assertTrue(myEngine.can(admin, DELETE, "something"));
      assertFalse(myEngine.can(admin, DELETE, null));
      assertEquals(asList("The Object passed should be a String"), myEngine.why(admin, DELETE, null));
      assertFalse(myEngine.can(guest, DELETE, null));
      assertEquals(asList("You need to be an Admin", "The Object passed should be a String"),
              myEngine.why(guest, DELETE, null));
      assertFalse(myEngine.can(guest, DELETE, "something"));
      assertEquals(asList("You need to be an Admin"),
              myEngine.why(guest, DELETE, "something"));
      assertTrue(myEngine.can(null, CustomAction.IDENTICAL, null));
      assertTrue(myEngine.can(admin, CustomAction.IDENTICAL, admin));
      assertFalse(myEngine.can(admin, CustomAction.IDENTICAL, "not admin"));
      assertEquals(asList("The user and the object are different"), myEngine.why(admin, CustomAction.IDENTICAL, "not admin"));
   }
}
