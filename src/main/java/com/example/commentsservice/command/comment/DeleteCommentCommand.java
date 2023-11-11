package com.example.commentsservice.command.comment;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Date;

@Builder
@Data
public class DeleteCommentCommand {
    @TargetAggregateIdentifier
    private String commentId;
    private String userId;
    private Date commentDate;
    private String commentDetail;
    private Integer reportCount;
    private String chapterId;
    private String bookId;
}
