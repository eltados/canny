package com.izera2.canny.impl;

import com.izera2.canny.interfaces.User;
import com.izera2.canny.rule.Rule;


public  class RuleImpl {
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