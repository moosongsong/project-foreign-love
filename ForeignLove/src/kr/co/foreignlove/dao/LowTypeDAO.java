package kr.co.foreignlove.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.NamingException;

import kr.co.foreignlove.controller.DBManager;
import kr.co.foreignlove.vo.LowTypeVO;

public class LowTypeDAO
{
	public static final String NAME = "LowType";

	public boolean insert(LowTypeVO l_type)
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

	public LowTypeVO find(String l_type)
	{
		Connection con = null;
		LowTypeVO lowType = null;
		
		try
		{
			con = DBManager.getConnection();
			lowType = find(con, l_type);
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
		
		return lowType;
	}
	
	public LowTypeVO find(Connection con, String l_type)
	{
		LowTypeVO lType = new LowTypeVO();

		Statement stmt = null;
		ResultSet rs = null;

		try
		{
			stmt = con.createStatement();
			String sql = "SELECT* FROM lowType where l_type like'" + l_type + "';";
			rs = stmt.executeQuery(sql);

			if (rs.next())
			{
				lType.setL_type(rs.getString("l_type"));
				lType.setL_typeName(rs.getString("l_typeName"));
			}
		}catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBManager.close(rs, stmt);
		}

		return lType;
	}

	public ArrayList<LowTypeVO> getList()
	{
		ArrayList<LowTypeVO> lType = new ArrayList<LowTypeVO>();

		return lType;
	}
}