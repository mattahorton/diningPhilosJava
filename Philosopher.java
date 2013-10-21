import java.text.SimpleDateFormat;
import java.util.Date;


public class Philosopher implements Runnable {
	
	Waiter waiter;
	int identifier;
	int numEat = 0;
	int numThink = 0;
	long elapsed,elapEat;
	static SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
	
	public Philosopher(Waiter waiter, int identifier){
		this.waiter = waiter;
		this.identifier = identifier;
	}

	@Override
	public void run() {
		String req, eat, finish;
		
		while(!Thread.currentThread().isInterrupted()){
			try {
				think();
				req = getClockTime();
				waiter.pickup(identifier);
				eat = getClockTime();
				eat();
				finish = getClockTime();
				printEat(identifier,numThink,elapEat,req,eat,finish);
				waiter.putdown(identifier);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}

	private void think() throws InterruptedException {
		numThink++;
		long start = System.currentTimeMillis();
		sleepTime(500);
		elapsed = System.currentTimeMillis() - start;
		printThink(identifier,numThink,elapsed);
	}
	
	private void eat() throws InterruptedException {
		numEat++;
		long start = System.currentTimeMillis();
		sleepTime(500);
		elapEat = System.currentTimeMillis() - start;
	}
	
	private static String getClockTime() {
		Date date = new Date();
		String stringDate = sdf.format(date);
		return stringDate;
	}
	
	private void sleepTime(int time) throws InterruptedException {
		long ms = (long)(time*Math.random());
		Thread.sleep(ms);
	}
	
	private void printThink(int i, int count, long time) {
		System.out.println(count + ": Philosopher " + i + " has been thinking for " + time/1000 + " seconds.");
	}
	
	private void printEat(int i, int count, long time, String reqTime, String eatTime, String finishTime) {
		System.out.println(count + ": Philosopher " + i + " requested to eat at " + reqTime + ", started eating at "
				+ eatTime + ", finished eating at " + finishTime + ", and ate for " + time/1000 + " seconds.");
	}
}
