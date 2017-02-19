import java.sql.*;  
import com.microsoft.sqlserver.jdbc.*;   

public class uploadUser {
	

	static int id = -9999;
	static String username = null;
    static String pass = null;
	
	
     public uploadUser(int id, String username, String pass){
    	 uploadUser.id = id; //unique identifier in SQL
    	 uploadUser.username = username; //user's chosen username
    	 uploadUser.pass = pass; //password for the user 
     }
	
	
     	public void upload(int id, String username, String pass) {  
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
	                statement = connection.prepareStatement("INSERT INTO users (id, username, pass) VALUES (?, ?, ?)");
	                statement.setInt(1, id);
	                statement.setString(2, username);
	                statement.setString(3, pass);

	                
	                
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