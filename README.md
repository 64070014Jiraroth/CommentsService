# CommentsService2

# RabbitMQ
#### Add Comment
 - Queues: addCommentQueue
 - Exchanges: Direct
 - Routing key: addComment
#### Update Comment
 - Queues: updateCommentQueue
 - Exchanges: Direct
 - Routing key: updateComment
#### Delete Comment
 - Queues: deleteCommentQueue
 - Exchanges: Direct
 - Routing key: deleteComment
#### Get Comment
 - Queues: getCommentQueue
 - Exchanges: Direct
 - Routing key: getComment
#### Get Comment By Id
 - Queues: getCommentByIdQueue
 - Exchanges: Direct
 - Routing key: getCommentId
#### Get Comment By ChapterId
 - Queues: getCommentByChapterIdQueue
 - Exchanges: Direct
 - Routing key: getChapterId


# Path
- Add Comment (POST)    : http://localhost:8082/comment-service/addComment
- Get Comment (GET)     : http://localhost:8082/comment-service/getComment
- Get CommentById (GET) : http://localhost:8082/comment-service/getComment/(commentId)
- Get CommentByChapterId (in process)
- Update Comment (PUT)  : http://localhost:8082/comment-service/updateComment
- Delete Comment (DEL)  : http://localhost:8082/comment-service/deleteComment/(commentId)


# ETC.
### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.5/maven-plugin/reference/html/#build-image)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/3.1.5/reference/htmlsingle/index.html#data.nosql.mongodb)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.5/reference/htmlsingle/index.html#web)
* [Spring for RabbitMQ](https://docs.spring.io/spring-boot/docs/3.1.5/reference/htmlsingle/index.html#messaging.amqp)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/)

