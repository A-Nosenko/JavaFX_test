package patient.registration.controller;

import patient.registration.database.AbstractDAO;
import patient.registration.database.PatientDAO;
import patient.registration.database.SessionDAO;
import patient.registration.model.Patient;
import patient.registration.model.Session;

public class Controller {
    private static final AbstractDAO<Patient> patientDAO = new PatientDAO();
    private static final AbstractDAO<Session> session = new SessionDAO();



}
