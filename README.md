# Automation Project Structure with Selenium in Java

This project automates login scenario in the site https://www.saucedemo.com/ with valid, invalid and blank data.
***

## Project Tools Used

- **Programming Language:** Java (JDK 1.8)
- **Framework and Tools Used:** Selenium (*Version: 4.8.1*), TestNG (*Version: 7.7.0*), Maven
- **IDE:** Intellij Idea

## Project Structure

1. The project has been implemented following POM (Page Object Model) structure. The page objects are implemented inside the package **com.assignment.pages**.
2. For *Data Driven Testing*, test data are read from a CSV file which is saved in the directory *DataFiles*.
3. The test classes are implemented inside the package ***com.assignment.tests***.

## Prerequisites

- You need to have the following installations in your machine:
    - JDK
    - Maven
    - Any IDE of your choice that supports Java
- A browser of your choice to check the HTML report
- For windows machine, you will need the following added to your system environment variables:
    - JDK
    - Maven

## Project Import and How to Run

1. Clone the project from GitHub or download the project and unzip it.
2. Open your IDE and import the project as a *maven project*.
3. Open the **testng.xml** file from *resources* path and run the tests as TestNG tests.
4. Right now, we have enabled headless run configuration, to run in headed mode, please comment out ***line number 48 and 56*** in the class ***BaseTest.java*** from *{project_root}/src/test/java/com/assignment/tests/*
5. Alternatively can run the project from command prompt or terminal by executing the following command from your *{project_root}*:

   >      mvn clean test -DsuiteXmlFile=testng.xml -Dbrowser={browserName}

   > In place of {browserName}, you need to put *chrome* or *firefox* to test in your choice of browser.

## Result Observation:

1. You can check the test cases executed from your IDE console.
2. An HTML file is also generated after the execution. It is named **index.html** and can be found in path: *test-output/html*.

## Future Improvements

1. Currently we only have Chrome and Firefox browser support, we can extend it to other browsers.
2. Test cases have not been written in *BDD* format, we can add any *Gherkin* framework to achieve that.
3. Logback support is not added yet.
4. Screenshot capture functionality is not added yet.
