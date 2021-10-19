package cafekiosk.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import cafekiosk.domain.CafeDTO;
import cafekiosk.domain.OrderDTO;

public class OrderDAO {

	public static Vector<OrderDTO> payOrder() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<OrderDTO> list = new Vector<OrderDTO>();

		try {

			con = CafeDAO.getConnection();
			String sql = "select * from orderTBL";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				OrderDTO dto = new OrderDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setCount(rs.getInt("count"));

				list.add(dto);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;

	}
	
	public boolean deleteOrderTBL() {

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean deleteFlag = false;

		try {
			con = CafeDAO.getConnection();
			String sql = "delete orderTBL";
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


	public CafeDTO getRow(int i) {
		
		return null;
	}


}
