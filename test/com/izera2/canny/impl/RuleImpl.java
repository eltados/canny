package com.izera2.canny.impl;

import com.izera2.canny.interfaces.User;
import com.izera2.canny.rule.Rule;


public  class RuleImpl {

   public static Rule Not(final Rule rule) {
      return new Rule(rule.getNotErrorMessage() , rule.getErrorMessage()) {
        public boolean can(User user, Object object) {
           return !rule.can(user,object);
        }
      };
   }

  
   public static final Rule ALL = new Rule("Always valid" , "Always rejected") {
      public boolean can(User user, Object object) {
         return true;
      }
   };
   public static final Rule NONE = new Rule("Always rejected", "Always valid") {
      public boolean can(User user, Object object) {
         return false;
      }
   };

   public static final Rule TRUE = new Rule("True" , "False") {
      public boolean can(User user, Object object) {
         return true;
      }
   };
   public static final Rule FALSE = TRUE.not();



}