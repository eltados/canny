package engine.rule;

import engine.User;
import engine.Translator;

import java.util.Locale;


public abstract class Rule {
   protected String errorMessage = "the rule " + this.getClass().getName() + " failed";
   protected String notErrorMessage = "the rule " + this.getClass().getName() + " did not failed";
   public abstract boolean can(User user, Object object);

   public String getErrorMessage(Translator translator , Locale locale, Object ... objects) {
         return translator.translate(errorMessage, locale , objects);
   }

   public String getNotErrorMessage(Translator translator , Locale locale, Object ... objects) {
         if (notErrorMessage == null)
            return translator.translate(errorMessage + ".not", locale, objects);
         return translator.translate(notErrorMessage, locale,objects);
   }

   public Rule() {
   }

   public Rule(String errorMessage) {
      this.errorMessage = errorMessage;
   }
   public Rule(String errorMessage, String notErrorMessage) {
      this.errorMessage = errorMessage;
      this.notErrorMessage = notErrorMessage;
   }





}