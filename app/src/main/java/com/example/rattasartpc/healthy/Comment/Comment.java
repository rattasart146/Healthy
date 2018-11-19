package com.example.rattasartpc.healthy.Comment;

public class Comment {

    private int commentPostId;
    private int commentId;
    private String commentContent;
    private String commentName;
    private String commentEmail;

    public Comment(int commentPostId, int commentId, String commentContent, String commentName, String commentEmail) {
        this.commentPostId = commentPostId;
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.commentName = commentName;
        this.commentEmail = commentEmail;
    }

    public int getCommentPostId() {
        return commentPostId;
    }

    public void setCommentPostId(int commentPostId) {
        this.commentPostId = commentPostId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    public String getCommentEmail() {
        return commentEmail;
    }

    public void setCommentEmail(String commentEmail) {
        this.commentEmail = commentEmail;
    }
}
