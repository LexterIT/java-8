import java.util.Random;

public class Randomizer {
	
	private Random randomizer = new Random();
	//ASCII FROM 57 TO 122 32 + 57 = 122
	private final int minAscii = 65;
	private final int maxAscii = 57;

	public boolean getRandomHealth() {
		return randomizer.nextBoolean();
	}

	public String getHorseString(int length) {
		String horseString = "";
		char randomChar;
		int asciiValue;
		for(int i=0; i<length; i++) {
			asciiValue = minAscii + (int) (Math.random() * maxAscii);
			randomChar = (char) asciiValue;
			horseString += randomChar;
		}
		return horseString;
	}

	public int getRandomSpeed(int speedLimit) {
		int randomSpeed = 1 + (int) (Math.random() * speedLimit);
		return randomSpeed;
	}

}