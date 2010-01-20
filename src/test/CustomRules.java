package test;

import engine.User;
import engine.rule.Rule;
import engine.rule.Rules;

/**
 * Created by IntelliJ IDEA.
 * User: Administrateur
 * Date: 17 déc. 2009
 * Time: 19:12:59
 * To change this template use File | Settings | File Templates.
 */
public class CustomRules extends Rules {

   public static final Rule OBJECT_SHOULD_BE_A_STRING = new Rule("The Object passed should be a String") {
      public boolean can(User user, Object object) {
         return object instanceof String;
      }
   };

   public static final Rule ADMIN = new Rule("You need to be an Admin", "You should not be an Admin") {
      public boolean can(User user, Object object) {
         UserVO userVO = (UserVO) user;
         return userVO != null && userVO.getRole().equals("admin");
      }
   };
   public static final Rule GUEST_OR_HIGHER = new Rule("You need to be an a guest or higher level") {
      public boolean can(User user, Object object) {
         UserVO userVO = (UserVO) user;
         return userVO != null && (userVO.getRole().equals("admin") || userVO.getRole().equals("guest"));
      }
   };

}
