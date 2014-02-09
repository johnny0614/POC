POC
===

POC : Secure multi-party protocol using modern cryptographic technique and tamper resistant hardware



Vulnerability PoC:

Alice sends E(1) and E(0) instead of E(z1x+a) and E(z1) in order to Bob, in step 1. 
Then she can obtain the value of z2+z2b. In step 3, Alice sends E(0) instead of E(a) to Bob, 
Then she can obtain z2b. Thus Alice is able to retrieve private data b.

Eve sends E(0) and E(1) instead of E(z1'x'+a') and E(z1') in order to Bob, in step 1. 
Then she can obtain the value of z2'y+z2'b. In step 3, Eve sends E(0) instead of E(a') to Bob. 
The Eve can obtain z2'b. Combined with b retrieved by Alice, Eve can work out the value of y.

Therefore, the private data y and b are both exposed.

