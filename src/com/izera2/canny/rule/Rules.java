package com.izera2.canny.rule;

import com.izera2.canny.interfaces.User;


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

  

}