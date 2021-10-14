package cafekiosk.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cafekiosk.domain.UserDTO;

public class UserDAO {
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		// 연결 시도
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "c##kiosk";
		String password = "12345";
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	// 회원가입
	public boolean insertUsert(UserDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean insertFlag = false;
		
		try {
			con = getConnection();
			String sql = "insert into userTBL(tel, name, nickname, point) values(?, ?, ?, 0)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getTel());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getNickname());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				insertFlag = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return insertFlag;
	}
	
	// 전화번호 중복 확인
	public int checkOverTel(UserDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			con = getConnection();
			
			String sql = "select count(*) from userTBL where tel=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getTel());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt("count(*)");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
