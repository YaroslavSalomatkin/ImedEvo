package imedevo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "article")
    private String article;
    @Column(name = "article_name")
    private String articleName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "picture")
    private String picture;
    @Column(name = "date")
    private LocalDateTime theDate;


    public Blog(){
        this.theDate = LocalDateTime.now();

    }

    public Blog(String article, String articleName, String firstName, String lastName, String picture) {
        this.article = article;
        this.articleName = articleName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.theDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", article='" + article + '\'' +
                ", articleName='" + articleName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", picture='" + picture + '\'' +
                ", theDate=" + theDate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public LocalDateTime  getTheDate() {
        return theDate;
    }

    public void setTheDate(LocalDateTime  theDate) {
        this.theDate = theDate;
    }
}
