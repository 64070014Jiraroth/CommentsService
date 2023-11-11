package com.example.commentsservice.command.rest;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommentRestModel implements Serializable {
    private String commentId;
    private String userId;
    private Date commentDate;
    private String commentDetail;
    private Integer reportCount;
    private String chapterId;
    private String bookId;
}
