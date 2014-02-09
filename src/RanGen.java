import java.math.BigInteger;

public class RanGen {

	public BigInteger randomGen(int bits) {
		char[] random = new char[bits];
		random[0] = '1';
		BitGen bitGen = new BitGen(random, bits - 1);

		for (int i = 1; i < bits; i++) {
			(new GenThread(i, bitGen)).start();
		}

		bitGen.notifyToRun();
		return bitGen.production();
	}
}

class Barrier {
	int waiting;

	Barrier(int waiting) {
		this.waiting = waiting;
	}

	public synchronized void iveArrived() {
		if (waiting > 0)
			waiting--;

		if (waiting == 0)
			notifyAll();
		else {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class BitGen {

	char[] producer;
	int bits;
	Barrier begin;
	Barrier end;

	BitGen(char[] producer, int bits) {
		this.producer = producer;
		this.bits = bits;
		begin = new Barrier(bits + 1);
		end = new Barrier(bits + 1);
	}

	public void ready(int index) {

		begin.iveArrived();

		producer[index] = Character.forDigit(
				(((int) (Math.random() * 10)) % 2), 2);

		end.iveArrived();
	}

	public void notifyToRun() {
		begin.iveArrived();
	}

	public BigInteger production() {
		end.iveArrived();
		return new BigInteger(new String(producer), 2);
	}
}

class GenThread extends Thread {
	int id;
	BitGen bitGen;

	GenThread(int id, BitGen bitGen) {
		this.id = id;
		this.bitGen = bitGen;
	}

	public void run() {
		bitGen.ready(id);
		// System.out.println(id);
	}
}
