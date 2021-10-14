package cafekiosk.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cafekiosk.domain.OrderDTO;

public class OrderDAO {

	public static ArrayList<OrderDTO> payOrder() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderDTO> list = new ArrayList<OrderDTO>();

		try {

			con = CafeDAO.getConnection();
			String sql = "select * from orderTBL";
			pstmt = con.prepareStatement(null);
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

}
