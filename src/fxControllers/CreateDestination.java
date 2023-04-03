package fxControllers;

import Personel.Destination;
import Personel.Manager;
import Personel.Status;
import Personel.Trucker;
import hibernate.DestinationHib;
import hibernate.ManagerHib;
import hibernate.TruckerHib;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CreateDestination implements Initializable {
    public TextField distanceField;
    public TextField startPointField;
    public TextField endPointField;
    public DatePicker departureField;
    public DatePicker arrivalField;
    public ChoiceBox statusField;
    public ComboBox driverCB;
    @FXML
    public EntityManagerFactory entityManagerFactory;
    @FXML
    public DestinationHib destinationHib;
    public TruckerHib userHib;
    public List<Trucker> truckerList;
    public Manager user;

    public void setData(EntityManagerFactory entityManagerFactory, Manager user) {
        this.entityManagerFactory = entityManagerFactory;
        this.destinationHib = new DestinationHib(entityManagerFactory);
        this.user = user;
        this.userHib = new TruckerHib(entityManagerFactory);
        truckerList = userHib.getAllTrucker();
        driverCB.getItems().setAll(truckerList);

    }

    public void submit() {
        Destination destination = null;
        destination = new Destination(Double.valueOf(distanceField.getText()), startPointField.getText(), endPointField.getText(), departureField.getValue(), arrivalField.getValue(), Status.valueOf(statusField.getSelectionModel().getSelectedItem().toString()), (Trucker) driverCB.getValue(), (Manager) user);
        destinationHib.createDestination(destination);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusField.getItems().add(Status.AVAILABLE.toString());
        statusField.getItems().add(Status.INPROGRESS.toString());
        statusField.getItems().add(Status.FINISHED.toString());
    }

}
