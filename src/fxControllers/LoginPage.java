package fxControllers;

import Personel.Manager;
import Personel.Trucker;
import hibernate.ManagerHib;
import hibernate.TruckerHib;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.FxUtils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class LoginPage {
    @FXML
    public TextField emailField;
    @FXML
    public PasswordField passwordField;
    public CheckBox managerCheck;
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TruckSystem");
    ManagerHib managerHib = new ManagerHib(entityManagerFactory);
    TruckerHib truckerHib = new TruckerHib(entityManagerFactory);

    public void login() throws IOException {
        if (managerCheck.isSelected()) {
            Manager manager = managerHib.getManagerByLoginData(emailField.getText(), passwordField.getText());
            if (manager != null) {
                FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("../fxml/front-page.fxml"));
                Parent parent = fxmlLoader.load();
                FrontPage frontPage = fxmlLoader.getController();
                frontPage.setDataManager(entityManagerFactory, manager, manager);

                Scene scene = new Scene(parent);
                Stage stage = (Stage) passwordField.getScene().getWindow();
                stage.setTitle("Front page");
                stage.setScene(scene);
                stage.show();
            } else {
                FxUtils.generateAlert(Alert.AlertType.INFORMATION, "User login report", "No such user or wrong credentials");
            }
        }
    }

    public void signUp() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("../fxml/sign-up-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) passwordField.getScene().getWindow();
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
        SignUpPage signUpPage = fxmlLoader.getController();
        signUpPage.setData(entityManagerFactory);
    }
}
