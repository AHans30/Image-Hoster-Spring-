package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@Controller
public class CommentController {

  @Autowired
  private CommentService commentService;

  @Autowired
  private ImageService imageService;


  // This method is called when the user submits the comment form.
  // It add the user and image fields and then sends it off the service layer for persistence.
  @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
  public String createComment(@PathVariable("imageTitle") String title,
      @PathVariable("imageId") Integer id, @RequestParam("comment") String comment,
      Comment newComment, HttpSession session, Model model) throws IOException {
    //Extract user from Http session
    User user = (User) session.getAttribute("loggeduser");

    //Set state of comment
    newComment.setUser(user);
    newComment.setText(comment);
    newComment.setCreatedDate(LocalDate.now());
    newComment.setImage(imageService.getImage(id));

    //Apply business logic and save comment in database through service + repository class
    commentService.createNewComment(newComment);

    model.addAttribute("id", id);
    model.addAttribute("title", title);

    return "redirect:/images/{imageId}/{imageTitle}";
  }
}
