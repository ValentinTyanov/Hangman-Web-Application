# Hangman Web Application

## Introduction
This Hangman Web Application is a modern take on the classic word guessing game. Designed as a full-stack application, it demonstrates the integration of Spring Boot for backend services, ReactJS for a dynamic frontend, MySQL for database management, and JSPs for server-side rendering.

## Technologies Used
- **Spring Boot**: Simplifies the development of new Spring applications through various helpful defaults.
- **Spring Data & Spring Web**: Facilitates the creation of RESTful services with comprehensive data handling.
- **Apache Shiro**: Utilized for both UI and BASIC Authentication for RESTful Web Services.
- **ReactJS**: Manages the stateful frontend presentation.
- **JSP**: Used for server-side rendering of views, integrating seamlessly with Spring MVC.
- **MySQL & JPA Hibernate**: Handles database operations.
- **JUnit, AssertJ, Mockito**: For robust backend testing.
- **Selenium WebDriver**: Ensures the reliability of integration tests.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites
- Java JDK 11 or later
- Node.js 12.x or later
- MySQL 8.x or later

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/ValentinTyanov/hangman-web-application.git
   cd hangman-web-application
2. Install NPM packages:
   npm install
3. Install Maven dependencies:
   mvn install

### Usage
To run the application, use the following Maven command:
   mvn spring-boot:run

Once the server starts, you can access the application by navigating to http://localhost:8080 in your web browser.
