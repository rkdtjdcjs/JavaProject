package Project;

import java.sql.*;

public class BudsDAO {
	Connection conn = null;

	PreparedStatement pstmt = null;

	ResultSet rs = null;

//DB접속 해제 메소드 conClose()
	public void connect() {
		conn = DBC.DBConnect();
	}

//DB접속 해제 메소드 conClose()
	public void conClose() {
		try {
			conn.close();
		} catch (SQLException le) {
			le.printStackTrace();
			}
		}
//회원등록
	public void memberJoin(BudsDTO buds) {
		String sql = "INSERT INTO BUDS VALUES(?,?,?,?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, buds.getbId());
			pstmt.setString(2, buds.getbPw());
			pstmt.setString(3, buds.getbName());
			pstmt.setString(4, buds.getbBirth());
			pstmt.setString(5, buds.getbGender());
			pstmt.setString(6, buds.getbEmail());
			pstmt.setString(7, buds.getbPhone());

			pstmt.setString(8, buds.getAccountNumber());
			pstmt.setInt(9, buds.getBalance());
			
			int result = pstmt.executeUpdate();

			if (result > 0) { // 회원가입이 성공하면 1값을 실패하면 값을 리턴해준다
				System.out.println("회원가입 성공!");
			} else {
				System.out.println("회원가입 실패!");
			}

		} catch (SQLException se) {

			se.printStackTrace();
		}
	}

//고객순번 체킹
	public int clientNumber() {
		String sql = "SELECT COUNT(*) FROM BUDS";
		int cNumber = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				cNumber = rs.getInt(1);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return cNumber;
	}

// 로그인, 아이디체크 메소드
	public boolean idCheck(String bId, String bPw) {
		String sql = "SELECT BID FROM BUDS " + "WHERE BID=? AND BPW=?";
		boolean checkResult = false;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bId);
			pstmt.setString(2, bPw);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				checkResult = true;
			} else {
				checkResult = false;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return checkResult;
	}

//회원수정
	public void memberModify(BudsDTO buds) {
		String sql = "UPDATE BUDS SET BPW = ?, BNAME = ?," + "BBIRTH = ?, BGENDER = ?, BEMAIL = ?, BPHONE = ?"
				+ "WHERE BID = ? AND BPW = ?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, buds.getbPw1()); 
			pstmt.setString(2, buds.getbName());
			pstmt.setString(3, buds.getbBirth());
			pstmt.setString(4, buds.getbGender());
			pstmt.setString(5, buds.getbEmail());
			pstmt.setString(6, buds.getbPhone());
			pstmt.setString(7, buds.getbId());
			pstmt.setString(8, buds.getbPw());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("회원정보 수정성공!");
			} else {
				System.out.println("회원정보 수정실패!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//회원탈퇴 시 아이디,비밀번호 확인
	public boolean idCheckForDelete(String bId, String bPw) {

		String sql = "SELECT BID FROM BUDS WHERE BID = ? AND BPW = ?";
		boolean checkResultForDelete = false;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bId);
			pstmt.setString(2, bPw);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				checkResultForDelete = true;
			} else {
				checkResultForDelete = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checkResultForDelete;
	}

//회원삭제메소드
	public void memberDelete(String bId) {
		String sql = "DELETE BUDS WHERE BID = ?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bId);
			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("회원정보 삭제 성공!");
			} else {
				System.out.println("회원정보 삭제 실패!");
			}
		} catch (SQLException ne) {
			ne.printStackTrace();
		}
	}
	
//시세정보 확인(모델명 기준)
	public void marketpriceList2(String mName) {
		String sql = "SELECT MDATE, MPRICE FROM MARKETPRICE WHERE MNAME = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mName);

			rs = pstmt.executeQuery();

			
			int i = 1;
			while (rs.next()) {
				System.out.println(i + "번째 제품");
				System.out.println("해당날짜: " + rs.getString(1));
				System.out.println("가격 : " + rs.getInt(2));
				i++;
				} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 시세정보 확인(기준일 기준)
	public void marketpriceList1(int mDate) {
		// TODO Auto-generated method stub
		String sql = "SELECT MNAME, MPRICE FROM MARKETPRICE WHERE MDATE = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mDate);

			rs = pstmt.executeQuery();
			
			int i = 1;
			while (rs.next()) {
				System.out.println(i + "번째 제품");
				System.out.println("모델명: " + rs.getString(1));
				System.out.println("가격 : " + rs.getInt(2));
				i++;
				} 
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

// 회원정보 조회 메소드
	public void memberList(String bId, String bPw) {
		String sql = "SELECT * FROM BUDS WHERE BID = ? AND BPW = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bId);
			pstmt.setString(2, bPw);

			rs = pstmt.executeQuery();

			int i = 1;
			while (rs.next()) {
				System.out.println(i + "번째 회원");
				System.out.println("아이디 : " + rs.getString(1));
				System.out.println("비밀번호 : " + rs.getString(2));
				System.out.println("이름 : " + rs.getString(3));
				System.out.println("생년월일 : " + rs.getDate(4));
				System.out.println("성별 : " + rs.getString(5));
				System.out.println("이메일 : " + rs.getString(6));
				System.out.println("전화번호 : " + rs.getString(7));
				i++;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

// 계좌 잔액조회 메소드
	public int checkBalance(String AccountNumber) {
		String sql = "SELECT BALANCE FROM BUDS WHERE ACCOUNTNUMBER=?";
		int balance = 0;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, AccountNumber);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				balance = rs.getInt(1);
			}else {
				System.out.println("계좌정보를 조회할 수 없습니다. 계좌번호를다시 확인해주세요!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return balance;
	}

// 계좌정보 수정 메소드
	public void AccModify(BudsDTO buds) {
		String sql = "UPDATE BUDS SET ACCOUNTNUMBER=?, BALANCE=?" + "WHERE BID=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, buds.getAccountNumber());
			pstmt.setInt(2, buds.getBalance());
			pstmt.setString(3, buds.getbId());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("회원정보 수정성공!");
			} else {
				System.out.println("회원정보 수정실패!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
// 입금메소드
	public int deposit(BudsDTO buds) {
		int result = 0;
		String sql = "UPDATE BUDS SET BALANCE = BALANCE+? WHERE ACCOUNTNUMBER=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, buds.getBalance());
			pstmt.setString(2, buds.getAccountNumber());

			result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("입금성공!");
			} else {
				System.out.println("입금실패! 계좌번호를 다시 확인해 주세요!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

// 출금 메소드
	public int withdraw(BudsDTO buds) {
		int result = 0;
		String sql = "UPDATE BUDS SET BALANCE = BALANCE-? WHERE ACCOUNTNUMBER=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, buds.getBalance());
			pstmt.setString(2, buds.getAccountNumber());

			result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("출금성공!");
			} else {
				System.out.println("출금실패!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
