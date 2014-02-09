import java.math.BigInteger;


public class KeyGen {
	
	private BigInteger n;
	private BigInteger g;
	private BigInteger lambda;
	private BigInteger mu;
	
	public KeyGen(int bits) {
		
		Prime prime = new Prime();
		BigInteger p = prime.primeGen(bits);
		BigInteger q = prime.primeGen(bits);
		
		while(p.equals(q))
			q = prime.primeGen(bits);
		
		n = p.multiply(q);
		g = n.add(BigInteger.ONE);
		
		lambda = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		mu = lambda.modInverse(n);
	}
	
	public PublicKey getPublicKey() {
		PublicKey pk = new PublicKey();
		pk.setPublicKey(n,g);
		return pk;
	}
	
	public PrivateKey getPrivateKey() {
		PrivateKey sk = new PrivateKey();
		sk.setPrivateKey(lambda,mu,n);
		return sk;
	}
	
}
