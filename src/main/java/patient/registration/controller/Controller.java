package patient.registration.controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import patient.registration.database.AbstractDAO;
import patient.registration.database.PatientDAO;
import patient.registration.database.SessionDAO;
import patient.registration.model.Patient;
import patient.registration.model.Session;

import java.util.stream.Collectors;

public class Controller {
    private static final AbstractDAO<Patient> patientDAO = new PatientDAO();
    private static final AbstractDAO<Session> sessionDAO = new SessionDAO();

    private final ObservableList<Patient> patientsData = FXCollections.observableArrayList();
    private final ObservableList<Session> sessionsData = FXCollections.observableArrayList();

    private final ObjectProperty<Patient> selectedPatient = new SimpleObjectProperty<>();
    private final ObjectProperty<Session> selectedSession = new SimpleObjectProperty<>();

    @FXML
    public TableView<Patient> tablePatients;

    @FXML
    public TableView<Session> tableSessions;

    @FXML
    public TableColumn<Patient, String> nameColumn;

    @FXML
    public TableColumn<Patient, String> surnameColumn;

    @FXML
    public TableColumn<Session, String> sessionIdColumn;

    @FXML
    public TableColumn<Session, String> sessionDateColumn;

    @FXML
    public TableColumn<Session, Integer> sessionDurationColumn;

    @FXML
    public TableColumn<Session, String> sessionEfficiencyColumn;

    @FXML
    private Button editPatient;

    @FXML
    private Button removePatient;

    @FXML
    private Button editSession;

    @FXML
    public void initialize() {
        patientsData.setAll(patientDAO.getAll());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("givenName"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("familyName"));
        tablePatients.setItems(patientsData);

        tablePatients.getSelectionModel().selectedItemProperty()
                .addListener((observableValue, patient, patientSelected) -> {
                    selectedPatient.setValue(patientSelected);
                    fillSessions();
                });

        fillSessions();
        sessionIdColumn.setCellValueFactory(new PropertyValueFactory<>("sessionIdColumn"));
        sessionDateColumn.setCellValueFactory(new PropertyValueFactory<>("sessionDateColumn"));
        sessionDurationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        sessionEfficiencyColumn.setCellValueFactory(new PropertyValueFactory<>("sessionEfficiencyColumn"));
        tableSessions.setItems(sessionsData);

        tableSessions.getSelectionModel().selectedItemProperty()
                .addListener((observableValue, session, sessionSelected) -> selectedSession.setValue(sessionSelected));

        editPatient.setDisable(true);
        removePatient.setDisable(true);
        editSession.setDisable(true);

        selectedPatient.addListener(patient -> {
            editPatient.setDisable(selectedPatient.get() == null);
            removePatient.setDisable(selectedPatient.get() == null);
        });


        selectedSession.addListener(session -> {
            editSession.setDisable(selectedSession.get() == null);
        });
    }

    @FXML
    private void closeApp(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void createPatient(ActionEvent event) {
        System.out.println("\t\t\t>>> CREATE PATIENT");
    }

    @FXML
    private void removePatient(ActionEvent event) {
        System.out.println("\t\t\tREMOVE " + selectedPatient.get());
    }

    @FXML
    private void editPatient(ActionEvent event) {
        System.out.println("\t\t\tEDIT " + selectedPatient.get());
    }

    @FXML
    private void createSession(ActionEvent event) {
        System.out.println("\t\t\t>>> CREATE SESSION");
    }

    @FXML
    private void editSession(ActionEvent event) {
        System.out.println("\t\t\tEDIT " + selectedSession.get());
    }

    private void fillSessions() {
        sessionsData.clear();
        if (selectedPatient.get() != null) {
            sessionsData.addAll(
                    sessionDAO.getAll()
                            .stream()
                            .filter(session -> session.getFkPatient() == selectedPatient.get().getPatientId())
                            .collect(Collectors.toList())
            );
        }
    }
}
