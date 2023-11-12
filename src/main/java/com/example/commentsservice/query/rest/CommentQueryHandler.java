package com.example.commentsservice.query.rest;

import com.example.commentsservice.command.rest.CommentRestModel;
import com.example.commentsservice.core.data.CommentEntity;
import com.example.commentsservice.core.data.CommentRepository;
import com.example.commentsservice.query.rest.comment.FindCommentsByChapterIdQuery;
import com.example.commentsservice.query.rest.comment.FindCommentsByCommentIdQuery;
import com.example.commentsservice.query.rest.comment.FindCommentsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.*;

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
    public CommentRestModel findCommentsByCommentId(FindCommentsByCommentIdQuery findCommentsByCommentIdQuery) {
        CommentEntity commentEntity = commentRepository.findCommentEntityByCommentId(findCommentsByCommentIdQuery.getCommentId());
        System.out.println("CommentQueryHandler findCommentsByCommentId");

        if(commentEntity != null) {
            CommentRestModel commentRestModel = new CommentRestModel();
            BeanUtils.copyProperties(commentEntity, commentRestModel);
            return commentRestModel;
        } else {
            System.out.println("can't find this commentID : " + findCommentsByCommentIdQuery.getCommentId());
            return null;
        }
    }

    @QueryHandler
    public List<CommentRestModel> findCommentsByChapterId(FindCommentsByChapterIdQuery findCommentsByChapterIdQuery) {
        List<CommentEntity> commentEntities = (List<CommentEntity>) commentRepository.findCommentEntityByChapterId(findCommentsByChapterIdQuery.getChapterId());
        System.out.println("commentEntities: " + commentEntities);
        System.out.println("CommentQueryHandler findCommentsByChapterIdQuery: " + findCommentsByChapterIdQuery.getChapterId());

        List<CommentRestModel> commentRestModels = new ArrayList<>();

        if (commentEntities != null && !commentEntities.isEmpty()) {
            for (CommentEntity commentEntity : commentEntities) {
                CommentRestModel commentRestModel = new CommentRestModel();
                BeanUtils.copyProperties(commentEntity, commentRestModel);
                commentRestModels.add(commentRestModel);
                System.out.println("commentRestModel: " + commentRestModel);
            }
            return commentRestModels;
        } else {
            System.out.println("No comments found for chapterID: " + findCommentsByChapterIdQuery.getChapterId());
            return Collections.emptyList();
        }
    }


}
