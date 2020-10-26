package ImageHoster.model;

import java.time.LocalDate;
import javax.persistence.*;

//@Entity annotation specifies that the corresponding class is a JPA entity
@Entity
//@Table annotation provides more options to customize the mapping.
//Here the name of the table to be created in the database is explicitly mentioned as 'comments'.
//Hence the table named 'comments' will be created in the database with all the columns mapped to all the attributes in 'Comment' class
@Table(name = "comments")
public class Comment {

  //@Id annotation specifies that the corresponding attribute is a primary key
  @Id
  //@GenerationValue with 'strategy' = 'GenerationType.AUTO' will help to auto-generate unique primary key values
  @GeneratedValue(strategy = GenerationType.AUTO)
  //@Column annotation specifies that the attribute will be mapped to the column in the database.
  //Here the column name is explicitly mentioned as 'id'
  @Column(name = "id")
  private Integer id;

  @Column(name = "text")
  private String text;

  @Column(name = "creation_date")
  private LocalDate createdDate;

  //The 'comments' table is mapped to 'users' table with Many:One mapping
  //One comment can have only one user but one user can have multiple comments
  //FetchType is EAGER - to load user object when a comment object is loaded
  @ManyToOne(fetch = FetchType.EAGER)
  //Below annotation indicates that the name of the column in 'comments' table referring the primary key in 'users' table will be 'user_id'
  @JoinColumn(name = "user_id")
  private User user;

  //The 'comments' table is mapped to 'images' table with Many:One mapping
  //One comment can have only one image but one image can have multiple comments
  //FetchType is EAGER - to load image object when a comment object is loaded
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "image_id")
  private Image image;

  //Getters and setters for Comment attributes
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }
}
