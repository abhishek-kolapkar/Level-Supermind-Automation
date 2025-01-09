# Level Supermind Automation : Android

### ```Appium-Selenium-TestNG Framework with POM structure```

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Folder Structure](#folder-structure)
- [Running Tests](#running-tests)
- [Approach Taken](#approach-taken)
- [Challenges](#challenges)

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 8+
- Maven
- Appium 2.x
- Android Studio
- UiAutomator2 driver
- Your Android device should connected to system
- Any IDE (VS Code, Eclipse, etc.)

## Installation

1. Clone the repository:

```bash
  git clone https://github.com/abhishek-kolapkar/Level-Supermind-Automation.git

  cd android
```

2. Install the required dependencies:

```bash
  [cmd] mvn clean
```

## Folder Structure

```
  Level-Supermind-Login-Automation/
  |
  ├── android/
  |   ├── src/
  |   |   ├── test
          |   ├── java
              |   ├── base  (initialize AndroidDriver with capabilities and all pages)
              |   ├── pages (all the locators and associated methods with Pagefctory conecepts)
              |   ├── reports
                      ├── ReportManager (initialization of Extent report object & apis)
                      ├── TestListner   (capture test events - passed, failed, skipped, etc.)

                  ├── tests
                      ├── LoginWithEmail (whole login process using email with OTP verification)
                      ├── LoginWithPhone (whole login process using phone with OTP verification)

                  ├── utils
                      ├── Helper       (utility methods)
                      ├── CaptureTest  (TakeScreenshot)
                      ├── Notification (Automate OTP from notification bar)
              |
              ├── resources
                  ├── properties  (Setup driver capabilities & login email/phone)
                  ├── reports     (HTML Tests report)
                  ├── runner [TestNG xml files]
                      ├── emailLogin.xml (run LoginWithEmail class)
                      ├── phoneLogin.xml (run LoginWithPhone class)
       
                  ├── screenshots
      |            
      ├── target
      ├── pom.xml (added all required dependecies & plugins)
      ├── README.md
   
```

## Running Tests

To run the tests, use the following maven commands:

```bash
  Open - src/test/resources/Properties/Properties.properties

  update with your connected device : 
  - platformVersion 
  - deviceName
  - userEmail ( valid / invalid )
  - userPhone ( valid / invalid )
```

```bash
1. To verify - Login functionality with Email

  [cmd] mvn test -P login-with-email
```


```bash
2. To verify - Login functionality with Phone

  [cmd] mvn test -P login-with-phone
```

## Approach Taken

### 1. Driver Initialization:
>- Centralized AndroidDriver initialization in the base package with customizable capabilities. 
>- Capability settings stored in properties files for easy configuration changes without modifying code.

### 2. Test Design:
>- Used POM for structuring page-specific locators and methods, ensuring modular and reusable code.
>- Divided test cases into separate test classes for different login methods, making the framework scalable for future enhancements.

### 3. Reporting:
>- Integrated Extent Reports for comprehensive HTML-based reports, providing detailed insights into test execution status.
>- TestNG listeners capture test events and link screenshots of failures for enhanced traceability.

### 4. Utility Layer:
>- Encapsulated reusable methods for tasks such as screenshot capturing, OTP handling, and UI interactions.
>- Notifications utility handles runtime OTP extraction, ensuring smooth test execution for login flows requiring OTP.

### 5. Handling valid/invalid email/phone inputs
>- Created *validateInput()* method in [pages.LoginPage] <br /> to handle valid/invalid inputs & log appropriate error messages

>- Handle mobile number format validation only for India


### 6. Test Execution:
>- TestNG XML files facilitate executing specific test suites (email / phone login).

### 7. Modular and Scalable Design:
>- Clear separation of concerns with dedicated folders for base setup, pages, utilities, and tests.

### 8. Scope for Enhancements
>- Perform regerssion testing with multiple valid/invalid data at a time & log error messages
<br />
> using testng *@DataProvider* method


## Challenges
```
- Learn Appium in less time & use it practically 

- Appium flaky nature

- Handling toast alert

- Country selection (Not implemented)

- Dynamic OTP handling

- Slow test execution

- Integration of screenshots with extent reports
```
