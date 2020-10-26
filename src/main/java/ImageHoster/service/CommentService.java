package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//This is an @Service class and used to write business logic pertaining to Comment feature in a different layer.
@Service
public class CommentService {

  private CommentRepository commentRepository;

  //CommentService being @Autowired, spring framework will instantiate and inject the object dependency implicitly for us
  //This is a constructor injection which is an cleaner and robust alternative to property injection
  @Autowired
  public CommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  // This method creates new comment for the current Image
  public void createNewComment(Comment comment) {
    commentRepository.createComment(comment);
  }

}
