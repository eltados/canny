package engine.rule;

import static engine.rule.Operator.*;
import engine.User;
import engine.Dynamic;
import engine.Translator;

import java.util.List;
import java.util.ArrayList;

public class RuleSet {

   List<Rule> rules = new ArrayList<Rule>();
   Operator operator = Operator.AND;

   //-----------------------------------------
   public RuleSet(List<Rule> rules, Operator operator) {
      this.rules = rules;
      this.operator = operator;
   }
   //-------------------------------------------------------------

   public List<Rule> getRules() {
      return rules;
   }

   public void setRules(List<Rule> rules) {
      this.rules = rules;
   }

   public Operator getOperator() {
      return operator;
   }

   public void setOperator(Operator operator) {
      this.operator = operator;
   }
//------------------------------------------------

   public boolean can(User user, Object object) {
      if (operator == OR)
         return canOR(user, object);
      if (operator == NOT)
         return canNOT(user, object);
      if (operator == NOT_OR)
         return canNOT_OR(user, object);
      return canAND(user, object);
   }

   public List<String> getErrors(User user, Object object, Translator translator) {
      if (operator == NOT || operator == NOT_OR)
         return getErrorsNOT(user, object, translator);
      else
         return getErrorsPOSITIVE(user, object, translator);
   }

   public List<String> getErrors(User user, Object object) {
      return getErrors(user, object, null);
   }
   //----------------------------------------------------------------------

   private List<String> getErrorsNOT(User user, Object object, Translator translator) {
      List<String> errors = new ArrayList<String>();
      for (Rule rule : rules) {
         if (rule.can(user, object))
            errors.add(rule.getNotErrorMessage(translator, Dynamic.getLocale(user) , user, object));
      }
      return errors;
   }

   private List<String> getErrorsPOSITIVE(User user, Object object, Translator translator) {
      List<String> errors = new ArrayList<String>();
      for (Rule rule : rules) {
         if (!rule.can(user, object))
            errors.add(rule.getErrorMessage(translator, Dynamic.getLocale(user) , user , object));
      }
      return errors;
   }

   private boolean canOR(User user, Object object) {
      for (Rule rule : rules) {
         if (rule.can(user, object))
            return true;
      }
      return false;
   }

   private boolean canAND(User user, Object object) {
      for (Rule rule : rules) {
         if (!rule.can(user, object))
            return false;
      }
      return true;
   }

   private boolean canNOT(User user, Object object) {
      return !canOR(user, object);
   }

   private boolean canNOT_OR(User user, Object object) {
      return !canAND(user, object);
   }


}
