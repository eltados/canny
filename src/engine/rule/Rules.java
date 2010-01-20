package engine.rule;

import engine.User;

import java.util.Locale;

import test.UserVO;


public  class Rules {
   public static final Rule TRUE = new Rule("Always true", "Always not true") {
      public boolean can(User user, Object object) {
         return true;
      }
   };
   public static final Rule FALSE = new Rule("Always false", "Always not false") {
      public boolean can(User user, Object object) {
         return false;
      }
   };
}