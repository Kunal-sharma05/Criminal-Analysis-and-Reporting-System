create database CARS;
use CARS;

CREATE TABLE Victims (
    VictimID INT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    DateOfBirth DATE,
    Gender VARCHAR(10),
    ContactInformation VARCHAR(255)
);
 
CREATE TABLE Suspects (
    SuspectID INT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    DateOfBirth DATE,
    Gender VARCHAR(10),
    ContactInformation VARCHAR(255)
);
 
CREATE TABLE LawEnforcementAgencies (
    AgencyID INT PRIMARY KEY,
    AgencyName VARCHAR(100),
    Jurisdiction VARCHAR(100),
    ContactInformation VARCHAR(255)
);

 
CREATE TABLE Officers (
    OfficerID INT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    BadgeNumber VARCHAR(20),
    OfficerRank VARCHAR(20),
    ContactInformation VARCHAR(255),
    AgencyID INT,
    FOREIGN KEY (AgencyID) REFERENCES LawEnforcementAgencies(AgencyID)
);
 
CREATE TABLE Incidents (
    IncidentID INT PRIMARY KEY,
    IncidentType VARCHAR(50),
    IncidentDate DATE,
    LocationLatitude DECIMAL(9,6),
    LocationLongitude DECIMAL(9,6),
    Description TEXT,
    Status VARCHAR(20),
    VictimID INT,
    SuspectID INT,
    AgencyID INT,
    FOREIGN KEY (VictimID) REFERENCES Victims(VictimID),
    FOREIGN KEY (SuspectID) REFERENCES Suspects(SuspectID),
    FOREIGN KEY (AgencyID) REFERENCES LawEnforcementAgencies(AgencyID)
);
 
CREATE TABLE Evidence (
    EvidenceID INT PRIMARY KEY,
    Description TEXT,
    LocationFound VARCHAR(255),
    IncidentID INT,
    FOREIGN KEY (IncidentID) REFERENCES Incidents(IncidentID)
);
 
CREATE TABLE Reports (
    ReportID INT PRIMARY KEY,
    IncidentID INT,
    ReportingOfficer INT,
    ReportDate DATE,
    ReportDetails TEXT,
    Status VARCHAR(20),
    FOREIGN KEY (IncidentID) REFERENCES Incidents(IncidentID),
    FOREIGN KEY (ReportingOfficer) REFERENCES Officers(OfficerID)
);

-- Insert dummy data into Victims table
INSERT INTO Victims (VictimID, FirstName, LastName, DateOfBirth, Gender, ContactInformation)
VALUES
    (1, 'Amit', 'Sharma', '1990-05-15', 'Male', '123-456-7890'),
    (2, 'Priya', 'Patel', '1985-08-22', 'Female', '987-654-3210'),
    (3, 'Raj', 'Verma', '1992-11-10', 'Male', '345-678-9012');

-- Insert dummy data into Suspects table
INSERT INTO Suspects (SuspectID, FirstName, LastName, DateOfBirth, Gender, ContactInformation)
VALUES
    (1, 'Sneha', 'Singh', '1988-04-18', 'Female', '555-123-7890'),
    (2, 'Vikram', 'Mehta', '1995-09-07', 'Male', '777-999-3333'),
    (3, 'Neha', 'Kapoor', '1990-12-30', 'Female', '444-888-2222');

-- Insert dummy data into LawEnforcementAgencies table
INSERT INTO LawEnforcementAgencies (AgencyID, AgencyName, Jurisdiction, ContactInformation)
VALUES
    (1, 'City Police Department', 'City A', '111-222-3333'),
    (2, 'County Sheriff Office', 'County X', '444-555-6666'),
    (3, 'State Bureau of Investigation', 'State Y', '777-888-9999');

-- Insert dummy data into Officers table
INSERT INTO Officers (OfficerID, FirstName, LastName, BadgeNumber, OfficerRank, ContactInformation, AgencyID)
VALUES
    (1, 'Rahul', 'Kumar', '12345', 'Sergeant', '999-111-2222', 1),
    (2, 'Anita', 'Gupta', '67890', 'Detective', '333-444-5555', 2),
    (3, 'Rajesh', 'Singh', '54321', 'Lieutenant', '666-777-8888', 3);


    


