package kr.co.foreignlove.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import kr.co.foreignlove.controller.DBManager;
import kr.co.foreignlove.vo.BoardTypeVO;

public class BoardTypeDAO
{
	public final static String NAME = "BoardType";

	public boolean insert(BoardTypeVO bt)
	{
		String sql = "insert into boardType values(?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try
		{
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bt.getBt_type());
			pstmt.setString(2, bt.getBt_typeName());
			result = (pstmt.executeUpdate() > 0);
		} catch (NamingException e)
		{
			System.err.println(e.getMessage());
		} catch (SQLException e)
		{
			System.err.println(e.getMessage());
		} finally
		{
			DBManager.close(pstmt, con);
		}
		return result;
	}

	public boolean delete(String bt_type)
	{
		String sql = "delete from boardType where bt_type = '" + bt_type + "' ";
		Connection con = null;
		Statement stmt = null;
		boolean result = false;

		try
		{
			con = DBManager.getConnection();
			stmt = con.createStatement();
			result = (stmt.executeUpdate(sql) > 0);
		} catch (NamingException e)
		{
			System.err.println(e.getMessage());
		} catch (SQLException e)
		{
			System.err.println(e.getMessage());
		} finally
		{
			DBManager.close(stmt, con);
		}
		return result;

	}

	public BoardTypeVO getBoardTypeVO(String bt_type)
	{
		Connection con = null;
		BoardTypeVO boardType = null;
		
		try
		{
			con = DBManager.getConnection();
			boardType = getBoardTypeVO(con, bt_type);
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
		
		return boardType;
	}
	
	public BoardTypeVO getBoardTypeVO(Connection con, String bt_type)
	{
		String sql = "select * from boardType where bt_type = '" + bt_type + "' ";
		
		Statement stmt = null;
		ResultSet rs = null;
		BoardTypeVO boardType = new BoardTypeVO();
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				boardType.setBt_type(rs.getString("bt_type"));
				boardType.setBt_typeName(rs.getString("bt_typeName"));
			}
		} catch (SQLException e)
		{
			System.err.println(e.getMessage());
		} finally
		{
			DBManager.close(rs, stmt);
		}
		return boardType;
	}
}
