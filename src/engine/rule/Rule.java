package engine.rule;

import engine.User;
import engine.Translator;

import java.util.Locale;


public abstract class Rule {
   protected String errorMessage = "the rule " + this.getClass().getName() + " failed";
   protected String notErrorMessage = "the rule " + this.getClass().getName() + " did not failed";
   public abstract boolean can(User user, Object object);

   protected String getErrorMessage() {
         return errorMessage;
   }
   public String getErrorMessage(Translator translator , Locale locale, Object ... objects) {
         return translator.translate(errorMessage, locale , objects);
   }



   public Rule() {
   }

   public Rule(String errorMessage) {
      this.errorMessage = errorMessage;
   }


   public String toString(){
      return errorMessage;
   }





}