package patient.registration.database;

import patient.registration.model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO implements AbstractDAO<Patient> {
    private final static Connection connection = ConnectionFactory.getConnection();

    public PatientDAO() {
        String createTable = "CREATE TABLE IF NOT EXISTS patients (" +
                "id_patient INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "given_name TEXT NOT NULL, " +
                "family_name TEXT NOT NULL, " +
                "note TEXT);";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTable);
        } catch (SQLException exception) {
            System.err.println(exception.toString());
        }
    }

    @Override
    public List<Patient> getAll() {
        String getAll = "SELECT id_patient, given_name, family_name, note FROM patients;";
        List<Patient> patients = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getAll)) {

            while (resultSet.next()) {
                Patient patient = new Patient(
                        resultSet.getInt("id_patient"),
                        resultSet.getString("given_name"),
                        resultSet.getString("family_name"),
                        resultSet.getString("note")
                );
                patients.add(patient);
            }
        } catch (SQLException exception) {
            System.err.println(exception.toString());
        }
        return patients;
    }

    @Override
    public Patient getOne(long id) {
        String getOne = "SELECT id_patient, given_name, family_name, note FROM patients WHERE id_patient = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(getOne)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Patient(
                        resultSet.getInt("id_patient"),
                        resultSet.getString("given_name"),
                        resultSet.getString("family_name"),
                        resultSet.getString("note")
                );
            }

            resultSet.close();
        } catch (SQLException exception) {
            System.err.println(exception.toString());
            return null;
        }
        return null;
    }

    @Override
    public int add(Patient source) {
        String insertData = "INSERT INTO patients(given_name, family_name, note) " +
                "VALUES(?, ?, ?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertData)) {
            preparedStatement.setString(1, source.getGivenName());
            preparedStatement.setString(2, source.getFamilyName());
            preparedStatement.setString(3, source.getNote());
            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println(exception.toString());
            return 0;
        }
    }

    @Override
    public int remove(long id) {
        String remove = "DELETE FROM patients WHERE patients.id_patient = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(remove)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println(exception.toString());
            return 0;
        }
    }

    @Override
    public int alter(Patient source) {
        String updateData = "UPDATE patients SET given_name = ?, family_name = ?, note = ? WHERE id_patient = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateData)) {
            preparedStatement.setString(1, source.getGivenName());
            preparedStatement.setString(2, source.getFamilyName());
            preparedStatement.setString(3, source.getNote());
            preparedStatement.setLong(4, source.getPatientId());
            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println(exception.toString());
            return 0;
        }
    }
}
