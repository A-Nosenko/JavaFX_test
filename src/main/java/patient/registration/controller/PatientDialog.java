package patient.registration.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import patient.registration.model.Patient;

import java.util.Optional;

public class PatientDialog extends Application {

    @Override
    public void start(Stage primaryStage) {
        Dialog<Patient> dialog = new Dialog<>();
        dialog.setTitle("Patient");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField givenNameTextField = new TextField("Given name:");
        TextField familyNameTextField = new TextField("Family name:");
        TextField noteTextField = new TextField("Note:");
        Platform.runLater(givenNameTextField::requestFocus);
        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                return new Patient(0, givenNameTextField.getText(), familyNameTextField.getText(), noteTextField.getText());
            }
            return null;
        });
        Optional<Patient> optionalResult = dialog.showAndWait();
        optionalResult.ifPresent((Patient patient) -> {
            System.out.println(patient);
        });
    }
}

