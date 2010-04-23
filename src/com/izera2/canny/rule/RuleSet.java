package com.izera2.canny.rule;

import com.izera2.canny.utils.Dynamic;
import com.izera2.canny.interfaces.Translator;
import com.izera2.canny.interfaces.User;

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
