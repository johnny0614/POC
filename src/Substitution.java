import java.math.BigInteger;

public class Substitution {

	char[] substitutionBox;
	int bits;
	BigInteger one;

	public Substitution(int bits) {
		this.bits = bits;
		substitutionBox = new char[bits * 6];
		for (int i = 0; i < bits * 6; i++) {
			substitutionBox[i] = Character.forDigit(
					(((int) (Math.random() * 10)) % 2), 2);
		}
		one = new BigInteger("1", 2);
	}

	public BigInteger substitute(BigInteger base) {
		char[] substitution = new char[bits];
		int begin = (int) (Math.random() * bits * 5);
		System.arraycopy(substitutionBox, begin, substitution, 0, bits);
		if (base.shiftRight(bits - 1).equals(one))
			substitution[0] = '0';
		else
			substitution[0] = '1';
		BigInteger xorvar = new BigInteger(new String(substitution), 2);
		return base.xor(xorvar);
	}

}
