import java.sql.*;  
import com.microsoft.sqlserver.jdbc.*;   

public class DownloadPass {
	
	
	//storage - stores current txt (int idNum, int playerNum, String txt) (limit = 4000);
	
	static int id = -9999;
    
		public String download(int id){  
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
	                statement = connection.prepareStatement("SELECT * from users WHERE id = " + id);
	                returned = statement.executeQuery();
	                returned.next();
	               return returned.getString("pass");
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