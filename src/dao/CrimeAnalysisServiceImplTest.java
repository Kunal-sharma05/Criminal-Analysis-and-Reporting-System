package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.sql.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import c.myexceptions.IncidentNumberNotFoundException;

import entity.Incident;

class CrimeAnalysisServiceImplTest {
	static CrimeAnalysisServiceImpl cr;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	cr=new CrimeAnalysisServiceImpl();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		cr=null;
	}

	@Test
	void testCrimeAnalysisServiceImpl() {
		CrimeAnalysisServiceImpl crimeAnalysisService = new CrimeAnalysisServiceImpl();

        // Assert
        assertNotNull(crimeAnalysisService, "CrimeAnalysisServiceImpl instance should not be null");
	}

	@Test
	void testCreateIncident() {
		//Date d = new Date(2002-02-01);
		Incident i=new Incident(6,"Roberry",Date.valueOf("2003-02-01"),"Assam","Eye","Open",1,1);
		boolean result=cr.createIncident(i);
		assertTrue(result, "Incident creation should be successful");

	}

	@Test
	void testUpdateIncidentStatus() {
		  boolean result = cr.updateIncidentStatus("Open", 1);
	       assertTrue(result, "Incident status should be updated successfully");
	}

	@Test
	void testGetIncidentsInDateRange() {
		
		Date startDate = Date.valueOf("2002-02-01");/* your start date */;
        Date endDate = Date.valueOf("2002-03-01");/* your end date */;

        // Act
        Collection<Incident> incidentsInRange = cr.getIncidentsInDateRange(startDate, endDate);

        // Assert
        assertNotNull(incidentsInRange, "Collection should not be null");
        assertTrue(incidentsInRange.isEmpty(), "Collection should not be empty");
	}
    
	@Test
	void testSearchIncidents() {
		Incident testIncident = new Incident(2, "Robbery", Date.valueOf("2002-05-01"), "Test Location", "Test Description", "Open", 1, 2);
        cr.createIncident(testIncident);
		Collection<Incident> result = cr.searchIncidents("Robbery", "Open");
		 assertNotNull(result, "Result should not be null");
		 assertEquals(1, result.size(), "Result size should be 1");
	}
    @Test
    void testGetIncidentDetails() {
        
        Incident testIncident = new Incident(3, "Test Incident", Date.valueOf("2002-05-01"), "Test Location", "Test Description", "Open", 1, 2);

        cr.createIncident(testIncident);

        try {
           
            Incident retrievedIncident = cr.getIncidentDetails(testIncident.getIncidentID());

            assertNotNull(retrievedIncident, "Incident details should not be null");
            assertEquals(testIncident.getIncidentID(), retrievedIncident.getIncidentID(), "Incident ID should match");
            assertEquals(testIncident.getIncidentType(), retrievedIncident.getIncidentType(), "Incident type should match");
            // Add additional assertions for other incident properties

        } catch (IncidentNumberNotFoundException e) {
            fail("Exception not expected: " + e.getMessage());
        }
    }
    @Test
    void testUpdateIncidentDetails() {
        Incident testIncident = new Incident(4, "Test Incident", Date.valueOf("2002-01-02"), "Test Location", "Test Description", "Open", 1, 2);
        cr.createIncident(testIncident);

        
        testIncident.setIncidentType("Updated Incident");
        testIncident.setDescription("Updated Description");

        boolean result;
		try {
			result = cr.updateIncidentDetails(testIncident);
			assertTrue(result, "Incident details should be updated successfully");
		} catch (IncidentNumberNotFoundException e) {
			e.printStackTrace();
		}

       
        
        
    }
    @Test
    void testGetAllIncidents() {
       
        List<Incident> allIncidents = cr.getAllIncidents();

        assertNotNull(allIncidents, "All incidents should not be null");
        assertEquals(5, allIncidents.size(), "There should be 5 incidents");
   
    }
    


}
