# Contact List Automation Suite
UI Automation framework for Contact List web application

# Overview
This project consists of an automation test suite for the Contact List web application. The suite is built using Java 17, Selenium, and TestNG, along with additional libraries like Lombok and SLF4J.

## Features
- Cross-browser testing
- Easily extensible for new test cases
- Clean and maintainable test code using TestNG
- Logging with SLF4J for easier debugging and tracking
- Test data management and parametrization
- Parallel execution support via TestNG


# Prerequisites
To set up and run this automation suite, ensure the following software is installed on your machine:

#### Java 17
#### Maven
#### Google Chrome or Microsoft Edge


# Getting started
### 1. Clone repository
```bash
git clone https://github.com/larimpon/selenium-contact-list-ui.git
cd selenium-contact-list-ui
```

### 2. Install dependencies and execute test suite
```bash
mvn clean test
```

### Execute single test cases
```bash
mvn clean test -Dtest=classname
```
Test cases are based on: VIM_INTERVIEW_TASK_QA.pdf, 

Test cases are located at: ./src/test/java/testCases

### 3. Configuration
You can configure the project settings such as browser type etc by modifying the ./src/resources/config.properties file:
```properties
browser=chrome
headless=false
maximize=true
baseUrl=https://thinking-tester-contact-list.herokuapp.com
timeout=100
```

### Configure test suite
You can configure the test suite such as parallel execution and tests to be executed by modifying the src/resources/testng.xml file.

### 4. Reporting
Except from the logs you can find a full report at ./target/surefire-reports/index.html
# Tools & Technologies
### 1. Java 17
The suite is developed using Java 17, leveraging its new features and performance improvements.

### 2. Selenium
Selenium WebDriver is used for interacting with the PMTool web application.

### 3. TestNG
TestNG is used as the test framework for structuring, running, and managing tests.

### 4. Lombok
Lombok is used to reduce boilerplate code by auto-generating methods like getters, setters, constructors, etc.

### 5. SLF4J
SLF4J is used for logging, providing useful insights into test execution and failure reasons.
