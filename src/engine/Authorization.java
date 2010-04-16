package engine;

import engine.exception.AccessDeniedException;
import engine.rule.Engine;

import java.util.List;
import java.util.Map;

public class Authorization {
   private static Engine engine=null ;

   private Authorization() {
   }

   public static Engine getEngine() {
      return engine;
   }
   public static void setEngine(Engine theEngine) {
      engine = theEngine;
   }

   public static boolean can(User user, Action action, Object object) {
      return engine.can(user, action, object);
   }

   public static List<String> why(User user, Action action, Object object) {
      return engine.why(user, action, object);
   }

   public static boolean isAutorized(User user, Action action, Object object) throws AccessDeniedException {
      return engine.isAutorized(user, action, object);
   }

   public static List<Action> getAllActions() {
      return engine.getAllActions();
   }

   public static List<Action> getAvailableActions(List<Action> actions, User user, Object object) {
      return engine.getAvailableActions(actions, user, object);
   }

   public static Map<Action, List<String>> getActionsAccess(List<Action> actions, User user, Object object) {
      return engine.getActionsAccess(actions, user, object);
   }

   public static List<Action> getUnavailableActions(List<Action> actions, User user, Object object) {
      return engine.getUnavailableActions(actions, user, object);
   }


   public static boolean can(User user, Action action) {
      return can(user, action, null);
   }

   public static List<Action> getUnavailableActions(List<Action> actions, User user) {
      return getUnavailableActions(actions, user, null);
   }

   public static List<Action> getUnavailableActions(User user, Object object) {
      return getUnavailableActions(getAllActions(), user, object);
   }

   public static List<Action> getUnavailableActions(User user) {
      return getUnavailableActions(getAllActions(), user, null);
   }

   public static Map<Action, List<String>> getUnavailableActionsAccess(User user, Object object) {
      return getActionsAccess(getUnavailableActions(user, object), user, object);
   }

   public static Map<Action, List<String>> getUnavailableActionsAccess(User user) {
      return getActionsAccess(getUnavailableActions(user, null), user, null);
   }

   public static Map<Action, List<String>> getActionsAccess(User user, Object object) {
      return getActionsAccess(getAllActions(), user, object);
   }

   public static Map<Action, List<String>> getActionsAccess(List<Action> actions, User user) {
      return getActionsAccess(actions, user, null);
   }

   public static Map<Action, List<String>> getActionsAccess(User user) {
      return getActionsAccess(getAllActions(), user, null);
   }

   public static List<Action> getAvailableActions(User user, Object object) {
      return getAvailableActions(getAllActions(), user, object);
   }

   public static List<Action> getAvailableActions(User user) {
      return getAvailableActions(getAllActions(), user, null);
   }

   public static boolean isAutorized(User user, Action action) throws AccessDeniedException {
      return isAutorized(user, action, null);
   }

   public static List<String> why(User user, Action action) {
      return why(user, action, null);
   }
}
