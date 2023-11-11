package com.example.commentsservice.query;

import com.example.commentsservice.command.rest.CommentRestModel;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CommentQueryController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(value = "/getComment")
    public ArrayList getComment() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Object comment = rabbitTemplate.convertSendAndReceive("Direct", "getComment", "");
        return (ArrayList) comment;
    }

    @GetMapping(value = "/getComment/{commentId}")
    public CommentRestModel getCommentById(@PathVariable("commentId") String commentId) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Object comment = rabbitTemplate.convertSendAndReceive("Direct", "getCommentId", commentId);
        return (CommentRestModel) comment;
    }
}
