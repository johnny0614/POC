import java.math.BigInteger;


public class PrivateKey {
	private BigInteger lambda;
	private BigInteger mu;
	private BigInteger n;
	
	public PrivateKey() {
		lambda = null;
		mu = null;
		n = null;
	}
	
	public BigInteger decryption(BigInteger cipher) {
		if(lambda==null||mu==null||n==null)
			return null;
		else {
			BigInteger tmp = cipher.modPow(lambda,n.pow(2));
			tmp = (tmp.subtract(BigInteger.ONE)).divide(n);
			return (tmp.multiply(mu)).mod(n);
		}
	}
	
	public void setPrivateKey(BigInteger lambda, BigInteger mu, BigInteger n) {
		this.lambda = lambda;
		this.mu = mu;
		this.n = n;
	}
}
