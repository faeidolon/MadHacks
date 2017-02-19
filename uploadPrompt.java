import java.sql.*;  
import com.microsoft.sqlserver.jdbc.*;   

public class uploadPrompt {
	
	static int id = -9999;
    static String gameType = null;
    static String prompt = null;
	
	
     public uploadPrompt(int id, String gameType, String prompt){
    	 uploadPrompt.id = id; //unique identifier in SQL
    	 uploadPrompt.gameType = gameType; //"hacking" or writing
    	 uploadPrompt.prompt = prompt; //text to input 
     }
     
     
	
	        // Connect to your database.  
	        // Replace server name, username, and password with your credentials  
	        public void upload(int id, String gameType, String prompt) {  
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
	           // ResultSet resultSet = null;  

	            try {  
	                connection = DriverManager.getConnection(connectionString);  

	                // Create and execute a SELECT SQL statement.  
	               // (id, gameType, pronptVal)
	                //            String
	               
	                
	                
	                statement = connection.prepareStatement("INSERT INTO prompts (id, gameType, promptVal) VALUES (?, ?, ?)");
	                statement.setInt(1, id);
	                statement.setString(2, gameType);
	                statement.setString(3, prompt);

	                
	                
	                int resultSet = statement.executeUpdate();
	                System.out.println(resultSet);
	            }  
	            catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	            finally {  
	                if (statement != null) try { statement.close(); } catch(Exception e) {}  
	                if (connection != null) try { connection.close(); } catch(Exception e) {}  
	            }  
	        }  
	    
}
