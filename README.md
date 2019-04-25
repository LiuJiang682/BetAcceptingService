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
