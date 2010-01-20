Canny
=====

Canny is a very small and very simple authorization solution in java more or less inspired from [ryanb/cancan](http://github.com/ryanb/cancan).
This is completely decoupled from any role based implementation allowing you to define user roles the way you want. All permissions are stored in a single location for convenience.

Define rules
------------

      Engine myEngine = new Engine(
                 Translator.DEFAULT,
                 new Definition() {{
                    setRule(CANCEL, CANCELLABLE , OBJECT_SHOULD_BE_A_INTEGER);
                    setRule(READ, TRUE);
                    setRule(EDIT, OBJECT_SHOULD_BE_A_STRING);
                    setRule(DELETE, ADMIN, OBJECT_SHOULD_BE_A_STRING);
                    setRule(IDENTICAL, new IdenticalRule());
                 }}
         );
         
_see more information in the [test folder](http://github.com/eltados/canny/tree/master/src/test/)._

Use Engine
----------
      myEngine.can(admin, CustomAction.READ)); 
      myEngine.can(null, CustomAction.READ, "something");
      myEngine.can(admin, CustomAction.EDIT, 15)
      myEngine.can(admin, DELETE, "something"));
      myEngine.can(guest, DELETE, null);
      myEngine.can(admin, DELETE, null);
      myEngine.why(admin, DELETE, null); // => "The Object passed should be a String"

_see more information in the [test folder](http://github.com/eltados/canny/tree/master/src/test/)._
