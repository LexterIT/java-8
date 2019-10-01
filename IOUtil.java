import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.NumberFormatException;

public class IOUtil {
	
	private Scanner scanner = new Scanner(System.in);

	public int getIntInput(String message, String inputtype) {
		int input = 0;
		int limit = 0 ;
		boolean valid = false;
		String errormessage = "No error message";
		System.out.println(message);
		if(inputtype.equalsIgnoreCase("distance")) {
			errormessage = "The lengths is too short should be at least 50";
			limit = 50;
		} else if (inputtype.equalsIgnoreCase("horse")) {
			errormessage = "You cannot race with less than 1 possible horse";
			limit = 1;
		}
		while(valid == false || input <= limit) {
			try {
				input = Integer.parseInt(scanner.nextLine());
				valid = true;
				if(input <= limit) {
					System.out.println(errormessage);
				}
			} catch(NumberFormatException e) {
				System.out.println("Please only enter numbers");
				valid = false;
			}
		}
		return input;
	}

	
}