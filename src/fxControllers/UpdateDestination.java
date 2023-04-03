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

public class UpdateDestination {
    public Button updateButton;
    public TextField startField;
    public TextField destinationField;
    public TextField distanceField;
    public DatePicker arrivalDate;
    public DatePicker departureDate;
    private DestinationHib destinationHib;
    private Destination selectedDestination;
    private EntityManagerFactory entityManagerFactory;

    public void setData(EntityManagerFactory entityManagerFactory, Destination selectedDestination) {
        this.entityManagerFactory = entityManagerFactory;
        this.selectedDestination = selectedDestination;
        this.destinationHib = new DestinationHib(entityManagerFactory);
        fillFields();
    }

    private void fillFields() {
        Destination destination = (Destination) destinationHib.getDestinationById(selectedDestination.getId());
        startField.setText(destination.getStart());
        destinationField.setText(destination.getDestination());
        distanceField.setText(String.valueOf(destination.getDistance()));
        departureDate.setValue(destination.getDepartureDate());
        arrivalDate.setValue(destination.getArrivalDate());
    }

    public void updateDestination() throws IOException {
        Destination destination = (Destination) destinationHib.getDestinationById(selectedDestination.getId());
        destination.setStart(startField.getText());
        destination.setDestination(destinationField.getText());
        destination.setDistance(Integer.parseInt(distanceField.getText()));
        destination.setDepartureDate(departureDate.getValue());
        destination.setArrivalDate(arrivalDate.getValue());
        destinationHib.updateDestination(destination);
    }
}
