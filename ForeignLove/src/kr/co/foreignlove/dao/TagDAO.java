package kr.co.foreignlove.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.NamingException;

import kr.co.foreignlove.controller.DBManager;
import kr.co.foreignlove.vo.TagVO;

public class TagDAO
{
	public static final String NAME = "Tag";

	public boolean insert(TagVO tag, int b_id)
	{
		String sql = "INSERT INTO tag VALUES(default, ?);";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try
		{
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tag.getT_name());
			result = (pstmt.executeUpdate() > 0);
			
			if(result == true)
			{
				DBManager.close(pstmt);
				int id = getLastInsertId(con);
				tag.setT_id(id);
				
				sql = "INSERT INTO tag_promotion_pivot VALUES(?, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, id);
				pstmt.setInt(2, b_id);
				result = (pstmt.executeUpdate() > 0);
			}
		}
		catch(NamingException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(pstmt, con);
		}
		
		return result;
	}
	
	public boolean delete(int t_id)
	{
		String sql = "DELETE FROM tag WHERE t_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try
		{
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, t_id);
			result = (pstmt.executeUpdate() > 0);
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
			DBManager.close(pstmt, con);
		}
		
		return result;
	}
	
	public TagVO getTag(int t_id)
	{
		String sql = "SELECT * FROM tag WHERE t_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TagVO tag = null;
		
		try
		{
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, t_id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				tag = new TagVO();
				tag.setT_id(rs.getInt("t_id"));
				tag.setT_name(rs.getString("t_name"));
			}
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
			DBManager.close(rs, pstmt, con);
		}
		
		return tag;
	}
	
	public ArrayList<TagVO> getList(String word)
	{
		String sql = "SELECT * FROM tag WHERE t_name LIKE '%" + word + "%' GROUP BY t_name";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<TagVO> list = new ArrayList<>();
		
		try
		{
			con = DBManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				TagVO tag = new TagVO();
				tag.setT_id(rs.getInt("t_id"));
				tag.setT_name(rs.getString("t_name"));
				list.add(tag);
			}
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
			DBManager.close(rs, stmt, con);
		}
		
		return list;
	}
	
	private int getLastInsertId(Connection con) throws SQLException
	{
		String sql = "SELECT LAST_INSERT_ID()";
		Statement stmt = null;
		ResultSet rs = null;
		int id = 0;
		
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		if(rs.next())
		{
			id = rs.getInt(1);
		}
		DBManager.close(rs, stmt);
		return id;
	}
}
