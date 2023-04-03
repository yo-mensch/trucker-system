package test;

import Personel.Comment;
import fxControllers.CreateReply;
import hibernate.CommentHib;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

public class CreateReplyTest {
    @Mock private EntityManagerFactory entityManagerFactory;
    @Mock private CommentHib commentHib;
    @Mock private Comment selectedComment;
    private CreateReply controllerUnderTest;

    @Before
    public void setUp() {
        // initialize JavaFX toolkit
        new JFXPanel();
        // initialize mocks
        MockitoAnnotations.initMocks(this);
        // create a new controller instance
        controllerUnderTest = new CreateReply();
        controllerUnderTest.titleField = new TextField();
        controllerUnderTest.descriptionField = new TextArea();
        // set up the controller
        controllerUnderTest.setData(entityManagerFactory, selectedComment);
        controllerUnderTest.commentHib = commentHib;
    }

    @Test
    public void testSetData() {
        // Verify the setup
        assertSame(entityManagerFactory, controllerUnderTest.entityManagerFactory);
        assertSame(selectedComment, controllerUnderTest.selectedComment);
        assertSame(commentHib, controllerUnderTest.commentHib);
    }

    @Test
    public void testCreateReply() {
        // Setup
        controllerUnderTest.titleField.setText("reply title");
        controllerUnderTest.descriptionField.setText("reply description");
        ArgumentCaptor<Comment> createdCommentCaptor = ArgumentCaptor.forClass(Comment.class);

        // Exercise
        controllerUnderTest.createReply();

        // Verify
        verify(commentHib).createComment(createdCommentCaptor.capture());
        Comment createdComment = createdCommentCaptor.getValue();
        assertNotNull(createdComment);
        assertEquals("reply title", createdComment.getTitle());
        assertSame(selectedComment, createdComment.getParentComment());
    }
}
