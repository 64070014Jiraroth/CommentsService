package com.example.commentsservice.command.rest;

import com.example.commentsservice.command.rest.CommentRestModel;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentCommandController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping(value = "/addComment")
    public String addComment(@RequestBody CommentRestModel model) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        rabbitTemplate.convertAndSend("Direct", "addComment", model);
        return "Added Comment";
    }

    @PutMapping(value = "/updateComment")
    public String updateComment(@RequestBody CommentRestModel model) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        rabbitTemplate.convertAndSend("Direct", "updateComment", model);
        return "Updated Comment ID : " + model.getCommentId();
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public String deleteComment(@PathVariable("commentId") String commentId) {
        rabbitTemplate.convertAndSend("Direct", "deleteComment", commentId);
        return "Deleted Comment ID : " + commentId;
    }
}
