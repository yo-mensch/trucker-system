package fxControllers;

import Personel.Destination;
import Personel.Trucker;
import hibernate.DestinationHib;
import hibernate.TruckerHib;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;

public class CreateDestination {
    public Button createButton;
    public TextField startField;
    public TextField destinationField;
    public TextField distanceField;
    public DatePicker arrivalDate;
    public DatePicker departureDate;
    private DestinationHib destinationHib;
    private EntityManagerFactory entityManagerFactory;

    public void setData(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.destinationHib = new DestinationHib(entityManagerFactory);
    }

    public void createDestination() throws IOException {
        Destination destination = null;
        destination = new Destination(startField.getText(), destinationField.getText(), Integer.parseInt(distanceField.getText()), departureDate.getValue(), arrivalDate.getValue());
        destinationHib.createDestination(destination);
    }
}
