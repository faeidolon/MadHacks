import java.sql.*;  
import com.microsoft.sqlserver.jdbc.*;   

public class downloadKey {
	
	
	//storage - stores current txt (int idNum, int promptNo, String txt) (limit = 4000);
	
	static int id = -9999;
    static int promptNo = -9999;
    static String txt = null;
	
	
     public downloadKey(int id, int promptNo){
    	 downloadKey.id = id; //unique identifier in SQL
    	 downloadKey.promptNo = promptNo; // corresponding prompt
    	 downloadKey.txt = txt; //keyword to be added 
     }
	
     	public String download(int promptNo){  
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
	                statement = connection.prepareStatement("SELECT * from keywords WHERE promptNo = "+ promptNo);
	               returned = statement.executeQuery();
	               returned.next();
	               return returned.getString("word");
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
