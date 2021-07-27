package patient.registration.database;

import patient.registration.model.Session;
import patient.registration.model.SessionType;
import patient.registration.utils.DateUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionDAO implements AbstractDAO<Session> {
    private final static Connection connection = ConnectionFactory.getConnection();

    public SessionDAO() {
        String createTable = "CREATE TABLE IF NOT EXISTS sessions (" +
                "id_session INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "session_type TEXT NOT NULL, " +
                "start_date TEXT NOT NULL, " +
                "duration INTEGER NOT NULL, " +
                "efficiency REAL NOT NULL, " +
                "id_patient INTEGER, " +
                "CONSTRAINT fk_patient FOREIGN KEY (id_patient) " +
                "REFERENCES patients (id_patient) ON DELETE CASCADE" +
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTable);
        } catch (SQLException exception) {
            System.err.println(exception.toString());
        }
    }

    @Override
    public List<Session> getAll() {
        String getAll = "SELECT id_session, id_patient, session_type, start_date, duration, efficiency FROM sessions;";
        List<Session> sessions = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getAll)) {

            while (resultSet.next()) {
                Session session = new Session(
                        resultSet.getInt("id_session"),
                        resultSet.getInt("id_patient"),
                        SessionType.valueOf(resultSet.getString("session_type")),
                        DateUtils.convertTextToDate(resultSet.getString("start_date")),
                        resultSet.getInt("duration"),
                        resultSet.getDouble("efficiency")
                );
                sessions.add(session);
            }
        } catch (SQLException exception) {
            System.err.println(exception.toString());
        }
        return sessions;
    }

    @Override
    public Session getOne(long id) {
        String getOne = "SELECT id_session, id_patient, session_type, start_date, duration, efficiency FROM sessions " +
                "WHERE id_session = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(getOne)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Session(
                        resultSet.getInt("id_session"),
                        resultSet.getInt("id_patient"),
                        SessionType.valueOf(resultSet.getString("session_type")),
                        DateUtils.convertTextToDate(resultSet.getString("start_date")),
                        resultSet.getInt("duration"),
                        resultSet.getDouble("efficiency")
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
    public int add(Session source) {
        String insertData = "INSERT INTO sessions(id_patient, session_type, start_date, duration, efficiency) " +
                "VALUES(?, ?, ?, ?, ?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertData)) {
            preparedStatement.setLong(1, source.getFkPatient());
            preparedStatement.setString(2, source.getSessionType().name());
            preparedStatement.setString(3, DateUtils.convertDateToText(source.getStartDate()));
            preparedStatement.setInt(4, source.getDuration());
            preparedStatement.setDouble(5, source.getEfficiency());
            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println(exception.toString());
            return 0;
        }
    }

    @Override
    public int remove(long id) {
        String remove = "DELETE FROM sessions WHERE sessions.id_session = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(remove)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println(exception.toString());
            return 0;
        }
    }

    @Override
    public int alter(Session source) {
        String updateData = "UPDATE sessions SET id_patient = ?, session_type = ?, start_date = ?, duration = ?, efficiency = ? " +
                "WHERE id_session = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateData)) {
            preparedStatement.setLong(1, source.getFkPatient());
            preparedStatement.setString(2, source.getSessionType().name());
            preparedStatement.setString(3, DateUtils.convertDateToText(source.getStartDate()));
            preparedStatement.setInt(4, source.getDuration());
            preparedStatement.setDouble(5, source.getEfficiency());
            preparedStatement.setLong(6, source.getSessionId());
            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println(exception.toString());
            return 0;
        }
    }
}
