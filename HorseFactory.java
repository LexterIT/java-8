import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;	
import java.util.Optional;

public class HorseFactory {

	private Randomizer randomizer;

	public HorseFactory() {
		randomizer = new Randomizer();
	}
	
	public List createHorses(int numberOfHorse) { 
		String[] names = {null,"Ardu", "Doom", "Dine","Horsey","Jackie","Felix","Gawi","Aces","Nobu","Airs"}; //11
		String[] warcries = {null,"Hephep","Hooray","Yee haw","Awoo Awoo","Ho ho","Ook Ook","Kek","Wkwk","CEEEEEEEB"}; //10
		List<String> horseNames = Arrays.asList(names);
		List<String> warCry = Arrays.asList(warcries);
		Collections.shuffle(horseNames);
		Collections.shuffle(warCry);
		String name, warcry;
		String prefix="horse";
		boolean health;
		List<Horse> listOfHorse = new ArrayList<Horse>(numberOfHorse);
		for(int i=0; i<numberOfHorse; i++) {

			if(i < horseNames.size()) {
				name = Optional
				.ofNullable(horseNames.get(i))
				.orElse("");
			} else {
				name = prefix+i;
			}
			if(i < warCry.size()) {
				warcry = Optional
				.ofNullable(warCry.get(i))
				.orElse("");
			} else {
				warcry = randomizer.getHorseString(8);
			}

			health = randomizer.getRandomHealth();
			listOfHorse.add(new Horse(name, warcry, health));
			// System.out.println(name+"\t"+warcry+"\t"+health);
		}
		return listOfHorse;
	}

}
