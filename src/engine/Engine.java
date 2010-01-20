package engine;

import engine.rule.RuleSet;
import engine.rule.Definition;
import engine.exception.AccessDeniedException;

import static java.util.Arrays.asList;

import java.util.*;

public class Engine {
   private Definition definition;
   private Translator translator = Translator.NO;

   public Engine(Definition definition) {
      this.definition = definition;
   }

   public Engine(Translator translator, Definition definition) {
      this.definition = definition;
      if(translator !=null)
         this.translator = translator;
   }

   public boolean can(User user, Action action, Object object) {
      RuleSet ruleset = definition.get(action);
      if (ruleset == null || ruleset.getRules().isEmpty())
         return false;
      return ruleset.can(user, object);

   }

   public List<String> why(User user, Action action, Object object) {
      RuleSet ruleset = definition.get(action);
      if (ruleset == null)
         return getActionNotFindErrorMessages(user, action);

      if (ruleset.getRules().isEmpty())
         return getActionHasNoRulesErrorMessages(user, action);

      return ruleset.getErrors(user, object, translator);
   }

   public boolean isAutorized(User user, Action action, Object object) throws AccessDeniedException {
      if (!can(user, action, object))
         throw new AccessDeniedException();
      return true;
   }

   public List<Action> getAllActions() {
      return definition.getActions();
   }
   public List<Action> getAvailableActions(List<Action> actions, User user, Object object) {
      List<Action> output = new ArrayList<Action>();
      for (Action action : actions) {
         if (can(user, action, object))
            output.add(action);
      }
      return output;
   }


   public Map<Action, List<String>> getActionsAccess(List<Action> actions, User user, Object object) {
      Map<Action, List<String>> output = new HashMap<Action, List<String>>();
      for (Action action : actions) {
         output.put(action, why(user, action, object));
      }
      return output;
   }

   public List<Action> getUnavailableActions(List<Action> actions, User user, Object object) {
      List<Action> output = new ArrayList<Action>();
      for (Action action : actions) {
         if (!can(user, action, object))
            output.add(action);
      }
      return output;
   }

   //=======================================
   public boolean can(User user, Action action) {
      return can(user, action, null);
   }
   public List<Action> getUnavailableActions(List<Action> actions, User user) {
      return getUnavailableActions(actions, user,null) ;
   }
   public List<Action> getUnavailableActions(User user, Object object) {
      return getUnavailableActions(getAllActions(), user, object);
   }
   public List<Action> getUnavailableActions(User user) {
      return getUnavailableActions(getAllActions(), user, null);
   }
   public Map<Action, List<String>> getUnavailableActionsAccess(User user, Object object) {
      return getActionsAccess(getUnavailableActions(user, object), user, object);
   }
   public Map<Action, List<String>> getUnavailableActionsAccess(User user) {
      return getActionsAccess(getUnavailableActions(user, null), user, null);
   }
   public Map<Action, List<String>> getActionsAccess(User user, Object object) {
      return getActionsAccess(getAllActions(), user, object);
   }
   public Map<Action, List<String>> getActionsAccess(List<Action> actions, User user) {
      return getActionsAccess(actions, user, null);
   }
   public Map<Action, List<String>> getActionsAccess(User user) {
      return getActionsAccess(getAllActions(), user, null);
   }
    public List<Action> getAvailableActions(User user, Object object) {
      return getAvailableActions(getAllActions(), user, object);
   }
    public List<Action> getAvailableActions(User user) {
      return getAvailableActions(getAllActions(), user,null);
   }
    public boolean isAutorized(User user, Action action) throws AccessDeniedException {
      return isAutorized(user,action,null);
   }
   public List<String> why(User user, Action action) {
      return why(user, action, null);
   }
 //===================================================================
    private List<String> getActionHasNoRulesErrorMessages(User user, Action action) {
      if (translator != null)
         return asList(translator.translate("engine.core.action.has.no.rules", Dynamic.getLocale(user), action.toString()));
      else
         return asList("This action " + action.toString() + " has no rules association with it");
   }

   private List<String> getActionNotFindErrorMessages(User user, Action action) {
      if (translator != null)
         return asList(translator.translate("engine.core.action.does.not.exist", Dynamic.getLocale(user), action.toString()));
      else
         return asList("The action " + action.toString() + " does not exists");
   }


}