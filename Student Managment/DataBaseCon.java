package MAnagement;

import java.sql.*;
public class DataBaseCon {

	    loginPage lp = new loginPage();
	    
	    static final String DB_URL = "jdbc:mysql://localhost:3307/test";
	    static final String USER = "root";
	    static final String PASS = "";
	    
	    public static Connection connectDB() {
	    	Connection conn = null;
	    	try {
	    		//regester jdbc driver , not required for newer 
	    		Class.forName("com.mysql.jdbc.Driver");
	    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
	    		Statement stmt = conn.createStatement();
	    		
	    		return conn;
	    	}catch(Exception e) {
	    		System.out.println("DATABASE connection faild");
	    		return null;
	    	}
	    }
}
