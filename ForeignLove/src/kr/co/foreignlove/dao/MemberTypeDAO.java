package kr.co.foreignlove.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.NamingException;

import kr.co.foreignlove.controller.DBManager;
import kr.co.foreignlove.vo.MemberTypeVO;

public class MemberTypeDAO
{
	public static final String NAME = "MemberType";

	public boolean insert(MemberTypeVO m_type)
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

	public MemberTypeVO find(Connection con, String m_type)
	{
		MemberTypeVO type = new MemberTypeVO();

		Statement stmt = null;
		ResultSet rs = null;

		try
		{
			stmt = con.createStatement();
			String sql = "Select * from memberType where m_type like'" + m_type + "';";
			rs = stmt.executeQuery(sql);

			if (rs.next())
			{
				type.setM_type(rs.getString("m_type"));
				type.setM_typeName(rs.getString("m_typeName"));
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBManager.close(rs, stmt);
		}

		return type;
	}
	
	public MemberTypeVO find(String m_type)
	{
		MemberTypeVO type = null;
		
		Connection con = null;

		try
		{
			con = DBManager.getConnection();
			type = find(con,m_type);
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally
		{
			DBManager.close(con);
		}

		return type;
	}

	public ArrayList<MemberTypeVO> getList()
	{
		ArrayList<MemberTypeVO> types = new ArrayList<MemberTypeVO>();

		return types;
	}
}
