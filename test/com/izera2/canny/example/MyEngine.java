package com.izera2.canny.example;

import com.izera2.canny.rule.Definition;
import com.izera2.canny.rule.Engine;

import static com.izera2.canny.example.MyActions.*;

public class MyEngine {
    private static Engine instance;

    private MyEngine() {
    }

    public static Engine getInstance() {
        if (instance == null) {
            instance = new Engine(
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
        }
        return instance;
    }
}
