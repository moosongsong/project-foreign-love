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
import kr.co.foreignlove.vo.BoardVO;
import kr.co.foreignlove.vo.LikeyCountVO;
import kr.co.foreignlove.vo.LikeyVO;
import kr.co.foreignlove.vo.MemberVO;
import kr.co.foreignlove.vo.ReplyVO;

public class LikeyDAO
{
	public final static String NAME = "Likey";

	public int toggle(LikeyVO likey)
	{
		String sql = "INSERT INTO likey VALUES(?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0; // if insert is success, result will be 1 or delete is success, result will be -1 
		int m_id = likey.getM_id().getM_id();
		int r_id = likey.getR_id().getR_id();
		String bt_type = likey.getB_id().getBoardType().getBt_type();
		int b_id = likey.getB_id().getB_id();
		
		try
		{
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, m_id);
			pstmt.setInt(2, r_id);
			pstmt.setString(3, bt_type);
			pstmt.setInt(4, b_id);

			if (pstmt.executeUpdate() > 0)
			{
				result = 1;
			}
		}
		catch(NamingException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			if(e.getMessage().toLowerCase().contains("duplicate")) // if there is already
			{
				// delete
				sql = "DELETE FROM likey WHERE m_id = ? AND r_id = ? AND bt_type = ? AND b_id = ?";
				try
				{
					if(pstmt != null)
					{
						DBManager.close(pstmt);
					}
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, m_id);
					pstmt.setInt(2, r_id);
					pstmt.setString(3, bt_type);
					pstmt.setInt(4, b_id);
					
					if(pstmt.executeUpdate() > 0)
					{
						result = -1;
					}
				}
				catch(SQLException ee)
				{
					ee.printStackTrace();
				}
			}
			else
			{
				e.printStackTrace();
			}
		}
		finally
		{
			DBManager.close(pstmt, con);
		}
		
		return result;
	}
	
	public ArrayList<LikeyVO> getMyList(BoardVO board, MemberVO member)
	{
		String sql = "SELECT * FROM likey WHERE bt_type = '" + board.getBoardType().getBt_type() + "' AND b_id = " + board.getB_id() + " AND m_id = " + member.getM_id();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<LikeyVO> list = new ArrayList<>();
		
		try
		{
			con = DBManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				LikeyVO likey = new LikeyVO();
				likey.setB_id(board);
				likey.setM_id(member);
				
				// if likey of post
				if(rs.getInt("r_id") == 0)
				{
					ReplyVO reply = new ReplyVO();
					reply.setR_id(0);
					reply.setB_id(board);
					likey.setR_id(reply);					
				}
				else // if likey of reply
				{
					ReplyDAO rDAO = (ReplyDAO)DAOManager.getDAO(ReplyDAO.NAME);
					likey.setR_id(rDAO.getReply(con, rs.getInt("r_id")));
				}
				
				list.add(likey);
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
	
	public LikeyCountVO getCount(BoardVO board, int r_id)
	{
		Connection con = null;
		LikeyCountVO likeyCount = null;
		
		try
		{
			con = DBManager.getConnection();
			likeyCount = getCount(con, board, r_id);
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
		
		return likeyCount;
	}
	
	public LikeyCountVO getCount(Connection con, BoardVO board, int r_id)
	{
		String sql = "SELECT COUNT(*) FROM likey WHERE bt_type = '" + board.getBoardType().getBt_type() + "' AND b_id = " + board.getB_id() + " AND r_id = " + r_id;
		
		Statement stmt = null;
		ResultSet rs = null;
		LikeyCountVO likeyCount = null;
		
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next())
			{
				likeyCount = new LikeyCountVO();
				likeyCount.setCount(rs.getInt(1));
				likeyCount.setId(r_id);
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
		
		return likeyCount;
	}
	
	public ArrayList<LikeyCountVO> getCountList(BoardVO board, String type)
	{
		String sql = null;
		
		switch(type)
		{
		case "BOARD":
			sql = "SELECT COUNT(*), b_id FROM likey WHERE bt_type = '" + board.getBoardType().getBt_type() + "' AND r_id = 0 GROUP BY(b_id)";
			break;
		case "POST":
			sql = "SELECT COUNT(*), r_id FROM likey WHERE bt_type = '" + board.getBoardType().getBt_type() + "' AND b_id = " + board.getB_id() + " GROUP BY(r_id)";
			break;
		}
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<LikeyCountVO> list = new ArrayList<>();
		
		try
		{
			con = DBManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				LikeyCountVO likeyCount = new LikeyCountVO();
				
				likeyCount.setCount(rs.getInt(1));
				likeyCount.setId(rs.getInt(2));
				list.add(likeyCount);
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
}
