package test;

import engine.User;
import engine.rule.Rule;

public class IdenticalRule extends Rule {
   {
      errorMessage    = "The user and the object are different";
   }

   public boolean can(User user, Object object) {
      return user == object;
   }
}