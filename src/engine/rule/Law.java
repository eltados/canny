package engine.rule;

import engine.Action;
import engine.Translator;
import engine.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mathieu
 * Date: 15-Apr-2010
 * Time: 12:05:37
 * To change this template use File | Settings | File Templates.
 */
public class Law {
   List<RuleSet> rulesets = new ArrayList<RuleSet>();
   List<RuleSet> denyRulesets = new ArrayList<RuleSet>();
   Action action ;

   public Law(Action action) {
      this.action = action;
   }

   public Law allow(Rule... rules) {
      RuleSet ruleset = new RuleSet(Arrays.asList(rules));
      rulesets.add(ruleset);
      return this;
   }
   public Law deny(Rule... rules) {
      RuleSet ruleset = new RuleSet(Arrays.asList(rules));
      denyRulesets.add(ruleset);
      return this;
   }

   protected boolean isEmpty() {
      return rulesets.isEmpty();
   }
   public boolean can(User user,Object object) {
      for (RuleSet ruleset : denyRulesets) {
         if(ruleset.can(user,object))
            return false;
      }
      for (RuleSet ruleset : rulesets) {
         if(ruleset.can(user,object))
            return true;
      }
      return false;
   }

   public List<String> getErrors(User user, Object object, Translator translator) {
      List<String> errors = new ArrayList<String>();
      for (RuleSet ruleSet : getAllowFailingRuleSets(user, object)) {
         errors.add(ruleSet.toString());
      }
      for (RuleSet ruleSet : getDenyFailingRuleSets(user, object)) {
         errors.add(ruleSet.toString());
      }
      return errors;
   }
   private List<RuleSet> getAllowFailingRuleSets(User user, Object object) {
      List<RuleSet> output = new ArrayList<RuleSet>();
      for (RuleSet ruleset : rulesets) {
         if(!ruleset.can(user,object))
            output.add(ruleset);
         else
            return new ArrayList<RuleSet>();
      }
      return output;
   }

   private List<RuleSet> getDenyFailingRuleSets(User user, Object object) {
      List<RuleSet> output= new ArrayList<RuleSet>();
      for (RuleSet ruleset : denyRulesets) {
         if(ruleset.can(user,object))
            output.add(ruleset);
      }
      return output;
   }
}
