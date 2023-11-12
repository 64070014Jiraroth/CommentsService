package com.example.commentsservice.query.rest;

import com.example.commentsservice.command.rest.CommentRestModel;
import com.example.commentsservice.core.data.CommentEntity;
import com.example.commentsservice.core.data.CommentRepository;
import com.example.commentsservice.query.rest.comment.FindCommentsByChapterIdQuery;
import com.example.commentsservice.query.rest.comment.FindCommentsByCommentIdQuery;
import com.example.commentsservice.query.rest.comment.FindCommentsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentQueryHandler {
    private final CommentRepository commentRepository;

    public CommentQueryHandler(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @QueryHandler
    public List<CommentRestModel> findComments(FindCommentsQuery findCommentsQuery) {
        System.out.println("CommentQueryHandler findComments " + findCommentsQuery);

        List<CommentRestModel> commentRest = new ArrayList<>();
        List<CommentEntity> storedComments = commentRepository.findAll();
        for (CommentEntity commentEntity : storedComments) {
            CommentRestModel commentRestModel = new CommentRestModel();
            BeanUtils.copyProperties(commentEntity, commentRestModel);
            commentRest.add(commentRestModel);
        }
        return commentRest;
    }

    @QueryHandler
    public CommentRestModel findCommentsByCommentId(FindCommentsByCommentIdQuery query) {
        CommentEntity commentEntity = commentRepository.findCommentEntityByCommentId(query.getCommentId());
        System.out.println("CommentQueryHandler findCommentsByCommentId");

        if(commentEntity != null) {
            CommentRestModel commentRestModel = new CommentRestModel();
            BeanUtils.copyProperties(commentEntity, commentRestModel);
            return commentRestModel;
        } else {
            return null;
        }
    }


    @QueryHandler
    public CommentRestModel findCommentsByChapterId(FindCommentsByChapterIdQuery query) {
        CommentEntity commentEntity = commentRepository.findCommentEntityByChapterId(query.getChapterId());
        System.out.println("CommentQueryHandler findCommentsByChapterId");

        if(commentEntity != null) {
            CommentRestModel commentRestModel = new CommentRestModel();
            BeanUtils.copyProperties(commentEntity, commentRestModel);
            return commentRestModel;
        } else {
            return null;
        }
    }
}
