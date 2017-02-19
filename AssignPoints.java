import java.util.Scanner;

public class AssignPoints {

	//100 points for each keyword
	//1 point for every line (writing)
	// 70 -1 point for every line (coding)
	
	
	//Not checked
	public int checkForKeywords(String text, String[]keywords){
		Scanner scan = new Scanner(text);
		int points = 100;
		
		while (scan.hasNext()==true){
			for (int i = 0; i<keywords.length; i++){
				if (scan.next().contains(keywords[i])){
					points++;
					keywords[i]=null;
				}
			}
		}
		
		return points;
	}
	
	
	public int writeLengthPoints(String text){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(text);
		int points = 0;
		
		while (scan.hasNext()==true){
			scan.nextLine();
			points++;
		}
		return points;
	}
	
	
	
	public int codeLengthPoints(String text){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(text);
		int points = 70;
		
		while (scan.hasNext()==true){
			scan.nextLine();
			points--;
		}
		return points;
	}
	
	
}
