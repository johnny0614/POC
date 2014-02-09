import java.math.BigInteger;


public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigInteger g = new BigInteger("5652");
		BigInteger lambda = new BigInteger("30");
		BigInteger n = new BigInteger("77");
		BigInteger r = new BigInteger("23");
		
		//System.out.println(g.pow(21).multiply(r.pow(77)).mod(n.pow(2)));
		//System.out.println((new BigInteger("3804")).pow(2));
		//System.out.println((new BigInteger("14470416")).mod(new BigInteger("5929")));
		//System.out.println((new BigInteger("3656")).pow(30).mod(n.pow(2)));
		//System.out.println(g.pow(42).mod(n.pow(2)));
		
		BigInteger msg = new BigInteger("445");
		KeyGen kg = new KeyGen(32);
		PublicKey pk = kg.getPublicKey();
		PrivateKey sk = kg.getPrivateKey();
		BigInteger cipher = pk.encryption(msg);
		System.out.println(cipher);
		BigInteger plaintext = sk.decryption(cipher);
		System.out.println(plaintext);
	}

}
