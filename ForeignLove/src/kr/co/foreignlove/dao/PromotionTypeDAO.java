package kr.co.foreignlove.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import kr.co.foreignlove.controller.DBManager;
import kr.co.foreignlove.vo.PromotionTypeVO;

public class PromotionTypeDAO
{
	public final static String NAME = "PromotionType";

	public PromotionTypeVO getType(String p_type)
	{
		Connection con = null;
		PromotionTypeVO promotionType = null;
		
		try
		{
			con = DBManager.getConnection();
			promotionType = getType(con, p_type);
		}
		catch(NamingException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(con);
		}
		
		return promotionType;
	}
	
	public PromotionTypeVO getType(Connection con, String p_type)
	{
		String sql = "SELECT * FROM promotionType WHERE p_type = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PromotionTypeVO promotionType = null;
		
		try
		{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, p_type);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				promotionType = new PromotionTypeVO();
				promotionType.setP_name(rs.getString("p_name"));
				promotionType.setP_type(rs.getString("p_type"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(rs, pstmt);
		}
		
		return promotionType;
	}
}
