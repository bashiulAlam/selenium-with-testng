# Automation Assignment

## Project Tools Used

- **Programming Language:** Java (JDK 1.8)
- **Framework and Tools Used:** Selenium, TestNG, JUnit, Maven
- **IDE:** Intellij Idea

## Project Structure

1. The project has been implemented following POM (Page Object Model) structure. The page objects are implemented inside the package **com.assignment.pages**.
2. The test classes are implemented inside the package ***com.assignment.tests***.
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
4. Alternatively can run the project from command prompt or terminal by executing the following command from your *{project_root}*:

         mvn clean test -DsuiteXmlFile=testng.xml -Dbrowser=chrome

## Result Observation:

1. You can check the test cases executed from your IDE console.
2. An HTML file is also generated after the execution. It is named **index.html** and can be found in path: *test-output/html*.

## Future Improvements

1. Currently we only have Chrome browser support, we can extend it to other browsers.
2. For an operations we had to use *Thread.sleep()* as data load time is high. We can try to figure out a way around with *Explicit* or *Fluent* wait to use instead.
3. Test cases have not been written in *BDD* format, we can add any *Gherkin* framework to achieve that.
4. Project can be integrated with a CI/CD pipeline.
5. Logback support is not added yet.
