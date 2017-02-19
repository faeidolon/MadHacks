import java.sql.*;  
import com.microsoft.sqlserver.jdbc.*;   

public class downloadStorage {
	
	
	//storage - stores current txt (int idNum, int playerNum, String txt) (limit = 4000);
	
	static int id = -9999;
    static int playerNum = -9999;
    static String txt = null;
	
	
     public downloadStorage(int id, int playerNum){
    	 uploadStorage.id = id; //unique identifier in SQL
    	 uploadStorage.playerNum = playerNum; // corresponding prompt
    	 uploadStorage.txt = txt; //keyword to be added 
     }
	
     	public static String download(int id, int playerNum){  
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
	                statement = connection.prepareStatement("SELECT * from storage WHERE id = "+id);
	               returned = statement.executeQuery();
	               returned.next();
	               return returned.getString("txt");
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