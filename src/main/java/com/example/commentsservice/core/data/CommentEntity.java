package com.example.commentsservice.core.data;

import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document("Comments")
public class CommentEntity implements Serializable {

    @Id
    @Column(unique = true)
    private String commentId;
    private String userId;
    private Date commentDate;
    private String commentDetail;
    private Integer reportCount;
//    private String chapterId;
//    private String bookId;

    public CommentEntity() {}

    public CommentEntity(String commentId, String userId, Date commentDate, String commentDetail, Integer reportCount) {
        this.commentId = commentId;
        this.userId = userId;
        this.commentDate = commentDate;
        this.commentDetail = commentDetail;
        this.reportCount = reportCount;
//        this.chapterId = chapterId;
//        this.bookId = bookId;

    }
}
