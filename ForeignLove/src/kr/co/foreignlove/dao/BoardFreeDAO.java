package kr.co.foreignlove.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.NamingException;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.controller.DBManager;
import kr.co.foreignlove.service.boardfree.BoardFreeGetListService;
import kr.co.foreignlove.vo.BoardFreeVO;
import kr.co.foreignlove.vo.BoardTypeVO;
import kr.co.foreignlove.vo.BoardVO;

public class BoardFreeDAO implements BoardDAO
{
	public final static String NAME = "BoardFree";

	public boolean insert(BoardVO board)
	{
		String sql = "insert into boardFree values(default, ?, ?, default, default, default, ?, ?)";
		BoardFreeVO boardfree = (BoardFreeVO) board;
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try
		{
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardfree.getB_title());
			pstmt.setString(2, boardfree.getB_content());
			pstmt.setString(3, boardfree.getBoardType().getBt_type());
			pstmt.setInt(4, boardfree.getMember().getM_id());

			result = (pstmt.executeUpdate() > 0);
			int id = getLastInsertId(con);
			board.setB_id(id);// id는 db에서 자동증가로 설정되어있으므로 db에가서 마지막id를 가져온다.

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

	public int getLastInsertId(Connection con) throws SQLException
	{
		String sql = "select last_insert_id()";
		Statement stmt = null;
		ResultSet rs = null;
		int id = 0;

		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		if (rs.next())
		{
			id = rs.getInt(1);
		}
		DBManager.close(rs, stmt);
		return id;
	}

	public boolean delete(int b_id)
	{
		String sql = "update boardFree set f_delete= curtime() where f_id = '" + b_id + "' ";
		Connection con = null;
		Statement stmt = null;
		boolean result = false;
 
	try
		{
			con = DBManager.getConnection();
			stmt = con.createStatement();
			result = (stmt.executeUpdate(sql) > 0);
		} catch (Exception e)
		{
			System.err.println(e.getMessage());
		} finally
		{
			DBManager.close(stmt, con);
		}
		return result;

	}

	public boolean update(BoardVO board)
	{
		String sql = "update boardFree set f_title =?, f_content =? where f_id=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		BoardFreeVO boardFree = (BoardFreeVO) board;
		try
		{
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardFree.getB_title());
			pstmt.setString(2, boardFree.getB_content());
			pstmt.setInt(3, boardFree.getB_id());
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

	public BoardFreeVO getBoard(int b_id)
	{
		Connection con = null;
		BoardFreeVO board = null;
		
		try
		{
			con = DBManager.getConnection();
			board = getBoard(con, b_id);
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
		
		return board;
	}
	
	public BoardFreeVO getBoard(Connection con, int b_id)
	{
		String sql = "select * from boardFree join boardType using(bt_type) where f_id = " + b_id;
		Statement stmt = null;
		ResultSet rs = null;
		BoardFreeVO board = null;

		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next())
			{
				board = new BoardFreeVO();
				board.setB_id(rs.getInt("f_id"));
				board.setB_title(rs.getString("f_title"));
				board.setB_content(rs.getString("f_content"));
				board.setB_post(rs.getString("f_post"));
				board.setB_delete(rs.getString("f_delete"));
				board.setB_count(rs.getInt("f_count"));
				BoardTypeVO bt = new BoardTypeVO();
				bt.setBt_type(rs.getString("bt_type"));
				bt.setBt_typeName(rs.getString("bt_typeName"));
				// board를 만들어주는과정
				board.setBoardType(bt);
				MemberDAO mDAO = (MemberDAO)DAOManager.getDAO(MemberDAO.NAME);
				//dao를 가져와서 board에 저장되어있는 m_id를 참고해서 member를 찾아온다.
				board.setMember(mDAO.findMember(con, rs.getInt("m_id")));
			}
		} catch (Exception e)
		{
			System.err.println(e.getMessage());
		} finally
		{
			DBManager.close(rs, stmt);
		}
		return board;
	}

	public int getTotalRecord(String condition, String word)
	{
		StringBuffer sb = new StringBuffer("select count(*) from boardFree ");
		switch (condition)
		{
		case BoardFreeGetListService.CONDITION_TITLE:
			sb.append("where f_delete is null AND f_title like '%" + word + "%' ");
			break;
		case BoardFreeGetListService.CONDITION_CONTENT:
			sb.append("where f_delete is null AND f_content like '%" + word + "%' ");
			break;
		case BoardFreeGetListService.CONDITION_ALL:
			sb.append("where f_delete is null AND (f_title like '%" + word + "%' ");
			sb.append("or f_content like '%" + word + "%') ");
		}
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		int rowCount = 0;

		try
		{
			con = DBManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sb.toString());
			if (rs.next())
			{
				rowCount = rs.getInt(1);
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
		
		return rowCount;
	}

	
	public ArrayList<BoardFreeVO> getList(int page, int pageSize, String condition, String word)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("select * from boardFree join boardType using(bt_type) ");
		switch (condition)
		{
		case BoardFreeGetListService.CONDITION_TITLE:
			sb.append("where f_title like '%" + word + "%' ");
			sb.append("and f_delete is null ");
			break;
		case BoardFreeGetListService.CONDITION_CONTENT:
			sb.append("where f_content like '%" + word + "%' ");
			sb.append("and f_delete is null ");
			break;
		case BoardFreeGetListService.CONDITION_ALL:
			sb.append("where (f_title like '%" + word + "%' ");
			sb.append("or f_content like '%" + word + "%') ");
			sb.append("and f_delete is null ");
		}
		
		int startPos = (page - 1) * pageSize;
		sb.append("order by f_id desc ");
		sb.append("limit " + startPos + ", " + pageSize);

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		BoardFreeVO board;
		ArrayList<BoardFreeVO> list = new ArrayList<>();

		try
		{
			con = DBManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sb.toString());
			
			
			while (rs.next())
			{
				board = new BoardFreeVO();
				board.setB_id(rs.getInt("f_id"));
				board.setB_title(rs.getString("f_title"));
				board.setB_content(rs.getString("f_content"));
				board.setB_post(rs.getString("f_post"));
				board.setB_delete(rs.getString("f_delete"));
				board.setB_count(rs.getInt("f_count"));
				BoardTypeVO bt = new BoardTypeVO();
				bt.setBt_type(rs.getString("bt_type"));
				bt.setBt_typeName(rs.getString("bt_typeName"));
				board.setBoardType(bt);
				MemberDAO mDAO = (MemberDAO)DAOManager.getDAO(MemberDAO.NAME);
				//dao를 가져와서 board에 저장되어있는 m_id를 참고해서 member를 찾아온다.
				board.setMember(mDAO.findMember(con, rs.getInt("m_id")));

				list.add(board);
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
		return list;
	}
	
	public boolean countPlus(int b_id) {
		String sql = "update boardFree set f_count = f_count + 1 where f_id = '"+b_id+"' ";
		Connection con = null;
		Statement stmt = null;
		boolean result = false;
		
		try {
			con = DBManager.getConnection();
			stmt = con.createStatement();
			result = (stmt.executeUpdate(sql)>0);
		}
		catch(NamingException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBManager.close(stmt, con);
		}
		return result;
	}
}
