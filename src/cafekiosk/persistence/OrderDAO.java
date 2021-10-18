package cafekiosk.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

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
	
	public boolean payPoint(int point) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean insertFlag = false;
		
		try {
			con = CafeDAO.getConnection();
			String sql = "insert into orderTBL(point) values(?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, point);
					
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
	
//	public static OrderDTO getPoint() {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//
//		try {
//
//			con = CafeDAO.getConnection();
//			String sql = "select point from orderTBL";
//			pstmt = con.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//
//				OrderDTO dto = new OrderDTO();
//				dto.setNo(rs.getInt("no"));
//				dto.setName(rs.getString("name"));
//				dto.setPrice(rs.getInt("price"));
//				dto.setCount(rs.getInt("count"));
//
//				
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				rs.close();
//				pstmt.close();
//				con.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return list;
//
//	}
	
	
	

}
