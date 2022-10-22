## Spike: Arduino to java connection

### 1) What problem are we trying to solve?

How to connect the arduino to the java application via a serial port

### 2) Possible solution:

Using the JSSC library to connect to the COM port of the arduino

### 2.1) Is this solution feasible?

Yes, this seems to be the best solution

### 2.2) How much effort does this solution need?

- _Give a time estimation of the solution_: 1 week
- _Is there enough knowledge in the team?_: After research, this seems like the best solution, test code is in the
  repository and available to everyone

### 2.3) Complete documentation of this solution

- By using the JSSC library we're connecting to the COM port of the arduino and dataStream the data from it.
- We're sending and receiving bytes which need to be parsed into proper data
- [Link to test code](https://gitlab.com/kdg-ti/integration-2.1/22-23/team-3/project-planning/-/blob/main/src/ArduinoDataReader.java#L1)
