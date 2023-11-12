package com.example.commentsservice.query.rest.comment;

public class FindCommentsByChapterIdQuery {

    private final String chapterId;

    public FindCommentsByChapterIdQuery(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterId() {
        return chapterId;
    }
}
