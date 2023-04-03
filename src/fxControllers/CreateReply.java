package fxControllers;

import Personel.Comment;
import hibernate.CommentHib;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.persistence.EntityManagerFactory;

public class CreateReply {
    public TextField titleField;
    public TextArea descriptionField;
    public CommentHib commentHib;
    public Comment selectedComment;
    public EntityManagerFactory entityManagerFactory;

    public void setData(EntityManagerFactory entityManagerFactory, Comment selectedComment) {
        this.entityManagerFactory = entityManagerFactory;
        this.commentHib = new CommentHib(entityManagerFactory);
        this.selectedComment = selectedComment;
    }
}
