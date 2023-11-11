package com.example.commentsservice.core.events;

import lombok.Data;

import java.util.Date;

@Data
public class CommentCreateEvent {
    private String commentId;
    private String userId;
    private Date commentDate;
    private String commentDetail;
    private Integer reportCount;
    private String chapterId;
    private String bookId;
}
