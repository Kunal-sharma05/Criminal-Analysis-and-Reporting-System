package Main;
 
import dao.CrimeAnalysisServiceImpl;
import dao.ICrimeAnalysisService;
import entity.Incident;
import entity.Report;
import c.myexceptions.IncidentNumberNotFoundException;
import java.sql.Date; 
import java.util.Collection;
import java.util.Scanner;
 
public class MainModule {
 
    public static void main(String[] args) {
        ICrimeAnalysisService crimeAnalysisService = new CrimeAnalysisServiceImpl();
        Scanner scanner = new Scanner(System.in);
 
        try {
            
            int choice;
            do {
            	System.out.println("====||C.A.R.S||===\n");
                System.out.println("1. Create Incident");
                System.out.println("2. Update Incident Status");
                System.out.println("3. Get Incidents in Date Range");
                System.out.println("4. Search Incidents");
                System.out.println("5. Generate Incident Report");
                System.out.println("6. Get Incident Details");
                System.out.println("7. Get All Incidents");
                System.out.println("8. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();  
 
                switch (choice) {
                
                case 1:
                    // Call createIncident method
                    {
                        int incidentID;
                        String incidentType;
                        Date incidentDate;
                        String location;
                        String description;
                        String status;
                        int victimID;
                        int suspectID;
 
                        System.out.print("Enter incident ID: ");
                        incidentID = scanner.nextInt();
 
                        System.out.print("Enter incident Type: ");
                        incidentType = scanner.next();
 
                        System.out.print("Enter incident Date (YYYY-MM-DD): ");
                        String dateString = scanner.next();
                        incidentDate = Date.valueOf(dateString);
 
                        System.out.print("Enter location: ");
                        location = scanner.next();
 
                        System.out.print("Enter description: ");
                        description = scanner.next();
 
                        System.out.print("Enter status: ");
                        status = scanner.next();
 
                        System.out.print("Enter victim ID: ");
                        victimID = scanner.nextInt();
 
                        System.out.print("Enter suspect ID: ");
                        suspectID = scanner.nextInt();
 
                        Incident newIncident = new Incident(incidentID, incidentType, incidentDate, location,
                                description, status, victimID, suspectID);
 
                        System.out.println("Incident value: " + newIncident);
                        boolean incidentCreated = crimeAnalysisService.createIncident(newIncident);
                        if (incidentCreated) {
                            System.out.println("Incident created successfully.");
                        } else {
                            System.out.println("Failed to create incident.");
                        }
                        break;
                    }
 
 
                case 2:
                    // Call updateIncidentStatus method
                    System.out.print("Enter incident ID: ");
                    int incidentId = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character
                    System.out.print("Enter new status: ");
                    String newStatus = scanner.nextLine();
                    boolean statusUpdated = crimeAnalysisService.updateIncidentStatus(newStatus, incidentId);
                    if (statusUpdated) {
                        System.out.println("Incident status updated successfully.");
                    } else {
                        System.out.println("Failed to update incident status.");
                    }
                    break;
 
 
                    case 3:
                        // Call getIncidentsInDateRange method
                        System.out.print("Enter start date (yyyy-MM-dd): ");
                        String startDateString = scanner.nextLine();
                        System.out.print("Enter end date (yyyy-MM-dd): ");
                        String endDateString = scanner.nextLine();
 
                        // Convert date strings to Date objects
                        Date startDate = java.sql.Date.valueOf(startDateString);
                        Date endDate = java.sql.Date.valueOf(endDateString);
 
                        Collection<Incident> incidentsInRange = crimeAnalysisService.getIncidentsInDateRange(startDate, endDate);
                        // Handle the collection as needed
                        for (Incident incident : incidentsInRange) {
                            System.out.println(incident);
                        }
                        break;
 
                    case 4:
                        // Call searchIncidents method
                        System.out.print("Enter incident type: ");
                        String incidentType = scanner.nextLine();
                        System.out.print("Enter status: ");
                        String status = scanner.nextLine();
 
                        Collection<Incident> searchResults = crimeAnalysisService.searchIncidents(incidentType, status);
                        for (Incident incident : searchResults) {
                            System.out.println(incident);
                        }
                        break;
 
                    case 5:
                        // Call generateIncidentReport method
                        System.out.print("Enter incident ID: ");
                        int reportIncidentId = scanner.nextInt();
                        scanner.nextLine();  
 
                        Incident reportIncident = crimeAnalysisService.getIncidentDetails(reportIncidentId);
                        if (reportIncident != null) {
                            Report incidentReport = crimeAnalysisService.generateIncidentReport(reportIncident);
                            System.out.println("Incident value: " +incidentReport);
                            
                        } else {
                            System.out.println("Incident not found.");
                        }
                        
                        
                        break;
 
                    case 6:
                        // Call getIncidentDetails method
                        System.out.print("Enter incident ID: ");
                        int detailsIncidentId = scanner.nextInt();
                        scanner.nextLine();  
 
                        try {
                            Incident incidentDetails = crimeAnalysisService.getIncidentDetails(detailsIncidentId);
                            
                                
                            System.out.println("Incident value: " +incidentDetails);
                            
                        } catch (IncidentNumberNotFoundException e) {
                            System.out.println("Incident Number Not Found: " + e.getMessage());
                        }
                        break;
 
                    //case 7:
                    	
 
                    
                    	
                    case 7:
                        // Call getAllIncidents method
                        Collection<Incident> allIncidents = crimeAnalysisService.getAllIncidents();
                        
                        // Handle the collection as needed
                        for (Incident incident : allIncidents) {
                            System.out.println(incident);
                        }
                        break;
 
                        
                        
 
                    case 8:
                        System.out.println("Exiting");
                        break;
 
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 9.");
                        break;
                }
 
            } while (choice != 9);
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
            scanner.close();
        }
    }
}

