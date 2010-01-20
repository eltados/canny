package test;

import engine.User;
import engine.rule.Rule;

/**
 * Created by IntelliJ IDEA.
 * User: Administrateur
 * Date: 17 déc. 2009
 * Time: 19:12:59
 * To change this template use File | Settings | File Templates.
 */
public class IdenticalRule extends Rule {
   {
      errorMessage    = "The user and the object are different";
      notErrorMessage = "The user and the object are the same";
   }

   public boolean can(User user, Object object) {
      return user == object;
   }
}