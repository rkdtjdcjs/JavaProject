package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBC {
	// DB 접속을 위한 메소드 DBConnect()
		public static Connection DBConnect() { 
			Connection con = null;
			
			String url = "jdbc:oracle:thin:@localhost:1521:XE";

			String user = "TAMI";
			String password = "1234";
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");

				
				con = DriverManager.getConnection(url,user,password);

				
			} catch (ClassNotFoundException cne) {
				cne.printStackTrace();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			return con;
		}
}
