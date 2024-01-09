package dao;

import c.myexceptions.IncidentNumberNotFoundException;
import entity.Incident;
import entity.Report;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;
import java.util.List;
 
public class CrimeAnalysisServiceImpl implements ICrimeAnalysisService {
 
    private static Connection connection;
 
    public CrimeAnalysisServiceImpl() {
        connection = DBConnection.getConnection();
    }
 
    @Override
    public boolean createIncident(Incident incident) {
    
    	String query = "INSERT INTO Incidents (IncidentId,Incidentdate, Location, Description, Status, VictimID, SuspectID,Incidenttype) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, incident.getIncidentID());
            
            if (incident.getIncidentDate() != null) {
                preparedStatement.setDate(2, new java.sql.Date(incident.getIncidentDate().getTime()));
            } else {
                preparedStatement.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            }
 
            preparedStatement.setString(3, incident.getLocation());
            preparedStatement.setString(4, incident.getDescription());
            preparedStatement.setString(5, incident.getStatus());
            preparedStatement.setInt(6, incident.getVictimID());
            preparedStatement.setInt(7, incident.getSuspectID());
            preparedStatement.setString(8, incident.getIncidentType());
 
            int affectedRows = preparedStatement.executeUpdate();
 
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
 
 
    @Override
    public boolean updateIncidentStatus(String status, int incidentId) {
        String query = "UPDATE Incidents SET Status = ? WHERE IncidentID = ? ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, incidentId);
 
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error updating incident status: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            e.printStackTrace();
            return false;
        }
    }
 
 
 
    @Override
    public Collection<Incident> getIncidentsInDateRange(Date startDate, Date endDate) {
        List<Incident> incidents = new ArrayList<>();
        String query = "SELECT * FROM Incidents WHERE IncidentDate BETWEEN ? AND ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));
 
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Incident incident = new Incident();
                incident.setIncidentID(resultSet.getInt("IncidentID"));
                incident.setIncidentType(resultSet.getString("IncidentType"));
                incident.setIncidentDate(resultSet.getDate("IncidentDate"));
                incident.setLocation(resultSet.getString("Location"));
                incident.setDescription(resultSet.getString("Description"));
                incident.setStatus(resultSet.getString("Status"));
                incident.setVictimID(resultSet.getInt("VictimID"));
                incident.setSuspectID(resultSet.getInt("SuspectID"));
 
                incidents.add(incident);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incidents;
    }
 
    @Override
    public Collection<Incident> searchIncidents(String incidentType, String status) {
        List<Incident> incidents = new ArrayList<>();
        String query = "SELECT * FROM Incidents WHERE IncidentType = ? AND Status = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, incidentType);
            preparedStatement.setString(2, status);
 
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Incident incident = new Incident();
                incident.setIncidentID(resultSet.getInt("IncidentID"));
                incident.setIncidentType(resultSet.getString("IncidentType"));
                incident.setIncidentDate(resultSet.getDate("IncidentDate"));
                incident.setLocation(resultSet.getString("Location"));
                incident.setDescription(resultSet.getString("Description"));
                incident.setStatus(resultSet.getString("Status"));
                incident.setVictimID(resultSet.getInt("VictimID"));
                incident.setSuspectID(resultSet.getInt("SuspectID"));
 
                incidents.add(incident);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incidents;
    }
 
    @Override
    public Report generateIncidentReport(Incident incident) {
    	Report report = new Report();

        // Set basic information for the report
        report.setIncidentID(incident.getIncidentID());
        report.setReportingOfficer(1);
        report.setReportDate(incident.getIncidentDate());
        report.setReportDetails("Placeholder report details");
        report.setStatus("Pending");  // You can set an appropriate status
        
        return new Report();
    }
 
    @Override
    public Incident getIncidentDetails(int incidentId) throws IncidentNumberNotFoundException {
        String query = "SELECT * FROM Incidents WHERE IncidentID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, incidentId);
 
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Incident incident = new Incident();
                incident.setIncidentID(resultSet.getInt("IncidentID"));
                incident.setIncidentType(resultSet.getString("IncidentType"));
                incident.setIncidentDate(resultSet.getDate("IncidentDate"));
                incident.setLocation(resultSet.getString("Location"));
                incident.setDescription(resultSet.getString("Description"));
                incident.setStatus(resultSet.getString("Status"));
                incident.setVictimID(resultSet.getInt("VictimID"));
                incident.setSuspectID(resultSet.getInt("SuspectID"));
 
                return incident;
            } else {
                throw new IncidentNumberNotFoundException("Incident with ID " + incidentId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IncidentNumberNotFoundException("Error retrieving incident details.");
        }
    }
 
    @Override
    public boolean updateIncidentDetails(Incident incident) throws IncidentNumberNotFoundException {
        String query = "UPDATE Incidents SET IncidentType = ?, IncidentDate = ?, Location = ?, Description = ?, " +
                "Status = ?, VictimID = ?, SuspectID = ? WHERE IncidentID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, incident.getIncidentType());
            preparedStatement.setDate(2, new java.sql.Date(incident.getIncidentDate().getTime()));
            preparedStatement.setString(3, incident.getLocation());
            preparedStatement.setString(4, incident.getDescription());
            preparedStatement.setString(5, incident.getStatus());
            preparedStatement.setInt(6, incident.getVictimID());
            preparedStatement.setInt(7, incident.getSuspectID());
            preparedStatement.setInt(8, incident.getIncidentID());
 
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                return true;
            } else {
                throw new IncidentNumberNotFoundException("Incident with ID " + incident.getIncidentID() + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IncidentNumberNotFoundException("Error updating incident details.");
        }
    }
 
    @Override
    public List<Incident> getAllIncidents() {
        List<Incident> incidents = new ArrayList<>();
        String query = "SELECT * FROM Incidents";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Incident incident = new Incident();
                incident.setIncidentID(resultSet.getInt("IncidentID"));
                incident.setIncidentType(resultSet.getString("IncidentType"));
                incident.setIncidentDate(resultSet.getDate("IncidentDate"));
                incident.setLocation(resultSet.getString("Location"));
                incident.setDescription(resultSet.getString("Description"));
                incident.setStatus(resultSet.getString("Status"));
                incident.setVictimID(resultSet.getInt("VictimID"));
                incident.setSuspectID(resultSet.getInt("SuspectID"));
 
                incidents.add(incident);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incidents;
    }
}
