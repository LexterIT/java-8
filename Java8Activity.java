import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.concurrent.Phaser;


public class Java8Activity {
	
	public static void main(String[] args) {
		RaceTrack raceTrack;
		Java8Activity java8 = new Java8Activity();
		HorseFactory horseFactory = new HorseFactory();
		IOUtil scanner = new IOUtil();
		List<Horse> listOfHorse = new ArrayList<Horse>();
		List<RaceHorse> raceHorses = new ArrayList<RaceHorse>();
		int distance, horses;
		Phaser phaser;

		distance = scanner.getIntInput("Enter racetrack Distance", "distance");
		horses = scanner.getIntInput("Enter number of Horses", "horse");

		//creates the horses
		listOfHorse = horseFactory.createHorses(horses);
		//filters healthy horses
		listOfHorse = listOfHorse.stream()
		.filter(h -> h.isHealthy() == true)
		.collect(Collectors.toList());

		//Horse is a POJO(Name, Warcry, Health), while RaceHorse does the Actions(Race)
		phaser = new Phaser(listOfHorse.size());
		for(Horse h: listOfHorse) {
			raceHorses.add(new RaceHorse(h, phaser));
		}

		if(raceHorses.size() == 0) {
			System.out.println("No Healthy horse has been created, terminating program!");
			System.exit(0);
		} else if (raceHorses.size() == 1) {
			System.out.println("Only one healthy horse is found! Cannot race with only 1 participant");
			System.exit(0);
		}

		//Putting racehorses into the racetrack
		raceTrack = new RaceTrack(raceHorses, distance);
		//Starting race
		// System.out.println("\nSTARTING RACE WITH "+raceHorses.size()+" HORSE PARTICIPANTS");
		System.out.println("\n" + raceHorses.size() + " PARTICIPANTS OF THE RACE ARRIVING, AND SHOWING UP!!!!!!");
		java8.threadSleep(500);
		for(int i=3;i>0;i--) {	
			System.out.println("IN:\t"+i+"!!!!!");
			java8.threadSleep(500);

		}
		raceTrack.Race();
	}

	public void threadSleep(int milis) {
		try {
			Thread.sleep(milis);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}