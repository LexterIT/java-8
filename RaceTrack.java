import java.util.concurrent.Phaser;
import java.util.List;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RaceTrack {
	
	private List<RaceHorse> groupOfHorse;
	private static int raceTrackDistance;

	public RaceTrack(){
	}

	public RaceTrack(List<RaceHorse> groupOfHorse, int raceTrackDistance) {
		this.groupOfHorse = groupOfHorse;
		this.raceTrackDistance = raceTrackDistance;
	}

	public void Race() {
		FinishLine finishLine = new FinishLine();
		Phaser phaser = new Phaser(groupOfHorse.size());
		ExecutorService executor = Executors.newFixedThreadPool(groupOfHorse.size());
		// for(RaceHorse h : groupOfHorse) {
		// 	h.setFinishLine(finishLine);
		// 	executor.execute(h);
		// }
		groupOfHorse.stream().forEach(h -> { 
				h.setFinishLine(finishLine); 
				executor.execute(h);} );
		// groupOfHorse.stream().forEach(executor::execute);
		executor.shutdown();
		try{ 
			executor.awaitTermination(20,TimeUnit.SECONDS); 
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}

		finishLine.showRankings();

	}

	public int getDistance() {
		return raceTrackDistance;
	}
}