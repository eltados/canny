package engine.rule;

import engine.Action;
import static engine.rule.Operator.*;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import static java.util.Arrays.asList;

public class Definition {
   HashMap<Action, RuleSet> rules = new HashMap<Action, RuleSet>();

   public RuleSet get(Action key) {
      return rules.get(key);
   }

   public List<Action> getActions() {
      List<Action> actions = new ArrayList<Action>();
      for (Action action : rules.keySet()) {
         actions.add(action);
      }
      return actions;
   }

   public void setRule(Action action, Rule rule) {
      setRule(AND, action, (Rule[]) asList(rule).toArray());
   }

   public void setRule(Operator operator, Action action, Rule rule) {
      setRule(operator, action, (Rule[]) asList(rule).toArray());
   }

   public void setRule(Action action, Rule... rulesArray) {
      setRule(AND, action, rulesArray);
   }

   public void setRule(Operator operator, Action action, Rule... rulesArray) {
      RuleSet ruleset = new RuleSet(asList(rulesArray), operator);
      rules.put(action, ruleset);
   }


}
