package com.izera2.canny.example.vo;

import com.izera2.canny.interfaces.User;

public class UserVO implements User {
    boolean admin=false;
    boolean author=false;
    boolean spammer=false;

    public UserVO(boolean admin, boolean author, boolean spammer) {
        this.admin = admin;
        this.author = author;
        this.spammer = spammer;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isAuthor() {
        return author;
    }

    public boolean isSpammer() {
        return spammer;
    }
}
