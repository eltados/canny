Canny
=====

Scottish English for the "can" from the verb can (“‘to know’”) from Middle English can, first and third person singular of cunnen, connen (“‘to be able, know how to’”) from Old English cunnan (“‘to know how to, be able to’”). More at can, cunning.(http://en.wiktionary.org/wiki/canny#Scots)

Canny is a very small and very simple *authorization* solution in java more or less inspired from [ryanb/cancan](http://github.com/ryanb/cancan).
It does not provide any sort of authentication mechanism.
It aims to provide a decoupled from any role based implementation allowing you to define user roles the way you want. All permissions are stored in a single location for convenience. And be reuse at every level of your application controller or view.

In order to use canny in your application you first need to create an engine that will contain all your authorization logic.
You can do this using a small DSL as follow.

Define rules
------------

      Authorization.load(new Engine(
                    new Definition() {{

                        forAction(CREATE_POST)  //  author and admins  can create post
                                .allow(MyRules.AUTHOR) // the 2 rules are joined by a OR
                                .allow(MyRules.ADMIN);

                        forAction(DELETE_POST)  // a post can be deleted by an admin or the author of the post
                                .allow(MyRules.AUTHOR_OF_THE_POST) // the 2 rules are joined by a OR
                                .allow(MyRules.ADMIN);

                        forAction(VIEW_POST) //  everybody can see the post
                                .allow(MyRules.ALL);

                        forAction(PROMOTE_POST)  // a post can promoted only by authors but the user cannot self promote
                                .allow(MyRules.AUTHOR_OF_THE_POST.not(), MyRules.AUTHOR); // the 2 rules are joined by a AND

                        forAction(COMMENT_POST) // everybody can comment a post but the knocked spammer
                                .deny(MyRules.SPAMMER)
                                .allow(MyRules.ALL);
                        /* could also be written like this
                        forAction(COMMENT_POST)
                                .allow(MyRules.SPAMMER.not());
                        */
                    }}
            );
      ));
         
_see a [the full example](http://github.com/eltados/canny/tree/master/test/com/izera2/canny/example/)._

Use Engine
----------
      Authorization.can(admin, MyActions.CREATE_POST) // return boolean
      Authorization.can(admin, MyActions.DELETE_POST, post)
      Authorization.isAuthorized(admin, MyActions.CREATE_POST) // throw an AccessDeniedException in case of access denied

The engine can be user in you view ( for example to hidden the links the user don't have access to) and in your server side maybe using isAuthorized().


_see a [the full example](http://github.com/eltados/canny/tree/master/test/com/izera2/canny/example/)._

Early Release
-------------
This is for the moment an early library and a basically i prove of concept that we use in a medium scale application by i would be more that happy to ear any comment or suggestion. Please feel free to send me a [message](http://github.com/inbox/new/eltados) or create an [issue](http://github.com/eltados/canny/issues).
