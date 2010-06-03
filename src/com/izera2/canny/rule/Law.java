package com.izera2.canny.rule;

import com.izera2.canny.interfaces.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Law{
   List<RuleSet> rulesets = new ArrayList<RuleSet>();

   public Law() {
   }

   public Law allow(RuleSet ruleset) {
      ruleset.actionType=RuleSet.ALLOW;
      this.rulesets.add(ruleset);
      return this;
   }
   public Law deny(RuleSet ruleset) {
      ruleset.actionType=RuleSet.DENY;
      this.rulesets.add(ruleset);
      return this;
   }
   public Law allow(Law law) {
      this.rulesets.addAll(law.rulesets);
      return this;
   }
   public Law deny(Law law) {
      this.rulesets.addAll(law.rulesets);
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
      RuleSet ruleset = new RuleSet(Arrays.asList(rules),RuleSet.DENY);
      rulesets.add(ruleset);
      return this;
   }

   protected boolean isEmpty() {
      return rulesets.isEmpty();
   }

   private List<RuleSet> getRuleSet(int actionType ){
      ArrayList<RuleSet> output = new ArrayList<RuleSet>();
      for (RuleSet ruleset : rulesets)
        if(ruleset.actionType == actionType)
           output.add(ruleset);
      return output;
   }
   private List<RuleSet> getDenyRuleSet(){
      return getRuleSet(RuleSet.DENY);
   }
   private List<RuleSet> getAllowRuleSet(){
      return getRuleSet(RuleSet.ALLOW);
   }

   public boolean can(User user, Object object) {
      
      for (RuleSet denyRuleSet : getDenyRuleSet()) {
         if (denyRuleSet.can(user, object))
            return false;
      }
      
      for (RuleSet ruleset : getAllowRuleSet()) {
         if (ruleset.can(user, object))
            return true;
      }

      return false;
   }


   public String getErrors(User user, Object object) {
      String errors ="";
      for (RuleSet ruleSet : getFailingRuleSets(user, object)) {
         errors+= ruleSet.toString(user,object)+"\n";
      }
      return errors;
   }

   public List<RuleSet> getFailingRuleSets(User user, Object object) {
      List<RuleSet> output = new ArrayList<RuleSet>();
      for (RuleSet ruleset : rulesets) {
         if(ruleset.actionType==RuleSet.ALLOW)
            if(!ruleset.can(user,object))
               output.add(ruleset);
            else
               return new ArrayList<RuleSet>();
         if(ruleset.actionType==RuleSet.DENY)
            if(ruleset.can(user,object))
               output.add(ruleset);
      }
      return output;
   }
   private List<RuleSet> getAllowFailingRuleSets(User user, Object object) {
      List<RuleSet> output = new ArrayList<RuleSet>();
      for (RuleSet ruleset : getAllowRuleSet()) {
         if(!ruleset.can(user,object))
            output.add(ruleset);
         else
            return new ArrayList<RuleSet>();
      }
      return output;
   }

   private List<RuleSet> getDenyFailingRuleSets(User user, Object object) {
      List<RuleSet> output= new ArrayList<RuleSet>();
      for (RuleSet ruleset : getDenyRuleSet()) {
         if(ruleset.can(user,object))
            output.add(ruleset);
      }
      return output;
   }
}
