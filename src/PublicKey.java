import java.math.BigInteger;


public class PublicKey {
	
	private BigInteger n;
	private BigInteger g;
	
	public PublicKey(){
		n = null;
		g = null;
	}
	
	public BigInteger encryption(BigInteger msg) {
		if(n==null||g==null)
			return null;
		else {
			int random = (int)(Math.random()*10);
			
			BigInteger r = (new BigInteger(((int)((n.doubleValue())*Math.random()))+"")).mod(n);
			return ((g.modPow(msg,n.pow(2))).multiply(r.modPow(n,n.pow(2)))).mod(n.pow(2));
		}
	}
	
	public void setPublicKey(BigInteger n, BigInteger g) {
		this.n = n;
		this.g = g;
	}
}
