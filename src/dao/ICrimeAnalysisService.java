package dao;

import c.myexceptions.IncidentNumberNotFoundException;
import entity.Incident;
import entity.Report;
import java.util.Collection;
import java.sql.Date;
import java.util.List;


public interface ICrimeAnalysisService {
	boolean createIncident(Incident incident);

	boolean updateIncidentStatus(String status, int incidentId);

	Collection<Incident> getIncidentsInDateRange(Date startDate, Date endDate);

	Collection<Incident> searchIncidents(String incidentType, String status);

	Report generateIncidentReport(Incident incident);

	Incident getIncidentDetails(int incidentId) throws IncidentNumberNotFoundException;

	boolean updateIncidentDetails(Incident incident) throws IncidentNumberNotFoundException;

	List<Incident> getAllIncidents();


}
