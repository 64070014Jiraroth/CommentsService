package com.example.commentsservice.query;

import com.example.commentsservice.command.rest.CommentRestModel;
import com.example.commentsservice.query.rest.comment.FindCommentsByChapterIdQuery;
import com.example.commentsservice.query.rest.comment.FindCommentsByCommentIdQuery;
import com.example.commentsservice.query.rest.comment.FindCommentsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentQueryService {

    @Autowired
    private QueryGateway queryGateway;

    public CommentQueryService(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @RabbitListener(queues = "getCommentQueue")
    public List<CommentRestModel> getComment() {
        System.out.println("GET ALL COMMENTS");

        FindCommentsQuery findCommentsQuery = new FindCommentsQuery();
        return queryGateway.query(
                findCommentsQuery,
                ResponseTypes.multipleInstancesOf(CommentRestModel.class)
        ).join();
    }

    @RabbitListener(queues = "getCommentByIdQueue")
    public CommentRestModel getCommentById(String commentId) {
        System.out.println("GET COMMENT BY CommentID: " + commentId);

        FindCommentsByCommentIdQuery findCommentsByCommentIdQuery = new FindCommentsByCommentIdQuery(commentId);
        return queryGateway.query(
                findCommentsByCommentIdQuery,
                ResponseTypes.instanceOf(CommentRestModel.class)
        ).join();
    }


    @RabbitListener(queues = "getCommentByChapterIdQueue")
    public CommentRestModel getCommentByChapterId(String chapterId) {
        System.out.println("GET Comment By ChapterID: " + chapterId);

        FindCommentsByChapterIdQuery findCommentsByChapterIdQuery = new FindCommentsByChapterIdQuery(chapterId);
        return queryGateway.query(
                findCommentsByChapterIdQuery,
                ResponseTypes.instanceOf(CommentRestModel.class)
        ).join();
    }
}
