package com.example.rattasartpc.healthy.Post;

public class Post {

    private int userId;
    private int postId;
    private String postTitle;
    private String postContent;

    public Post(int userId, int postId, String postTitle, String postContent) {
        this.userId = userId;
        this.postId = postId;
        this.postTitle = postTitle;
        this.postContent = postContent;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }
}
