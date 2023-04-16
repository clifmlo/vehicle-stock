# vehicle-stock
To track vehicle stock

#Technology Stack
Java 11,
Spring-boot 2.7.10
PostgreSQL
Maven
Docker
JUnit


#Getting started
- Clone the project
- Install docker
- Configure and run postgres database in docker 
- Create database called postgres to macth the connection properties in the properties file
- Run [mvn clean install] to build the project (make sure database is already running and application can connect for unit tests to pass during build)
- Run the application [mvn spring-boot:run]
- Open browser and open url http://localhost:8080/swagger-ui/
- Test Vehicles Controller in the swagger-ui o the browser
- If posting via postman or any separate tool you will be prompted for username and password
  - username: cliff
  - password: password



#Note
Exception handling is done at global level
