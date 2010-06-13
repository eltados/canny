package com.izera2.canny.example.vo;

import java.util.List;

public class PostVO {
    List<CommentVO> comments; //  has many comments
    UserVO author; // belong to a user

    public List<CommentVO> getComments() {
        return comments;
    }

    public void setComments(List<CommentVO> comments) {
        this.comments = comments;
    }

    public UserVO getAuthor() {
        return author;
    }

    public void setAuthor(UserVO author) {
        this.author = author;
    }
}
