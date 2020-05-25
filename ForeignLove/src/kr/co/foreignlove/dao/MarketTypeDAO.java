package kr.co.foreignlove.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.NamingException;

import kr.co.foreignlove.controller.DBManager;
import kr.co.foreignlove.vo.MarketTypeVO;

public class MarketTypeDAO
{
	public static final String NAME = "MarketType";

	public boolean insert(MarketTypeVO mk_type)
	{
		boolean result = false;
		
		return result;
	}

	public boolean delete()
	{
		boolean result = false;

		return result;
	}

	public boolean update()
	{
		boolean result = false;

		return result;
	}

	public MarketTypeVO find(String mk_type)
	{
		Connection con = null;
		MarketTypeVO marketType = null;
		
		try
		{
			con = DBManager.getConnection();
			marketType = find(con, mk_type);
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
		
		return marketType;
	}
	
	public MarketTypeVO find(Connection con, String mk_type)
	{
		MarketTypeVO mkType = new MarketTypeVO();

		Statement stmt = null;
		ResultSet rs = null;

		System.out.println(mk_type);
		try
		{
			stmt = con.createStatement();
			String sql = "SELECT* FROM marketType where mk_type like '" + mk_type + "';";
			rs = stmt.executeQuery(sql);
			
			
			if (rs.next())
			{
				mkType.setMk_type(rs.getString("mk_type"));
				mkType.setMk_typeName(rs.getString("mk_typeName"));
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBManager.close(rs, stmt);
		}
//		
		System.out.println(mkType);
		return mkType;
	}

	public ArrayList<MarketTypeVO> getList()
	{
		ArrayList<MarketTypeVO> mkType = new ArrayList<MarketTypeVO>();

		return mkType;
	}
}