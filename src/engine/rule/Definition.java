package engine.rule;

import engine.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Definition {
   HashMap<Action, HashMap<Class,Law>> laws = new HashMap<Action, HashMap<Class,Law>>();

   protected HashMap<Action, HashMap<Class, Law>> getLaws() {
      return laws;
   }

   public Law forAction(Action action) {
      return forAction(action,null);
   }

   public Law forAction(Action action, Class clazz) {
      Law law = new Law(action);
      if(laws.get(action)==null)
         laws.put(action, new HashMap<Class,Law>());
      laws.get(action).put(clazz,law);
      return law;
   }

   protected Law get(Action key) {
      return laws.get(key).get(null);
   }
   protected Law get(Action key , Class clazz) {
      return laws.get(key).get(clazz);
   }

   protected List<Action> getActions() {
      List<Action> actions = new ArrayList<Action>();
      for (Action action : laws.keySet()) {
         actions.add(action);
      }
      return actions;
   }


}