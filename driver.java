
public class driver {


	//BUILD ALL THE VARIABLES
	static int id = -999999; //Open location in the SQL
	int promptNum = -999999; //Prompt corresponding with keyword in question
	static int playerNum = -999999; //User's id number (with username)
	int pointVal = -99999; //Pointer location for a SQL
	String word = null; //keyword for prompt
	String text = null; //String for text that the user writes
	String tableName = null; //The table accepted with pointers
	String gameType = null; //Writing or Hacking
	String prompt = null; // String for prompt to add
	static String username = null; //User's username
	static String pass = null; //User's password
	uploadPrompt upPrompt = new uploadPrompt(-999999, null,null); //prompts - writing prompts int idNum, String gameType, String prompt
	uploadKey upKey = new uploadKey(-999999, -999999,null); //keywords - writing keywords int idNum, int promptNum, String word (limit 50char)
	uploadStorage upStore = new uploadStorage(-999999, -999999, null); //storage - stores current text (int idNum, int playerNum, String text) (limit = 4000);
	static uploadUser upUser = new uploadUser(-9999999, null, null);
	static updateIndex upDex = new updateIndex(-99999, -9999);
	//uploadPrompt upPrompt = new uploadPrompt(-999999, null,null); //prompts - writing prompts int idNum, String gameType, String prompt
	//uploadKey upKey = new uploadKey(-999999, -999999,null); //keywords - writing keywords int idNum, int promptNum, String word (limit 50char)
	static downloadStorage downStore = new downloadStorage(-999999, -999999); //storage - stores current text (int idNum, int playerNum, String text) (limit = 4000);
	
	
	
	
	
	public static void main(String[] args) {
	 //Put code here to compile!
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
	
	public static void uploadUser(){
		upUser.upload(id, username, pass);
	}
	
	
	public static String downloadStore(){
		return downStore.download(id, playerNum);	
	//	return downStore.download(1, 1);
	}
	
	public void setPlayerID(){
		//TODO: pull the unique id from the user's name
		playerNum = 1;
	}
	
	public static void updateIndex(String tableName){
		
	
		switch (tableName){
		case("prompts"):upDex.update(1, 1); break;
		case("keywords"): upDex.update(2, 1);break;
		case("users"): upDex.update(3, 1);break;
		case("storage"):upDex.update(4, 1); break;
		}
	
	}
	
	
	
	
}



