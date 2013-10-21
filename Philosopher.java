
public class Philosopher implements Runnable {
	
	Waiter waiter;
	int philosopher;
	int numEat, numThink;
	
	public Philosopher(Waiter waiter, int philosopher){
		this.waiter = waiter;
		this.philosopher = philosopher;
	}

	@Override
	public void run() {
		
		while(!Thread.currentThread().isInterrupted()){
			try {
				think();
				waiter.pickup();
				eat();
				waiter.putdown();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}

	private void think() throws InterruptedException {
		sleepTime(500);
	}
	
	private void eat() throws InterruptedException {
		sleepTime(500);
	}
	
	private void sleepTime(int time) throws InterruptedException {
		long ms = (long)(time*Math.random());
		Thread.sleep(ms);
	}
}
