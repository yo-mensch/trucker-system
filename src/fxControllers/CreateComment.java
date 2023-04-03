package fxControllers;

import Personel.Comment;
import Personel.Forum;
import hibernate.CommentHib;
import hibernate.ForumHib;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.persistence.EntityManagerFactory;

public class CreateComment {
    public TextField titleField;
    public TextArea descriptionField;
    private CommentHib commentHib;
    private Forum selectedForum;
    private ForumHib forumHib;
    private EntityManagerFactory entityManagerFactory;

    public void setData(EntityManagerFactory entityManagerFactory, Forum selectedForum) {
        this.entityManagerFactory = entityManagerFactory;
        this.commentHib = new CommentHib(entityManagerFactory);
        this.forumHib = new ForumHib(entityManagerFactory);
        this.selectedForum = selectedForum;
    }
}
