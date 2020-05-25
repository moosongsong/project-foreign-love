package kr.co.foreignlove.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.NamingException;

import kr.co.foreignlove.controller.DBManager;
import kr.co.foreignlove.vo.SchoolVO;

public class SchoolDAO
{
	public static final String NAME = "School";

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

	public SchoolVO find(String s_id)
	{
		SchoolVO school = new SchoolVO();

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try
		{
			con = DBManager.getConnection();
			stmt = con.createStatement();
			String sql = "Select * from school where s_id like'" + s_id + "';";
			rs = stmt.executeQuery(sql);

			if (rs.next())
			{
				school.setS_id(rs.getString("s_id"));
				school.setS_name(rs.getString("s_name"));
				school.setS_nation(rs.getString("s_nation"));
				school.setS_state(rs.getString("s_state"));
			}
		} catch (NamingException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBManager.close(rs, stmt, con);
		}

		return school;
	}
	
	public SchoolVO find(Connection con, String s_id)
	{
		SchoolVO school = new SchoolVO();

		Statement stmt = null;
		ResultSet rs = null;

		try
		{
			stmt = con.createStatement();
			String sql = "Select * from school where s_id like'" + s_id + "';";
			rs = stmt.executeQuery(sql);

			if (rs.next())
			{
				school.setS_id(rs.getString("s_id"));
				school.setS_name(rs.getString("s_name"));
				school.setS_nation(rs.getString("s_nation"));
				school.setS_state(rs.getString("s_state"));
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBManager.close(rs, stmt);
		}

		return school;
	}

	public ArrayList<SchoolVO> searchForRegister(String s_name)
	{
		ArrayList<SchoolVO> schools = new ArrayList<SchoolVO>();
		String sql = "Select * from school where s_name like'%" + s_name + "%' order by s_name ASC;";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try
		{
			con = DBManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				SchoolVO temp = new SchoolVO();
				temp.setS_id(rs.getString("s_id"));
				temp.setS_name(rs.getString("s_name"));
				temp.setS_nation(rs.getString("s_nation"));
				temp.setS_state(rs.getString("s_state"));
				schools.add(temp);
			}
			
		} catch (NamingException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBManager.close(rs, stmt, con);
		}

		return schools;
	}
}
