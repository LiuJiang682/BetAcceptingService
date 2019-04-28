# BetAcceptingService



**Coding Challenge**

Requirements

- Code must be checked into your own github account and the link shared
- A working solution to the problem test-driven in Java / Maven
- There must be no errors in the code
- Code must have sufficient test coverage
- Please explain your design / approach and any assumptions
- Clear and concise instructions on how to execute the solution

Challenge

1. Create a Microservice that can accept bets in Binary or JSON format. The Microservice will need to be capable of accepting hundreds of bets per second. The data needs to be stored in a database (Any database can be used).

Sample Bet Data

| **Field Name** | **Field Type** | **Sample Data** |

| Date Time | Date | 2018-01-01 14:56 |

| Bet Type | String | WIN |

| Prop Number | Number | 104567 |

| Customer ID | Number | 1080 |

| Investment Amount | Double | 100.00 |

- Add some validation to the bets processing

  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a. Bet Type can only be _WIN / PLACE / TRIFECTA / DOUBLE / QUADDIE_
  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b. Maximum Investment Amount is $20,000
  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;c. Date Time must not be in the past


- Add some reports to the microservice

  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a. Total investment per bet type
  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b. Total investment per Customer ID
  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;c. Total bets sold per bet type
  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;d. Total number of bets sold per hour

- Add security to the Microservice


### Installation Guide

**Prerequisite**

Please verify that the target machine has the following software packages installed:

- Java 8
- Maven
- Git
- curl

**Install, Build and Run the Application**

1. Create a target directory
2. Open a new command prompt (Window box) or terminal (Linux box)
3. Go to the target directory:
   
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`cd path-to-target-dir`
4. Checkout the code:
	
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`git clone https://github.com/LiuJiang682/BetAcceptingService.git`
5. To build the application, run this command:

   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`mvn clean install` 
6. If the build success, run this command to start the application:

   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`mvn spring-boot:run` 

**Test the application**

To test the application, please pay attention to the src/test/resource directory and its scripts sub-directory.

 - bet.json file has all bet related fields, please modify this file according to your test scenario before you execute your test scenario for place bet.
 - There are 2 login script files in the script directory, they login user and admin respectively.
 - After run respective login script, testing can start with rest of scripts, those scripts with _adminCookies.sh use the amdin_login.sh cookie. Those scripts without adminCookies.sh use user:password to login every time execute.
 - Windows user please use respected batch files install of shell script files.. 