package kr.co.foreignlove.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.naming.NamingException;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.controller.DBManager;
import kr.co.foreignlove.service.boardpromotion.BoardPromotionListService;
import kr.co.foreignlove.vo.BoardPromotionVO;
import kr.co.foreignlove.vo.BoardVO;

public class BoardPromotionDAO implements BoardDAO
{
	public static final String NAME = "BoardPromotion";

	public boolean insert(BoardVO board)
	{
		BoardPromotionVO bp = (BoardPromotionVO)board;
		
		String sql = "INSERT INTO boardPromotion VALUES(default, ?, ?, ?, ?, default, ?, ?, ?, ?, ?)";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try
		{
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bp.getB_title());
			pstmt.setString(2, bp.getB_content());
			pstmt.setString(3, bp.getB_post());
			pstmt.setString(4, bp.getB_delete());
			pstmt.setString(5, bp.getB_startDate());
			pstmt.setString(6, bp.getB_endDate());
			pstmt.setString(7, bp.getBoardType().getBt_type());
			pstmt.setString(8, bp.getP_type().getP_type());
			pstmt.setInt(9, bp.getMember().getM_id());
			
			result = (pstmt.executeUpdate() > 0);
			int id = getLastInsertID(con);
			board.setB_id(id);
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

	public boolean delete(int b_id)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String now = sdf.format(calendar.getTime());
		String sql = "UPDATE boardPromotion SET p_delete = '" + now + "' WHERE p_id = " + b_id;
		
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

	public boolean update(BoardVO board)
	{
		BoardPromotionVO bp = (BoardPromotionVO)board;
		
		String sql = "UPDATE boardPromotion SET p_title = ?, p_content = ?, p_startDate = ?, p_endDate = ?, p_type = ? WHERE p_id = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try
		{
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bp.getB_title());
			pstmt.setString(2, bp.getB_content());
			pstmt.setString(3, bp.getB_startDate());
			pstmt.setString(4, bp.getB_endDate());
			pstmt.setString(5, bp.getP_type().getP_type());
			pstmt.setInt(6, bp.getB_id());
			
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

	public BoardPromotionVO getBoard(int b_id)
	{
		Connection con = null;
		BoardPromotionVO board = null;
		
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

	public BoardPromotionVO getBoard(Connection con, int b_id)
	{
		String sql = "SELECT * FROM boardPromotion WHERE p_id = " + b_id;
		
		Statement stmt = null;
		ResultSet rs = null;
		BoardPromotionVO board = null;
		
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next())
			{
				board = new BoardPromotionVO();
				board.setB_id(rs.getInt("p_id"));
				board.setB_title(rs.getString("p_title"));
				board.setB_content(rs.getString("p_content"));
				board.setB_post(rs.getString("p_post"));
				board.setB_delete(rs.getString("p_delete"));
				board.setB_count(rs.getInt("p_count"));
				board.setB_startDate(rs.getString("p_startDate"));
				board.setB_endDate(rs.getString("p_endDate"));
				BoardTypeDAO btDAO= (BoardTypeDAO)DAOManager.getDAO(BoardTypeDAO.NAME);
				board.setBoardType(btDAO.getBoardTypeVO(con, rs.getString("bt_type")));
				PromotionTypeDAO ptDAO = (PromotionTypeDAO)DAOManager.getDAO(PromotionTypeDAO.NAME);
				board.setP_type(ptDAO.getType(con, rs.getString("p_type")));
				MemberDAO mDAO = (MemberDAO)DAOManager.getDAO(MemberDAO.NAME);
				board.setMember(mDAO.findMember(con, rs.getInt("m_id")));
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
		
		return board;
	}
	
	public ArrayList<BoardPromotionVO> getList(int page, int pageSize, String condition, String word, String type)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM boardPromotion ");
		
		// SELECT * FROM boardPromotion WHERE END
		if(type.equals("END"))
		{
			sb.append("WHERE p_endDate < CURRENT_TIMESTAMP() AND p_delete IS NULL");
		}
		else
		{
			switch(condition)
			{
			case BoardPromotionListService.CONDITION_TITLE:
				sb.append("WHERE p_endDate > CURRENT_TIMESTAMP() AND p_delete IS NULL AND (p_title LIKE '%" + word + "%' AND p_type LIKE '%" + type + "%') ");
				break;
			case BoardPromotionListService.CONDITION_CONTENT:
				sb.append("WHERE p_endDate > CURRENT_TIMESTAMP() AND p_delete IS NULL AND (p_content LIKE '%" + word + "%' AND p_type LIKE '%" + type + "%') ");
				break;
			case BoardPromotionListService.CONDITION_TAG:
				sb.append("JOIN tag_promotion_pivot USING(p_id) JOIN tag USING(t_id) WHERE p_endDate > CURRENT_TIMESTAMP() AND p_delete IS NULL AND t_name LIKE '%" + word + "%' ");
				break;
			default: 
				sb.append("WHERE p_endDate > CURRENT_TIMESTAMP() AND p_delete IS NULL AND (p_title LIKE '%" + word + "%' ");
				sb.append("OR p_content LIKE '%" + word + "%') AND p_type LIKE '%" + type + "%' ");
				break;
			}
		}
		
		int startPos = (page - 1) * pageSize;
		sb.append("ORDER BY p_id DESC ");
		sb.append("LIMIT " + startPos + ", " + pageSize);
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		ArrayList<BoardPromotionVO> list = new ArrayList<>();
		try
		{
			con = DBManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sb.toString());
			
			while(rs.next())
			{
				BoardPromotionVO board = new BoardPromotionVO();
				board.setB_id(rs.getInt("p_id"));
				board.setB_title(rs.getString("p_title"));
				board.setB_content(rs.getString("p_content"));
				board.setB_post(rs.getString("p_post"));
				board.setB_delete(rs.getString("p_delete"));
				board.setB_count(rs.getInt("p_count"));
				board.setB_startDate(rs.getString("p_startDate"));
				board.setB_endDate(rs.getString("p_endDate"));
				BoardTypeDAO btDAO= (BoardTypeDAO)DAOManager.getDAO(BoardTypeDAO.NAME);
				board.setBoardType(btDAO.getBoardTypeVO(con, rs.getString("bt_type")));
				PromotionTypeDAO ptDAO = (PromotionTypeDAO)DAOManager.getDAO(PromotionTypeDAO.NAME);
				board.setP_type(ptDAO.getType(con, rs.getString("p_type")));
				MemberDAO mDAO = (MemberDAO)DAOManager.getDAO(MemberDAO.NAME);
				board.setMember(mDAO.findMember(con, rs.getInt("m_id")));
				list.add(board);
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

	public int getTotalRecord(String condition, String word, String type)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(*) FROM boardPromotion ");
		
		// SELECT * FROM boardPromotion WHERE END
		if(type.equals("END"))
		{
			sb.append("WHERE p_endDate < CURRENT_TIMESTAMP() AND p_delete IS NULL");
		}
		else
		{
			switch(condition)
			{
			case BoardPromotionListService.CONDITION_TITLE:
				sb.append("WHERE p_endDate > CURRENT_TIMESTAMP() AND p_delete IS NULL AND (p_title LIKE '%" + word + "%' AND p_type LIKE '%" + type + "%') ");
				break;
			case BoardPromotionListService.CONDITION_CONTENT:
				sb.append("WHERE p_endDate > CURRENT_TIMESTAMP() AND p_delete IS NULL AND (p_content LIKE '%" + word + "%' AND p_type LIKE '%" + type + "%') ");
				break;
			case BoardPromotionListService.CONDITION_TAG:
				sb.append("JOIN tag_promotion_pivot USING(p_id) JOIN tag USING(t_id) WHERE p_endDate > CURRENT_TIMESTAMP() AND p_delete IS NULL AND t_name LIKE '%" + word + "%' ");
				break;
			default: 
				sb.append("WHERE p_endDate > CURRENT_TIMESTAMP() AND p_delete IS NULL AND (p_title LIKE '%" + word + "%' ");
				sb.append("OR p_content LIKE '%" + word + "%') AND p_type LIKE '%" + type + "%' ");
				break;
			}
		}
		
		sb.append("ORDER BY p_id DESC");
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		int rowCount = 0;
		
		try
		{
			con = DBManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sb.toString());
			if(rs.next())
			{
				rowCount = rs.getInt(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(rs, stmt, con);
		}
		
		return rowCount;
	}

	@Override
	public int getTotalRecord(String condition, String word)
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	private int getLastInsertID(Connection con) throws SQLException
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
	
	public void countPlus(int b_id)
	{
		String sql = "update boardPromotion set p_count = p_count + 1 where p_id = '" + b_id + "' ";
		
		Connection con = null;
		Statement stmt = null;
		
		try 
		{
			con = DBManager.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
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
	}
}