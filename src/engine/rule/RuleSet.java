package engine.rule;

import engine.Dynamic;
import engine.Translator;
import engine.User;

import java.util.ArrayList;
import java.util.List;

public class RuleSet {

   List<Rule> rules = new ArrayList<Rule>();

   //-----------------------------------------
   
   public RuleSet(List<Rule> rules) {
      this.rules = rules;
   }
   //-------------------------------------------------------------

   public List<Rule> getRules() {
      return rules;
   }

   public void setRules(List<Rule> rules) {
      this.rules = rules;
   }

//------------------------------------------------

   public boolean can(User user, Object object) {
      for (Rule rule : rules) {
         if (!rule.can(user, object))
            return false;
      }
      return true;
   }

   public List<String> getErrors(User user, Object object, Translator translator) {
      List<String> errors = new ArrayList<String>();
      for (Rule rule : rules) {
         if (!rule.can(user, object))
            errors.add(rule.getErrorMessage(translator, Dynamic.getLocale(user), user, object));
      }
      return errors;
   }

   public String toString(){
      String ouput = "";
      for (Rule rule : rules) {
         ouput+= rule.getErrorMessage() +" and ";
      }
      if(ouput.endsWith(" and "))
         ouput = ouput.substring(0, ouput.length()-5);
      return ouput;
   }



}
