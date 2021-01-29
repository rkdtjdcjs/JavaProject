package Jdbc0120;
//db작성을 우ㅣ한
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Stu_SQL {
	// DB접속을 위한 변수 con 선언
	Connection con = null;
	
	// 쿼리문 전송을 위한 변수 선언
	Statement stmt = null;
	PreparedStatement pstmt = null;
	//PreparedStatement : ? 를 문자로 인식
	
	// 조회(select) 결과를 저장하기 위한 변수 선언
	ResultSet rs =null;
	
	// DB접속을 위한 메소드
	public void connect() {
		con = DBCon.DBConnect();
		// DBC클래스의 DBConnect()메소드의 리턴값(con)을
		// con에 담겠다
		
	} 
	//DB접속 해제
	public void conClose() {
		System.out.println("DB접속 해제");
		try {
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	//학생 등록을 위한 메소드 insert
	// insert (StuDTO stu) : 파라미터로 StuDTO의 내용을 가져온다
	public void insert(StuDTO stu) {
		String sql = "INSERT INTO STUDTO VALUES(?,?)";
		//stu값(학생정보)과 con(DB연결)값 확인
		System.out.println("학생정보 : " + stu);
		System.out.println("DB연결 : " + con);
		
		//stmt = con,createStatement();
		
		
		
		try {
			pstmt = con.prepareStatement(sql);
			
			//숫자는 물음표 순서대로, ","뒤에는 물음표 안에 들어갈 내용
			pstmt.setString(1, stu.getStuName());
			pstmt.setInt(2, stu.getStuAge());
			
			int count = pstmt.executeUpdate();
			
			if (count > 0 ) {
				System.out.println("학생등록 성공!");
			}else{
				System.out.println("학생등록 실패!");
			}
			
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
			//try : 정상 작동할때
			// catch : 오류 발생할때 (예외처리가 발생 할때)
			// Ex
		try {
			pstmt.close();
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
		
	}
	

// 학생정보를 조회하는 셀렉트 메소드
public void select() {
	String sql = "SELECT * FROM STUDTO";
	try {
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		int i =1;
		while(rs.next()) {		//조회된 데이터의 갯수만큼 반복문을 돌린다
			System.out.println(i +"번째 학생 정보");
			System.out.println("이름  : " + rs.getString(1));
			System.out.println("나이  : " + rs.getInt(2));
			System.out.println();

		}
	} catch (SQLException se) {
		// TODO Auto-generated catch block
		se.printStackTrace();
	}finally{
		try {
			
		}
	}
	
}
}