package cafekiosk.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cafekiosk.domain.UserDTO;

public class CafeDAO {

	static {

		try {

			Class.forName("oracle.jdbc.OracleDriver");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "c##kiosk";
		String password = "12345";
		Connection con = null;

		try {
			con = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;

	}

	public static UserDTO getPoint(String tel) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserDTO dto = null;

		try {
			con = getConnection();

			String sql = "select * from userTBL where tel = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tel);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				dto = new UserDTO();
				dto.setName(rs.getString("name"));
				dto.setNickname(rs.getString("nickname"));
				dto.setPoint(rs.getInt("point"));
				dto.setTel(rs.getString("tel"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return dto;

	}

}
