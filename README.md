# SplitYourBills

Spring Boot is used for the backend.

Database for development : MySQL <br/>
Database for production : Postgresql <br/>
Deployed in Heroku <br/>

Backend - SpringBoot , db- MySQL<br/>
Android - Java with Dagger 2


## Current features
 - Authentication and Authorization using JWT (JSON Web Token)
 - Create a space - A Space is where you can add transactions for a trip.
 - Create and edit Transactions - Transaction is the amount spent; it can be divided among everyone equally or manually type the amount.
 - Add/Invite members to join a space - You can invite people from your contacts;whether they have registered on the app or not. 
 - Once an invited user joins the app, he will be joining those invited spaces(to be implemented)
 - Can view the total amount owed/to be paid by all members in the space

## Proposed optimizations 
 - Improve the UI (Not in a good state as of now)
 - When invited users join the app, the invited spaces should show(he should join those spaces by default)
 - Give Edit/Update/Delete methods for all the transactions/spaces
 - Give edit/update  feature for profile details
 - Make app flow more production ready;navigate from one fragment to another while completing a job
 - Add multiple contacts at the same to a space(Currently adding one at a time)
 - Document the code
 - Write tests for the code


## Swagger API Documentation :  https://split-your-bills-prod.herokuapp.com/swagger-ui.html#/
## Database Credentials for development (No password is set)
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
- [x] Deploy the backend in cloud
- [ ] Deploy the app in the playstore
- [ ] Fix bugs (There will be many)
- [ ] Add FCM Notifications
- [ ] Add Privacy Policy and Terms Conditions
- [ ] Add more bill spliting functionalities
- [ ] Integrate Payments (UPI)
- [ ] Integrate oAuth (Google/Facebook)
- [ ] Add Email functionality
- [ ] Add Text Message functionality
