package com.example.commentsservice.query.rest.comment;

public class FindCommentsByCommentIdQuery {
    private final String commentId;

    public FindCommentsByCommentIdQuery(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentId() {
        return commentId;
    }
}
