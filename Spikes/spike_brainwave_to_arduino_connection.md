## Spike: Brainwave headset connect to Arduino

### 1) What problem are we trying to solve?

How to connect the brainwave headset to the Arduino

### 2) Possible solution:

Headset >> PC connection via bluetooth

### 2.1) Is this solution feasible?

- Current progress:
- Headset >> PC connection seems possible, the next step is polishing up the checksum method and figuring out what the numbers mean
- Benefit of directly connecting to the PC instead of using an arduino as the MITM

### 2.2) How much effort does this solution need?

- _Give a time estimation of the solution_: By the end of the week this should be figured out
- _Is there enough knowledge in the team?_: We're trying our best!

### 2.3) Complete documentation of this solution

-   By using the JSSC library we're connecting to the COM port of the headset and reading the data from it. The data is then parsed and the checksum is calculated.
### 3) Alternative solution...

[Brain-hack](https://frontiernerds.com/brain-hack)

### 3.1) Is this solution feasible?
TBD

### 3.2) How much effort does this solution need?
TBD

### 3.3) Complete documentation of this solution
TBD

And so on...
