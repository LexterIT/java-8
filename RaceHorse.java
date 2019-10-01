import java.util.concurrent.Phaser;

public class RaceHorse implements RaceMount, Runnable{
	
	private Horse horse;
	private static Phaser phaser;
	private int totalDistanceTravelled = -10;
	private int speed;
	private int runCounter = 0;
	private static int lowestLast;
	private FinishLine finishLine;

	public RaceHorse(Horse horse, Phaser phaser) {
		this.horse = horse;
		this.phaser = phaser;
	}

	public void setFinishLine(FinishLine finishLine) {
		this.finishLine = finishLine;
	}

	private void setTotalDistTrav(int additional) {
		this.totalDistanceTravelled += additional;
	}

	private int getTotalDistTrav() {
		return totalDistanceTravelled;
	}

	private void setSpeed(int speed) {
		this.speed = speed;
	}

	private int getSpeed() {
		return speed;
	}

	public String getName() {
		return horse.getName();
	}

	public synchronized void run() {	
		Randomizer random = new Randomizer();
		RaceTrack raceTrack = new RaceTrack();
		int speed, speedlimit;
		int distance = raceTrack.getDistance();
		int totalDist = getTotalDistTrav();
		boolean isBoosted = false;
		while(totalDist < 0) {
			speedlimit = 10;
			speed = random.getRandomSpeed(speedlimit);
			totalDist += speed;
			System.out.println(horse.getName() + "\t ARRIVING TO THE STARTING LINE AT SPEED:" + speed);
			threadSleep(300);
		}
		System.out.println(horse.getName() + "\t HAS ARRIVED TO THE STARTING LINE!");
		totalDistanceTravelled = 0;
		phaser.arriveAndAwaitAdvance();
		System.out.println();
		phaser.arriveAndAwaitAdvance();
		while(totalDist < distance) {
			speedlimit = 10;
			isBoosted = false;
			if(runCounter > 0) {
				isBoosted = (getSpeed() == lowestLast);	
				if(isBoosted) {
					speedlimit = 20;
				}
			}
			phaser.arriveAndAwaitAdvance();
			speed = random.getRandomSpeed(speedlimit);
			setSpeed(speed);
			System.out.println(horse.getName() +"\tTOTAL DISTANCE: "+ getTotalDistTrav() +"\tCURRENT SPEED: "+ speed +"\tBOOSTED: "+ isBoosted +"\tRUNCOUNTER:\t"+runCounter);
			setTotalDistTrav(speed);
			lowestLast = 0;
			phaser.arriveAndAwaitAdvance();
			//first horse that arrives here set the first lowestlast from 0. from there on check if the remaining horses has lower speed
			if(lowestLast == 0) {
				lowestLast = speed;
				System.out.println();
			} else if(speed < lowestLast) {
				lowestLast = speed;
			}
			totalDist = getTotalDistTrav();
			//Checks if this finished the race
			if(totalDist >= distance) {
				phaser.arriveAndDeregister();
				finishLine.arrive(horse);
			} 
			// threadSleep(300);
			runCounter++;

		}
	}

	public void threadSleep(int milis) {
		try{
			Thread.sleep(milis);
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}