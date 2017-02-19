import java.sql.*;  
import com.microsoft.sqlserver.jdbc.*;   

public class uploadKey {
	

	static int id = -9999;
    static int promptNo = -9999;
    static String word = null;
	
	
     public uploadKey(int id, int promptNo, String word){
    	 uploadKey.id = id; //unique identifier in SQL
    	 uploadKey.promptNo = promptNo; // corresponding prompt
    	 uploadKey.word = word; //keyword to be added 
     }
	
	
     	public void upload(int id, int promptNo, String word) {  
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
	                statement = connection.prepareStatement("INSERT INTO keywords (id, promptNo, word) VALUES (?, ?, ?)");
	                statement.setInt(1, id);
	                statement.setInt(2, promptNo);
	                statement.setString(3, word);

	                
	                
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