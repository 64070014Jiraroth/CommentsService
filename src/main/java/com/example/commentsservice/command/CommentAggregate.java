package com.example.commentsservice.command;

import com.example.commentsservice.command.comment.CreateCommentCommand;
import com.example.commentsservice.command.comment.DeleteCommentCommand;
import com.example.commentsservice.command.comment.UpdateCommentCommand;
import com.example.commentsservice.core.events.CommentCreateEvent;
import com.example.commentsservice.core.events.CommentDeleteEvent;
import com.example.commentsservice.core.events.CommentUpdateEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Aggregate
public class CommentAggregate {
    @AggregateIdentifier
    private String commentId;
    private String userId;
    private Date commentDate;
    private String commentDetail;
    private Integer reportCount;
    private String chapterId;
    private String bookId;

    public CommentAggregate() {}

    @CommandHandler
    public CommentAggregate(CreateCommentCommand createCommentCommand) {
        System.out.println("CommentAggregate : Create");

        if(createCommentCommand.getCommentDetail() == null || createCommentCommand.getCommentDetail().isBlank()) {
            throw new IllegalArgumentException("Comment Detail cannot be empty");
        }

        CommentCreateEvent commentCreateEvent = new CommentCreateEvent();
        BeanUtils.copyProperties(createCommentCommand, commentCreateEvent);
        AggregateLifecycle.apply(commentCreateEvent);
    }

    @EventSourcingHandler
    public void on(CommentCreateEvent commentCreateEvent) {
        System.out.println("ON AGGREGATE : Create");

        this.commentId = commentCreateEvent.getCommentId();
        this.userId = commentCreateEvent.getUserId();
        this.commentDate = commentCreateEvent.getCommentDate();
        this.commentDetail = commentCreateEvent.getCommentDetail();
        this.reportCount = commentCreateEvent.getReportCount();
    }

    //UPDATE
    @CommandHandler
    public CommentAggregate(UpdateCommentCommand updateCommentCommand) {
        System.out.println("CommentAggregate: Update");

        CommentUpdateEvent commentUpdateEvent = new CommentUpdateEvent();
        BeanUtils.copyProperties(updateCommentCommand, commentUpdateEvent);
        AggregateLifecycle.apply(commentUpdateEvent);
    }

    @EventSourcingHandler
    public void on(CommentUpdateEvent commentUpdateEvent) {
        System.out.println("ON AGGREGATE : Update");

        this.commentId = commentUpdateEvent.getCommentId();
        this.userId = commentUpdateEvent.getUserId();
        this.commentDate = commentUpdateEvent.getCommentDate();
        this.commentDetail = commentUpdateEvent.getCommentDetail();
        this.reportCount = commentUpdateEvent.getReportCount();
    }

    //DELETE
    @CommandHandler
    public CommentAggregate(DeleteCommentCommand deleteCommentCommand) {
        System.out.println("CommentAggregate : Delete");

        CommentDeleteEvent commentDeleteEvent = new CommentDeleteEvent();
        BeanUtils.copyProperties(deleteCommentCommand, commentDeleteEvent);
        AggregateLifecycle.apply(commentDeleteEvent);
    }

    @EventSourcingHandler
    public void on(CommentDeleteEvent commentDeleteEvent) {
        System.out.println("ON AGGREGATE : DELETE");

        this.commentId = commentDeleteEvent.getCommentId();
        this.userId = commentDeleteEvent.getUserId();
        this.commentDate = commentDeleteEvent.getCommentDate();
        this.commentDetail = commentDeleteEvent.getCommentDetail();
        this.reportCount = commentDeleteEvent.getReportCount();
    }

}
