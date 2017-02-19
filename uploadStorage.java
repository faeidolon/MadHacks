import java.sql.*;  
import com.microsoft.sqlserver.jdbc.*;   

public class uploadStorage {
	
	
	//storage - stores current txt (int idNum, int playerNum, String txt) (limit = 4000);
	
	static int id = -9999;
    static int playerNum = -9999;
    static String txt = null;
	
	
     public uploadStorage(int idNum, int playerNum, String txt){
    	 uploadStorage.id = idNum; //unique identifier in SQL
    	 uploadStorage.playerNum = playerNum; // corresponding prompt
    	 uploadStorage.txt = txt; //keyword to be added 
     }
	
     	public void upload(int idNum, int playerNum, String txt){  
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

	                statement = connection.prepareStatement("INSERT INTO storage (id, playerNum, txt) VALUES (?, ?, ?)");
	                statement.setInt(1, id);
	                statement.setInt(2, playerNum);
	                statement.setString(3, txt);

	                
	                
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