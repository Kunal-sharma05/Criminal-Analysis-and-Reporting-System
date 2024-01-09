# Criminal-Analysis-and-Reporting-System
Criminal Analysis and Reporting System

Welcome to the Criminal Analysis and Reporting System GitHub repository! This project, designed with an acute understanding of industry norms, offers a comprehensive solution for efficient criminal data management.

Entity Package:
Includes pivotal entity classes - Evidence.java, Incident.java, LawEnforcementAgency.java, Officer.java, Report.java, and Suspect.java. Each class encapsulates essential information, forming the backbone of the system.

Utility Package:
The DBConnection.java and PropertyUtil.java files in the utility package employ JDBC connectivity to seamlessly link the project with a MySQL database. The recommended property file method ensures secure storage and retrieval of sensitive information like usernames, passwords, and URLs.

DAO Package:
In the DAO package, the ICrimeAnalysisService.java interface guarantees data security. The CrimeAnalysisServiceImplement.java file implements this interface and handles database connectivity by calling the DBConnection class in its constructor. This approach promotes encapsulation and modular design.

C.myexception Package:
A dedicated package, C.myexception, hosts a custom exception - IncidentNumberNotFoundException. This exception enhances the system's robustness, allowing specific handling where an incident number is not found.

Main Package:
The central module, MainModule.java, features a menu-driven program. This module serves as the user interface, providing seamless access to the project's functionalities, demonstrating a mastery of user interaction design.

Testing:
Ensuring reliability and functionality, the project incorporates a JUnit Jupiter testing class. This rigorous testing methodology covers all components, validating the system's robustness and adherence to industry standards.

Explore the repository to witness a meticulously crafted Criminal Analysis and Reporting System. The well-structured design, adherence to industry best practices, and rigorous testing collectively contribute to a powerful solution for streamlined criminal data management. Engage, contribute, and experience the capabilities of this fully-tested system.
