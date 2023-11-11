package com.example.commentsservice.core;

import com.example.commentsservice.core.data.CommentEntity;
import com.example.commentsservice.core.data.CommentRepository;
import com.example.commentsservice.core.events.CommentCreateEvent;
import com.example.commentsservice.core.events.CommentDeleteEvent;
import com.example.commentsservice.core.events.CommentUpdateEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentEventHandler {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentEventHandler(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @EventHandler
    public void on(CommentCreateEvent commentCreateEvent) {
        System.out.println("Adding Comment in DB");

        CommentEntity commentEntity = new CommentEntity();
        BeanUtils.copyProperties(commentCreateEvent, commentEntity);
        commentRepository.insert(commentEntity);
    }

    @EventHandler
    public void on(CommentUpdateEvent commentUpdateEvent) {
        System.out.println("Updating Comment in DB");

        CommentEntity commentEntity = new CommentEntity();
        BeanUtils.copyProperties(commentUpdateEvent, commentEntity);
        commentRepository.save(commentEntity);
    }

    @EventHandler
    public void on(CommentDeleteEvent commentDeleteEvent) {
        System.out.println("Deleting Comment in DB");

        CommentEntity commentEntity = new CommentEntity();
        BeanUtils.copyProperties(commentDeleteEvent, commentEntity);
        commentRepository.deleteById(commentEntity.getCommentId());
    }

}
