## Spike: Brainwave headset connect to Arduino

### 1) What problem are we trying to solve?

How to connect the brainwave headset to the Arduino

### 2) Possible solution:

Headset >> PC connection via bluetooth << WHAT WE'RE GOING WITH

### 2.1) Is this solution feasible?

- Current progress:
- Headset >> PC connection seems possible, the next step is polishing up the checksum method and figuring out what the numbers mean
- Benefit of directly connecting to the PC instead of using an arduino as the MITM
### UPDATE
- We are able to parse a packet, the checksum is not necessary just for the attention/meditation values 
- Basically done

### 2.2) How much effort does this solution need?

- _Give a time estimation of the solution_: By the end of the week this should be figured out
- _Is there enough knowledge in the team?_: After research, this seems like the best solution

### 2.3) Complete documentation of this solution

- By using the JSSC library we're connecting to the COM port of the headset and reading the data from it. The data is then parsed and SIGNAL/ATTENTION/MEDITATION is returned.
- The checksum is not necessary for the attention/meditation values - so we decided to skip that part.
### 3) Alternative solution...

[Brain-hack](https://frontiernerds.com/brain-hack)

### 3.1) Is this solution feasible?
### UPDATE - NOT POSSIBLE WITH THE CURRENT TOOLS DUE TO HC-05 REQUIRING ANOTHER PIN TO BE POWERED ON

### 3.2) How much effort does this solution need?
TBD

### 3.3) Complete documentation of this solution
TBD

And so on...
