package kr.co.foreignlove.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.NamingException;

import kr.co.foreignlove.controller.DBManager;
import kr.co.foreignlove.vo.TradeWayVO;

public class TradeWayDAO
{
	public static final String NAME = "TradeWay";

	public boolean insert(TradeWayVO tw_type)
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

	public TradeWayVO find(String tw_type)
	{
		Connection con = null;
		TradeWayVO tradeWay = null;
		
		try
		{
			con = DBManager.getConnection();
			tradeWay = find(con, tw_type);
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
		
		return tradeWay;
	}
	
	public TradeWayVO find(Connection con, String tw_type)
	{
		TradeWayVO twType = new TradeWayVO();

		Statement stmt = null;
		ResultSet rs = null;

		try
		{
			stmt = con.createStatement();
			String sql = "SELECT* FROM tradeWay where tw_type like'" + tw_type + "';";
			rs = stmt.executeQuery(sql);

			if (rs.next())
			{
				twType.setTw_type(rs.getString("tw_type"));
				twType.setTw_typeName(rs.getString("tw_typeName"));
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBManager.close(rs, stmt);
		}
//
		System.out.println(twType);
		return twType;
	}

	public ArrayList<TradeWayVO> getList()
	{
		ArrayList<TradeWayVO> twType = new ArrayList<TradeWayVO>();

		return twType;
	}
}