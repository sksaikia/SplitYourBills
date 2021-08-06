# SplitYourBills- Backend
This folder contains the backend code written in Java. The backend is running the localhost 8080.
Database used : MySQL

## Swagger API Documentation :  http://localhost:8080/swagger-ui.html#/
## Database Credentials (No password is set)
> username : root

## Project Structure: 
  - config - Contains security and swagger configuration files
  - controller - Contains all the api end-points for the database
  - dto - Encapsulation of data transfer
  - exception - Contains Custom Exceptions created by the app
  - model - These are the database tables
  - payload - All the basic Request Body and Response for Authentication and Authorization of user.
  - repository - JPARepository implementing interfaces for database calls
  - security - Contains all the JWT Authentication/Authorization related files
  - service - Handles all the business logic for the app
  - SplitYourBillsApplication - Run this file to start the server

## Libraries : 

# SplitYourBills- Android
This folder contains the Android application.
## Libraries used :
  - Dagger 2 (For dependency injection)
  - Retrofit 2 (For network calls)
  - RXJava 2 (Publisher-subscriber model in network calls)
  - Dexter (Easier permission)
  - Google Material Design
  - OkHttp Interceptor (Logging of network calls)
