Canny
=====

Canny is a very small and very simple authorization solution in java more or less inspired from [ryanb/cancan](http://github.com/ryanb/cancan).
This is completely decoupled from any role based implementation allowing you to define user roles the way you want. All permissions are stored in a single location for convenience.

Define rules
------------

      Authorization.load(new Engine(
              new Definition() {{

                 forAction(CREATE_POST)
                         .allow(REGISTERED_USER)
                         .allow(ADMIN)
                         .deny(BAN_USER);

                 forAction(READ_POST)
                         .allow(ALL)
                         .deny(OBJECT_IS_DISACTIVATED);

                 forAction(UPDATE_POST)
                         .allow(ADMIN)
                         .allow(OWNER)
                         .deny(OBJECT_IS_DISACTIVATED);

                 forAction(SUPER_SECURE_ACTION)
                         .allow(ADMIN, OWNER);
              }}
      ));
         
_see more information in the [test folder](http://github.com/eltados/canny/tree/master/test/com/izera2/canny/TestAuthorization.java)._

Use Engine
----------

      Authorization.can(A_USER, CREATE_POST); // return boolean
      Authorization.can(A_USER, CREATE_POST); // return boolean


_see more information in the [test folder](http://github.com/eltados/canny/tree/master/test/com/izera2/canny/TestAuthorization.java)._
