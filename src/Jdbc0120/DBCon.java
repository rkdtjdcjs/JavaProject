package Jdbc0120;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
	//DB에 접속하기 위한 메소드 DBConnect()
		public static Connection DBConnect() {
			//DB에 접속정보 저장을 위한 Connection 변수 con 
			Connection con = null;
			
			//접속할 DB의 주소정보
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			//접속할 DB의 계쩡정보
			String user = "KOTLIN01";
			String password = "1111";
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//("oracle.jdbc.driver.OracleDriver"); 오라클에 접속하기 위해 적는다
				// ojdbc6 라이브러리를 현재 소스에 적용
				con = DriverManager.getConnection(url,user,password);
				//con = DriverManager.getConnection("jdbc:oracle:thin:@localhist:1521:xe";)
				// con은 실제 DB와 Java를 연결해 주는 역할
				System.out.println("DB접속 성공!");
			} catch (ClassNotFoundException cne) {
				cne.printStackTrace();
				//printStackTrace(); 에러 발생시 경로를 찾아준다
				System.out.println("DB접속 실패 : 드라이버 로딩 실패");
			} catch (SQLException se) {
				se.printStackTrace();
				System.out.println("DB접속 실패 : 계정정보 확인!");
			}
			
			
			//DB접속이 정상적으로 되면 접속 상태를  리턴해준다.
			return null;
			
		}
}
