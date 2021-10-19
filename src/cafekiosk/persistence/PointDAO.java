package cafekiosk.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cafekiosk.domain.PointDTO;

public class PointDAO {

	// 포인트사용
	public boolean insertPoint(String tel, int point) {

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean insertFlag = false;

		try {
			con = CafeDAO.getConnection();
			String sql = "insert into pointTBL(tel, point) values(?,?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, tel);
			pstmt.setInt(2, point);

			int result = pstmt.executeUpdate();

			if (result > 0) {
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

	public static PointDTO getrow() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PointDTO dto = null;

		try {

			con = CafeDAO.getConnection();
			String sql = "select * from pointTBL";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				dto = new PointDTO();
				dto.setTel(rs.getString("tel"));
				dto.setPoint(rs.getInt("point"));

			}

		} catch (Exception e) {
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

		return dto;

	}

	// pointTBL 전체 데이터 delete
	public boolean deletePointTBL() {

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean deleteFlag = false;

		try {
			con = CafeDAO.getConnection();
			String sql = "delete pointTBL";
			pstmt = con.prepareStatement(sql);
			int result = pstmt.executeUpdate();

			if (result > 0) {
				deleteFlag = true;
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
		return deleteFlag;

	}

	// 포인트적립
	public boolean plusPoint(String tel, int point) {

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean updateFlag = false;

		try {
			con = CafeDAO.getConnection();
			String sql = "update userTBL set point = point + ? where tel = ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(2, tel);
			pstmt.setInt(1, point);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				updateFlag = true;
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
		return updateFlag;

	}

	// 포인트차감
	public boolean minusPoint(String tel, int point) {

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean updateFlag = false;

		try {
			con = CafeDAO.getConnection();
			String sql = "update userTBL set point = point - ? where tel = ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(2, tel);
			pstmt.setInt(1, point);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				updateFlag = true;
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
		return updateFlag;

	}

}
