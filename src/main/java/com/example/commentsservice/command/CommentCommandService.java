package com.example.commentsservice.command;

import com.example.commentsservice.command.comment.CreateCommentCommand;
import com.example.commentsservice.command.comment.DeleteCommentCommand;
import com.example.commentsservice.command.comment.UpdateCommentCommand;
import com.example.commentsservice.command.rest.CommentRestModel;
import com.example.commentsservice.core.data.CommentEntity;
import com.example.commentsservice.core.data.CommentRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class CommentCommandService {
    @Autowired
    private final CommandGateway commandGateway;

    @Autowired
    private final CommentRepository commentRepository;


    @Autowired
    public CommentCommandService(CommandGateway commandGateway, CommentRepository commentRepository) {
        this.commandGateway = commandGateway;
        this.commentRepository = commentRepository;
    }

    //Create
    @RabbitListener(queues = "addCommentQueue")
    public void addComment(CommentRestModel model) {
        System.out.println("CommentCommandService : create");

        CreateCommentCommand createCommentCommand = CreateCommentCommand.builder()
                .commentId(UUID.randomUUID().toString())
                .userId(model.getUserId())
                .commentDate(model.getCommentDate())
                .commentDetail(model.getCommentDetail())
                .reportCount(model.getReportCount())
//                .bookId(model.getBookId())
//                .chapterId(model.getChapterId())
                .build();

        try {
            commandGateway.sendAndWait(createCommentCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Update
    @RabbitListener(queues = "updateCommentQueue")
    public void updateComment(CommentRestModel model) {
        System.out.println("CommentCommandService : update ID : " + model);
//        System.out.println("CommentCommandService : update ID : " + model.getBookId());

        UpdateCommentCommand updateCommentCommand = UpdateCommentCommand.builder()
                .commentId(UUID.randomUUID().toString())
                .userId(model.getUserId())
                .commentDate(model.getCommentDate())
                .commentDetail(model.getCommentDetail())
                .reportCount(model.getReportCount())
//                .bookId(model.getBookId())
//                .chapterId(model.getChapterId())
                .build();

        try {
            commandGateway.sendAndWait(updateCommentCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "deleteCommentQueue")
    public void deleteComment(String commentId) {
        System.out.println("CommentCommandService : delete ID : " + commentId);

        CommentEntity commentEntity = commentRepository.findCommentEntityByCommentId(commentId);

        if(commentEntity != null) {
            DeleteCommentCommand deleteCommentCommand = DeleteCommentCommand.builder()
                    .commentId(UUID.randomUUID().toString())
                    .userId(commentEntity.getUserId())
                    .commentDate(commentEntity.getCommentDate())
                    .commentDetail(commentEntity.getCommentDetail())
                    .reportCount(commentEntity.getReportCount())
//                    .bookId(commentEntity.getBookId())
//                    .chapterId(commentEntity.getChapterId())
                    .build();

            try {
                commandGateway.sendAndWait(deleteCommentCommand);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
