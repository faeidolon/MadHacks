import java.sql.*;  
import com.microsoft.sqlserver.jdbc.*;   

public class DownloadPrompt {
	
	
	//storage - stores current txt (int idNum, int playerNum, String txt) (limit = 4000);
	
	static int id = -9999;
    static String txt = null;
    int tempSize = -99999;
    int tempRandom = -99999;
	
	
     public DownloadPrompt(int id){
    	 DownloadPrompt.id = id; // corresponding prompt
    	 
    	
     }
	
     	@SuppressWarnings("resource")
		public String download(String gameType){  
     		
     		
     		
	            String connectionString =  
	                    "jdbc:sqlserver://testmadhacks2017.database.windows.net:1433;"  
	                    + "database=testMADHACKS;"  
	                    + "user=madskills@testmadhacks2017;"  
	                    + "password=savvyCoders2017;"  
	                    + "encrypt=true;"  
	                    + "trustServerCertificate=false;"  
	                    + "hostNameInCertificate=*.database.windows.net;"  
	                    + "loginTimeout=30;";  


	            // Declare the JDBC objects.  
	            Connection connection = null;  
	            PreparedStatement statement = null;   
	            ResultSet returned = null;  

	            try {  
	                connection = DriverManager.getConnection(connectionString);  
	               
	                statement = connection.prepareStatement("SELECT * from pointers WHERE tableName = 'prompts'");
	                returned = statement.executeQuery();
	                returned.next();
	               tempSize = returned.getInt("pointVal");
	                
	                id = (int) (Math.random() * tempSize-1)+1; 
	                //System.out.println(id);
	                statement = connection.prepareStatement("SELECT * from prompts WHERE id = " + id);
	                returned = statement.executeQuery();
	                returned.next();
	               // System.out.println(returned.getString("id"));
	                while (!returned.getString("gameType").equals(gameType)){
	             // System.out.println(returned.getString("gameType"));
	                	id = (int) (Math.random() * tempSize-1)+1; 
	               statement = connection.prepareStatement("SELECT * from prompts WHERE id = " + id);
	               returned = statement.executeQuery();
	               returned.next();
	                }
	               
	               return returned.getString("PromptVal");
	            }  
	            catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	            finally {  
	                if (statement != null) try { statement.close(); } catch(Exception e) {}  
	                if (connection != null) try { connection.close(); } catch(Exception e) {}  
	            }
				return null; 
	        }  
	    
}