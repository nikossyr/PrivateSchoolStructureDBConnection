# Private School Command Prompt Application (Part B)

This project provides a privates school command prompt interface that gives the ability to the user to manipulate several
private school entities (courses, students, trainers and assignments) as well as the relations between them. This 
implementation aims to demonstrate object-oriented programming skills as well as manipulation of tables and relationships
of a connected Relational Database as a data persistence tool.

## Getting Started

In order to run the project you can use any of the wildly used IDEs (Netbeans, IntelliJ, Ecliple) to compile and run.
The project is written in Java 8. You will also need to import the Connector/J for MySQL Jar Library 
(https://dev.mysql.com/downloads/connector/j/). The application works with MySQL Databases.
 

#### Prerequisites
* JRE v8
* JDK v8
* Database with Tables and Relations for school entities. A MySQL script is provided in 
/MySQL-DB-scripts/private_school_DB_create_populate.sql that creates the tables and relationships and fills the database 
with mock data
* The database connection credentials should be set on the top of file src/Utils/DBUtils.java 
* JDBC Connector/J MySQL Added as a Jar Library (https://dev.mysql.com/downloads/connector/j/)
* Recommended: IDE (Netbeans, IntelliJ, Ecliple)
* The database connection credentials should be set on the top of file src/Utils/DBUtils.java 

## Runtime Examples 

![Running Application Screenshot 1](/img/ps1.jpg)
![Running Application Screenshot 2](/img/ps2.jpg)
![Running Application Screenshot 3](/img/ps3.jpg)
![Running Application Screenshot 4](/img/ps4.jpg)
