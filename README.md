# SplitYourBills- Backend
Backend - SpringBoot , db- MySQL<br/>
Android - Java with Dagger 2


## Current features
 - JWT Authentication/Authorization
 - Creation of space; add or invite people based on whether they have registered on the app
 - Adding Transaction to spaces; split manually or split equally.

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


# SplitYourBills- Android
This folder contains the Android application.
## Project Structure: 
  - base - Contains base classes, BaseApplication, BaseViewModel etc.
  - data - Everything related to the Database and network calls
    - models - The Request Body and Response for all network calls
    - pref - SharedPreference used to store JWT Authentication token. It contains all the methods related to shared preference
    - retrofit - Contains all the API calls for the app
    - AppDataManager - It combines both the shared preference and retrofit calls. ViewModels use this class to do network calls.
  - di - All the things related to Dagger 2 are stored here.
    - components
    - modules - Contains activity modules,retrofit modules etc.
    - scope - Custom Annotations
  - ui - All the UI related things are done here. Each folder contains one activity,one viewmodel and multiple fragments .
    - auth - Auth related stuff
    - main - All the app mainly resides here. All space,transactions and everything.
    - splash  - Just another splash activity, You know what it does

## Libraries used :
  - Dagger 2 (For dependency injection)
  - Retrofit 2 (For network calls)
  - RXJava 2 (Publisher-subscriber model in network calls)
  - Dexter (Easier permission)
  - Google Material Design
  - OkHttp Interceptor (Logging of network calls)

## Required Permissions : 

 - Internet
 - Read Contacts

## Tasks to be completed: 

- [x] Develop the basic backend 
- [x] Develop the basic android app 
- [ ] Improve the UI
- [ ] Deploy the backend in cloud
- [ ] Deploy the app in the playstore
- [ ] Fix bugs (There will be many)
- [ ] Add FCM Notifications
- [ ] Add Privacy Policy and Terms Conditions
- [ ] Add more bill spliting functionalities
- [ ] Integrate Payments (UPI)
- [ ] Integrate oAuth (Google/Facebook)
- [ ] Add Email functionality
- [ ] Add Text Message functionality
