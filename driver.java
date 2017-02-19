
public class driver {


	//BUILD ALL THE VARIABLES
	int id = -999999; //Open location in the SQL
	int promptNum = -999999; //Prompt corresponding with keyword in question
	int playerNum = -999999; //User's id number (with username)
	int pointVal = -99999; //Pointer location for a SQL
	String word = null; //keyword for prompt
	String text = null; //String for text that the user writes
	String tableName = null; //The table accepted with pointers
	String gameType = null; //Writing or Hacking
	String prompt = null; // String for prompt to add
	uploadPrompt upPrompt = new uploadPrompt(-999999, null,null); //prompts - writing prompts int idNum, String gameType, String prompt
	uploadKey upKey = new uploadKey(-999999, -999999,null); //keywords - writing keywords int idNum, int promptNum, String word (limit 50char)
	uploadStorage upStore = new uploadStorage(-999999, -999999, null); //storage - stores current text (int idNum, int playerNum, String text) (limit = 4000);
	
	
	
	
	
	public static void main(String[] args) {
		//storage - stores current text (int idNum, int playerNum, String text) (limit = 4000);
		//points - int id, String tableName, int pointVal
		//keep track of what elements are filled
		//Index pointer points at blank
		
		//WOW! YOU CAN CALL THE METHODS HERE!
		
		
	//upUsers (2)
	//upPointer ();	
	}

	
	public void uploadPrompt(){
		upPrompt.upload(id, gameType, prompt);
	}
	
	public void uploadKey(){
		upKey.upload(id, promptNum, word);
	}
	
	public void uploadStore(){
		upStore.upload(id, playerNum, text);
	}
	
	
}



