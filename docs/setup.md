# Project Setup

## Web Client with Vite

The DSR FlightDeck web application is built using the Vite stack.

```
npm create vite@latest flightdeck -- --template react-ts
```
The application is ready to be implemented.

## Web Server with Spring Boot

The DSR FlightDeck application server is built using the Spring Boot 3 stack. 
The initial project was started with https://start.spring.io/ using the 
following settings:
* Project: Maven
* Language: Java
* Spring Boot: 3.3.5
* Group: com.desertskyrangers
* Artifact: flightdeck
* Name: FlightDeck
* Package: com.desertskyrangers.flightdeck
* Packaging: jar
* Configuration: YAML
* Java: 17

## DSR FlightDeck Database

The DSR FlightDeck production database uses MariaDB [https://mariadb.org] version 10
or higher.

### Initial Configuration
* Log in to MariaDB as the root user:
  ```
  sudo mariadb
  ```
* Create the DSR FlightDeck database:
  ```
  create database flightdeck default character set utf8 default collate utf8_general_ci;
  ```
* Create the perform user:
  ```
  grant all privileges on flightdeck.* to 'flightdeck'@'localhost' identified by '<password>' with grant option;
  ```

### Maintenance
* Backup the database
  ```shell
  sudo mysqldump flightdeck > Temp/flightdeck.sql
  ```
* Restore the database
  ```shell
  sudo mariadb flightdeck < Temp/flightdeck.sql
  ```