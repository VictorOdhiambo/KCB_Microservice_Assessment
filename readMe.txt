The Program is developed using Java Spring Boot.
The IDE used during development is STS.
Database: MySQL

-- Running the Program
---- DB Installation ----

1. Install XAMPP
2. Navigate to PYPMYADMIN Panel
3. Import the sql file under db folder

---- Running the App ----
1. Open the App in STS IDE
2. Right Click on the App
3. In the drop down, click run as -> Java Spring Boot.

--- OR ----
CD into the app root folder and:
$ ./gradlew bootRun --args='--spring.profiles.active=dev'

-- Accessing the APP
Use Postman or Insomnia to access the End-points
e.g GET:  http://localhost:8080/api/customer/all -- retrieves all customers