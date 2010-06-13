package com.izera2.canny.example.vo;


public class CommentVO {
    private PostVO post; // belong_to a post
    private UserVO owner; // belong_to a user


    public PostVO getPost() {
        return post;
    }

    public void setPost(PostVO post) {
        this.post = post;
    }

    public UserVO getOwner() {
        return owner;
    }

    public void setOwner(UserVO owner) {
        this.owner = owner;
    }
}