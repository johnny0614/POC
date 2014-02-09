import java.math.BigInteger;


public class AttackSimulator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Key generation\n");
		
		//generate given length crypto key
		KeyGen key_alice = new KeyGen(32);
		KeyGen key_eve = new KeyGen(32);
		
		
		PublicKey pk_alice = key_alice.getPublicKey();
		PrivateKey sk_alice = key_alice.getPrivateKey();
		
		
		PublicKey pk_eve = key_eve.getPublicKey();
		PrivateKey sk_eve = key_eve.getPrivateKey();
		
		//Bob's private data, Y and B
		int y_bob = 23;
		int b_bob = 287;
		
		//random number generate by Bob in protocol
		int random1_bob = 231;
		int random2_bob = 451;
		
		System.out.println("Alice Attack Step 1 :");
		System.out.println("Alice send Ea(1) and Ea(0) to Bob");
		
		BigInteger step1_alice_e0 = pk_alice.encryption(BigInteger.ZERO);
		BigInteger step1_alice_e1 = pk_alice.encryption(BigInteger.ONE);
		
		//System.out.println("Ea(0) = "+step1_alice_e0.toString());
		//System.out.println("Ea(1) = "+step1_alice_e1.toString());
		
		System.out.println();
		
		System.out.println("Alice Attack Step 2 :");
		System.out.println("Bob receive the data and computer Ea(z2z1(x+y)+z2(a+b))");
		
		
		BigInteger bob_send_to_alice_1 = (step1_alice_e1.pow(random1_bob))
				.multiply(step1_alice_e0.pow(y_bob*random1_bob))
				.multiply(pk_alice.encryption(new BigInteger((random1_bob*b_bob)+"")));
		
		//System.out.println("Ea(z2z1(x+y)+z2(a+b))=Ea(z2(z1x+a)+z2z1y+z2b) = "+bob_send_to_alice_1.toString());
		
		System.out.println();
		
		System.out.println("Alice Attack Step 3 : ");
		BigInteger result1_alice = sk_alice.decryption(bob_send_to_alice_1);
		System.out.println("Alice get value : z2+z2*b = "+result1_alice.toString());
		BigInteger step3_alice_e0 = pk_alice.encryption(BigInteger.ZERO);
		System.out.println("Alice send Ea(0) to Bob");
		//System.out.println("Ea(0) = "+step3_alice_e0.toString());
		
		System.out.println();
		
		System.out.println("Alice Attack Step 4");
		System.out.println("Bob receive the data and compute Ea(z2(a+b))");
		BigInteger z2b = new BigInteger((random1_bob*b_bob)+"");
		BigInteger bob_send_to_alice_2 = (step3_alice_e0.pow(random1_bob)).multiply(pk_alice.encryption(z2b));
		//System.out.println("Ea(z2(a+b))=Ea(a)^z2Ea(z2b) = "+bob_send_to_alice_2.toString());
		
		System.out.println();
		
		System.out.println("Alice Attack Step 5 : ");
		BigInteger result2_alice = sk_alice.decryption(bob_send_to_alice_2);
		System.out.println("Alice get value : z2*b = "+result2_alice.toString());
		
		BigInteger b = result2_alice.divide(result1_alice.subtract(result2_alice));
		
		System.out.println("Alice get value : b = "+b.toString());
		
		System.out.println();
		
		System.out.println("Eve Attack Step 1 :");
		System.out.println("Eve send Ee(0) and Ee(1) to Bob");
		
		BigInteger step1_eve_e0 = pk_eve.encryption(BigInteger.ZERO);
		BigInteger step1_eve_e1 = pk_eve.encryption(BigInteger.ONE);
		
		//System.out.println("Ee(0) = "+step1_eve_e0.toString());
		//System.out.println("Ee(1) = "+step1_eve_e1.toString());
		
		System.out.println();
		
		System.out.println("Eve Attack Step 2 :");
		System.out.println("Bob receive the data and computer Ea(z2z1(x+y)+z2(a+b))");
		
		
		BigInteger bob_send_to_eve_1 = (step1_eve_e0.pow(random2_bob))
				.multiply(step1_eve_e1.pow(y_bob*random2_bob))
				.multiply(pk_eve.encryption(new BigInteger((b_bob*random2_bob)+"")));
		
		//System.out.println("Ee(z2'z1'(x'+y)+z2'(a'+b))=Ee(z2'(z1'x'+a')+z2'z1'y+z2'b) = "+bob_send_to_eve_1.toString());
		
		System.out.println();
		
		System.out.println("Eve Attack Step 3 : ");
		BigInteger result1_eve = sk_eve.decryption(bob_send_to_eve_1);
		System.out.println("Eve get value : z2'y+z2'*b = "+result1_eve.toString());
		BigInteger step3_eve_e0 = pk_eve.encryption(BigInteger.ZERO);
		System.out.println("Eve send Ee(0) to Bob");
		//System.out.println("Ee(0) = "+step3_eve_e0.toString());
		
		System.out.println();
		
		System.out.println("Eve Attack Step 4");
		System.out.println("Bob receive the data and compute Ee(z2'(a'+b))");
		
		BigInteger bob_send_to_eve_2 = (step3_eve_e0.pow(random2_bob))
				.multiply(pk_eve.encryption(new BigInteger((random2_bob*b_bob)+"")));
		//System.out.println("Ee(z2'(a'+b))=Ee(a')^z2'Ee(z2'b) = "+bob_send_to_eve_2.toString());
		
		System.out.println();
		
		System.out.println("Eve Attack Step 5 : ");
		BigInteger result2_eve = sk_eve.decryption(bob_send_to_eve_2);
		System.out.println("Eve get value : z2'*b = "+result2_eve.toString());
		
	}

}
