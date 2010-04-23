package com.izera2.canny.rule;

import com.izera2.canny.interfaces.Translator;
import com.izera2.canny.interfaces.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Law{
   List<RuleSet> rulesets = new ArrayList<RuleSet>();
   List<RuleSet> denyRulesets = new ArrayList<RuleSet>();

   public Law() {
   }

   public Law allow(Law law) {
      this.rulesets.addAll(law.rulesets);
      return this;
   }
   public Law deny(Law law) {
      this.denyRulesets.addAll(law.denyRulesets);
      return this;
   }
   public Law addLaw(Law law) {
      return deny(law).allow(law);
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
      // todo use translator
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
