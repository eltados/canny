package engine.rule;

import engine.*;
import test.UserVO;


public  class Rules {
   public static final Rule ALL = new Rule("Always valid") {
      public boolean can(User user, Object object) {
         return true;
      }
   };
   public static final Rule NONE = new Rule("Always rejected") {
      public boolean can(User user, Object object) {
         return false;
      }
   };

   public static final Rule OWNER = new Rule("You need to be the owner") {
      public boolean can(User user, Object object) {
         Ownable obj = (Ownable) object;
         return obj !=null && user!=null && user.equals(obj.getOwner());
      }
   };

   public static final Rule ADMIN = new Rule("You need to be an admin") {
      public boolean can(User user, Object object) {
         return user!=null && ((Adminable) user).isAdmin();
      }
   };
   public static Rule ROLE(final String role){
        return new Rule("You need to be a  "+role) {
           public boolean can(User user, Object object) {
              if(user==null)
                 return false;
              UserVO userVO = (UserVO) user;
              return role.equals(userVO.getRole());
           }
        };
     }



   public static Rule GROUP(final Group group){
      return new Rule("You are not in the group "+group) {
         public boolean can(User user, Object object) {
            if(user==null)
               return false;
            Groupable groupable = (Groupable) user;
            return groupable.getGroups()!=null && groupable.getGroups().contains(group);
         }
      };
   }

}