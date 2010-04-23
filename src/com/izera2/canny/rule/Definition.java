package com.izera2.canny.rule;

import com.izera2.canny.interfaces.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Definition {
   HashMap<Action, Law> laws = new HashMap<Action,Law>();

   protected HashMap<Action,  Law> getLaws() {
      return laws;
   }


   public Law forAction(Action action) {
      Law law = new Law();
      laws.put(action,law);
      return law;
   }

   protected Law get(Action action) {
      return laws.get(action);
   }


   protected List<Action> getActions() {
      List<Action> actions = new ArrayList<Action>();
      for (Action action : laws.keySet()) {
         actions.add(action);
      }
      return actions;
   }


}