package kr.co.foreignlove.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.naming.NamingException;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.controller.DBManager;
import kr.co.foreignlove.vo.BoardVO;
import kr.co.foreignlove.vo.ReplyVO;

public class ReplyDAO
{
	public static final String NAME = "Reply";
	
	public boolean insert(ReplyVO reply)
	{
		String sql = "INSERT INTO reply VALUES(DEFAULT, ?, NULL, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try
		{
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reply.getR_post());
			pstmt.setString(2, reply.getR_content());
			if(reply.getR_highId() != null)
			{
				pstmt.setInt(3, reply.getR_highId().getR_id());				
			}
			else
			{
				pstmt.setNull(3, Types.INTEGER);
			}
			pstmt.setInt(4, reply.getM_id().getM_id());
			pstmt.setString(5, reply.getB_id().getBoardType().getBt_type());
			pstmt.setInt(6, reply.getB_id().getB_id());
			result = (pstmt.executeUpdate() > 0);		
			reply.setR_id(getLastInsertId(con));
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
	
	public boolean delete(int r_id)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String now = sdf.format(calendar.getTime());
		String sql = "UPDATE reply SET r_delete = '" + now + "' WHERE r_id = " + r_id;
		Connection con = null;
		Statement stmt = null;
		boolean result = false;
		
		try
		{
			con = DBManager.getConnection();
			stmt = con.createStatement();
			result = (stmt.executeUpdate(sql) > 0);
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
			DBManager.close(stmt, con);
		}
		
		return result;
	}
	
	public boolean update(ReplyVO reply)
	{
		String sql = "UPDATE reply SET r_content = '" + reply.getR_content() + "' WHERE r_id = " + reply.getR_id();
		Connection con = null;
		Statement stmt = null;
		boolean result = false;
		
		try
		{
			con = DBManager.getConnection();
			stmt = con.createStatement();
			result = (stmt.executeUpdate(sql) > 0);
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
			DBManager.close(stmt, con);
		}
		
		return result;
	}
	
	public ReplyVO getReply(int r_id)
	{
		Connection con = null;
		ReplyVO reply = null;
		
		try
		{
			con = DBManager.getConnection();
			reply = getReply(con, r_id);
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
		
		return reply;
	}
	
	public ReplyVO getReply(Connection con, int r_id)
	{
		// On DB, if r_id = NULL then rs.getInt return 0
		if(r_id == 0)
		{
			return null;
		}
		
		String sql = "SELECT * FROM reply WHERE r_id = " + r_id;
		Statement stmt = null;
		ResultSet rs = null;
		ReplyVO reply = null;
		
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next())
			{
				reply = new ReplyVO();
				reply.setR_id(rs.getInt("r_id"));
				reply.setR_post(rs.getString("r_post"));
				reply.setR_delete(rs.getString("r_delete"));
				reply.setR_content(rs.getString("r_content"));
				reply.setR_highId(getReply(con, rs.getInt("r_highId"))); // On DB if highId = null, return 0
				MemberDAO mDAO = (MemberDAO)DAOManager.getDAO(MemberDAO.NAME);
				reply.setM_id(mDAO.findMember(con, rs.getInt("m_id")));
				
				String strBt_type = rs.getString("bt_type");
				BoardVO board = null;
				int b_id = rs.getInt("b_id");
				
				switch(strBt_type)
				{
				case "FR":
					BoardFreeDAO bfDAO = (BoardFreeDAO)DAOManager.getDAO(BoardFreeDAO.NAME);
					board = bfDAO.getBoard(con, b_id);
					break;
				case "MK":
					BoardMarketDAO bmDAO = (BoardMarketDAO)DAOManager.getDAO(BoardMarketDAO.NAME);
					board = bmDAO.getBoard(con, b_id);
					break;
				case "PR":
					BoardPromotionDAO bpDAO = (BoardPromotionDAO)DAOManager.getDAO(BoardPromotionDAO.NAME);
					board = bpDAO.getBoard(con, b_id);
					break;
				}
				reply.setB_id(board);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(rs, stmt);
		}
		
		return reply;
	}
	
	public ArrayList<ReplyVO> getList(BoardVO board)
	{
		String sql = "SELECT * FROM reply WHERE r_delete IS NULL AND b_id = " + board.getB_id() + " AND bt_type = '" + 
					board.getBoardType().getBt_type() + "' ORDER BY r_id";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<ReplyVO> list = new ArrayList<>();
		
		try
		{
			con = DBManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				ReplyVO reply = new ReplyVO();
				reply.setR_id(rs.getInt("r_id"));
				reply.setR_post(rs.getString("r_post"));
				reply.setR_delete(rs.getString("r_delete"));
				reply.setR_content(rs.getString("r_content"));
				reply.setR_highId(getReply(rs.getInt("r_highId")));
				MemberDAO mDAO = (MemberDAO)DAOManager.getDAO(MemberDAO.NAME);
				reply.setM_id(mDAO.findMember(rs.getInt("m_id")));
				// From replyListService, make board perfectly
				reply.setB_id(board);
				list.add(reply);
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
