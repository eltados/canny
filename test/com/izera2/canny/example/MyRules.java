package com.izera2.canny.example;

import com.izera2.canny.example.vo.PostVO;
import com.izera2.canny.example.vo.UserVO;
import com.izera2.canny.interfaces.User;
import com.izera2.canny.rule.Rule;

public class MyRules {

    public static final Rule ALL = new Rule("Always valid", "Always rejected") {
        public boolean can(User user, Object object) {
            return true;
        }
    };
    public static final Rule AUTHOR = new Rule("You are an author", "You are not an author") {
        public boolean can(User user, Object object) {
            UserVO uservo = (UserVO) user;
            return uservo.isAuthor();
        }
    };
    public static final Rule ADMIN = new Rule("You are an admin", "You are not an admin") {
        public boolean can(User user, Object object) {
            UserVO uservo = (UserVO) user;
            return uservo.isAdmin();
        }
    };
    public static final Rule SPAMMER = new Rule("You are an spammer", "You are an spammer") {
        public boolean can(User user, Object object) {
            UserVO uservo = (UserVO) user;
            return uservo.isSpammer();
        }
    };
    public static final Rule AUTHOR_OF_THE_POST = new Rule("You are the author of this post", "You are not the author of this post") {
        public boolean can(User user, Object object) {
            UserVO uservo = (UserVO) user;
            PostVO postvo = (PostVO) object;
            return uservo.equals(postvo.getAuthor());
        }
    };


}
