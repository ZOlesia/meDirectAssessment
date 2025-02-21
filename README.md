Assessment

Overview

This repository contains Postman API collection, Postman API test run report, automation frameworks for both API and UI testing. The structure is as follows:

API/ → API test automation using Java, Rest Assured, Maven, TestNG.
UI/ → UI test automation using Java, Selenium WebDriver, Maven, and TestNG.
Restful-Booker API.postman_collection.json → Postman API collection.
Restful-Booker API.postman_test_run.json → Postman API test run report.

Prerequisites

Before running the tests, ensure that the following dependencies are installed on your system:

1. Install Java 17

Download Java 17 from Oracle JDK or OpenJDK.

Install Java and set up system environment variables:

Add JAVA_HOME to system variables (pointing to the JDK installation path).

Add JAVA_HOME/bin to the system PATH.

Verify installation by running:

java -version
javac -version

2. Install Maven

Download Maven Source zip archive from Apache Maven.

Extract and install Maven.

Set up system environment variables:

Add MAVEN_HOME to system variables (pointing to the Maven installation path).

Add MAVEN_HOME/bin to the system PATH.

Verify installation by running:

mvn -version

3. Install an IDE (Optional)

You can use Eclipse, IntelliJ IDEA, or any preferred IDE to open the project.

Cloning the Repository

To clone the repository, run:

git clone <repository_url>
cd meDirectAssessment

Installing Dependencies

After cloning, you need to install all dependencies before running tests for both API and UI:


API Tests

Navigate to the API directory and execute the tests using Maven:

This command will download and install all necessary dependencies from pom.xml.

mvn clean install

Running the Tests

mvn test

This will run all TestNG test cases defined in testng.xml.


UI Tests

Navigate to the UI directory and execute the tests using Maven:

This command will download and install all necessary dependencies from pom.xml.

mvn clean install

Running the Tests

mvn test

This will run all TestNG test cases defined in testng.xml.


Dependencies

The frameworks use the following dependencies:

API Framework: Java, Rest Assured, TestNG, Gson, Jackson, Lombok, Extent Reports, Log4j

UI Framework: Java, Selenium WebDriver, TestNG, WebDriverManager, Extent Reports

Reporting

After test execution, reports will be available in the reports/ folder in API and UI directories.
