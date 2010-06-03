package com.izera2.canny.rule;

import com.izera2.canny.interfaces.User;
import com.izera2.canny.interfaces.Translator;

import java.util.Locale;


public abstract class Rule {
   protected String errorMessage = "the rule " + this.getClass().getName() + " failed";
   protected String notErrorMessage = null;
   public abstract boolean can(User user, Object object);

   public String getErrorMessage() {
         return errorMessage;
   }
   public String getNotErrorMessage() {
         return notErrorMessage == null ? "NOT("+errorMessage+")":notErrorMessage;
   }
   public String getErrorMessage(Translator translator , Locale locale, Object ... objects) {
         return translator.translate(errorMessage, locale , objects);
   }

   public Rule() {
   }

   public Rule(String errorMessage) {
      this.errorMessage = errorMessage;
   }

   public Rule(String errorMessage,String notErrorMessage) {
      this.errorMessage = errorMessage;
      this.notErrorMessage = notErrorMessage;
   }

   public String toString(){
      return errorMessage;
   }

   public Rule not() {
      return new NotRule(this);
   }
   public class NotRule extends Rule{
      Rule rule;
      public NotRule(Rule rule) {
         this.rule = rule;
         this.errorMessage=rule.getNotErrorMessage();
         this.notErrorMessage=rule.getErrorMessage();
      }
      public boolean can(User user, Object object) {
         return !rule.can(user,object);
      }
   }


}