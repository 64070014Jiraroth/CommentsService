package com.example.commentsservice.core.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<CommentEntity, String> {

    @Query(value = "{ 'commentId' : ?0 }")
    public CommentEntity findCommentEntityByCommentId(String commentId);

    @Query(value = "{ 'chapterId' : ?0 }")
    public CommentEntity findCommentEntityByChapterId(String chapterId);
}
