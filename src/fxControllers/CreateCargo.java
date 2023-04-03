package fxControllers;

import Personel.Cargo;
import Personel.CheckPoint;
import hibernate.CargoHib;
import hibernate.CheckPointHib;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;

public class CreateCargo {
    public Button createButton;
    public TextField companyField;
    public TextField productField;
    public TextField weightField;
    public CheckBox expirationCheck;
    private CargoHib cargoHib;
    private EntityManagerFactory entityManagerFactory;

    public void setData(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.cargoHib = new CargoHib(entityManagerFactory);
    }

    public void createCargo() throws IOException {
        Cargo cargo = null;
        cargo = new Cargo(companyField.getText(), productField.getText(), Integer.parseInt(weightField.getText()), expirationCheck.isSelected());
        cargoHib.createCargo(cargo);
    }
}
