import java.sql.*;  
import com.microsoft.sqlserver.jdbc.*;   

public class getNextIndex {
	
	
	//storage - stores current txt (int idNum, int playerNum, String txt) (limit = 4000);
	
	static int id = -9999;
    static String tableName = null;
    static int index = -9999;
    int tempInt = -999;
	
	
		@SuppressWarnings("resource")
		public int getIndex(int id){  
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
	                statement = connection.prepareStatement("SELECT * from pointers WHERE id = "+id);
	                returned = statement.executeQuery();
	                returned.next();
	            return returned.getInt("pointVal");
		             
		               
		               
	            }  
	            catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	            finally {  
	                if (statement != null) try { statement.close(); } catch(Exception e) {}  
	                if (connection != null) try { connection.close(); } catch(Exception e) {}  
	            }
				return -999;  
	        }  
	    
}
