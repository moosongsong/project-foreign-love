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
import kr.co.foreignlove.service.boardmarket.BoardMarketListService;
import kr.co.foreignlove.vo.BoardMarketVO;
import kr.co.foreignlove.vo.BoardTypeVO;
import kr.co.foreignlove.vo.BoardVO;

public class BoardMarketDAO implements BoardDAO
{
	public static final String NAME = "BoardMarket";
	
	public boolean insert(BoardVO board)
	{
		BoardMarketVO bm = (BoardMarketVO)board;
		
		String sql = "INSERT INTO boardMarket VALUES (default, ?, ?, ?, ?, default, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try
		{
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bm.getB_title());
			pstmt.setString(2, bm.getB_content());
			pstmt.setString(3, bm.getB_post());
			pstmt.setString(4, bm.getB_delete());
			pstmt.setString(5, bm.getMk_price());
			pstmt.setString(6, bm.getMk_area());
			pstmt.setString(7, bm.getBoardType().getBt_type());
			pstmt.setString(8, bm.getLowType().getL_type());
			pstmt.setString(9, bm.getMarketType().getMk_type());
			pstmt.setString(10,  bm.getTradeWay().getTw_type());
			pstmt.setInt(11, bm.getMember().getM_id());

			result = (pstmt.executeUpdate() > 0);
			int id = getLastInsertId(con);
			board.setB_id(id);

		} 
		catch (NamingException e)
		{
			e.printStackTrace();
			//System.err.println(e.getMessage());
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			System.err.println(e.getMessage());
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
		
		String sql = "UPDATE boardMarket SET mk_delete='"+now+"' WHERE mk_id = '"+b_id+"' ";
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
		
		BoardMarketVO bm = (BoardMarketVO)board;
		
		String sql = "UPDATE boardMarket SET mk_title=?, mk_type=?, l_type=?, tw_type=?, mk_price=?, mk_content=? WHERE mk_id=?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try
		{
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bm.getB_title());
			pstmt.setString(2, bm.getMarketType().getMk_type());
			pstmt.setString(3, bm.getLowType().getL_type());
			pstmt.setString(4,  bm.getTradeWay().getTw_type());
			pstmt.setString(5, bm.getMk_price());
			pstmt.setString(6, bm.getB_content());
			pstmt.setInt(7, bm.getB_id());
			
			result = (pstmt.executeUpdate() > 0);
		} 
		catch (NamingException e)
		{
			e.printStackTrace();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		} 
		finally
		{
			DBManager.close( pstmt, con);
		}
		return result;
	}


	public int getLastInsertId(Connection con) throws SQLException
	{
		String sql = "SELECT last_insert_id()";
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

	public BoardMarketVO getBoard(int b_id)
	{
		Connection con = null;
		BoardMarketVO board = null;
		
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
	
	public BoardMarketVO getBoard(Connection con, int b_id)
	{
		BoardMarketVO board = null;
		@SuppressWarnings("unused")
		BoardTypeVO bt=new BoardTypeVO();
	
		String sql = "SELECT* FROM boardMarket JOIN boardType USING(bt_type) WHERE mk_id= "+b_id;

		Statement stmt = null;
		ResultSet rs = null;

		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next())
			{
				board = new BoardMarketVO();
				board.setB_id(rs.getInt("mk_id"));
				board.setB_title(rs.getString("mk_title"));
				board.setB_content(rs.getString("mk_content"));
				board.setB_post(rs.getString("mk_post"));
				board.setB_delete(rs.getString("mk_delete"));
				board.setB_count(rs.getInt("mk_count"));
				board.setMk_price(rs.getString("mk_price"));
				board.setMk_area(rs.getString("mk_area"));
				
				
				BoardTypeDAO btDAO= (BoardTypeDAO)DAOManager.getDAO(BoardTypeDAO.NAME);
				board.setBoardType(btDAO.getBoardTypeVO(con, rs.getString("bt_type")));
				
				LowTypeDAO lDAO = (LowTypeDAO)DAOManager.getDAO(LowTypeDAO.NAME);
				board.setLowType(lDAO.find(con, rs.getString("l_type")));
				
				MarketTypeDAO mkDAO = (MarketTypeDAO)DAOManager.getDAO(MarketTypeDAO.NAME);
				board.setMarketType(mkDAO.find(con, rs.getString("mk_type")));
				
				TradeWayDAO twDAO = (TradeWayDAO)DAOManager.getDAO(TradeWayDAO.NAME);
				board.setTradeWay(twDAO.find(con, rs.getString("tw_type")));
				
				MemberDAO mDAO = (MemberDAO)DAOManager.getDAO(MemberDAO.NAME);
				board.setMember(mDAO.findMember(con, rs.getInt("m_id")));
			}
		} 
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		} 
		finally
		{
			DBManager.close(rs, stmt);
		}
		return board;

	}
	
	public ArrayList<BoardMarketVO> getList(int page, int pageSize, String condition, String word, String type)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM viewBoardMarket ");
		
		switch(condition)
		{
		case BoardMarketListService.CONDITION_TITLE:
			sb.append("WHERE mk_title LIKE '%" + word + "%' AND mk_type LIKE '%" + type + "%' ");
			sb.append("and mk_delete is null ");
			break;
		case BoardMarketListService.CONDITION_CONTENT:
			sb.append("WHERE mk_content LIKE '%" + word + "%' AND mk_type LIKE '%" + type + "%' ");
			sb.append("and mk_delete is null ");
			break;
		default: 
			sb.append("WHERE (mk_title LIKE '%" + word + "%' ");
			sb.append("OR mk_content LIKE '%" + word + "%') AND mk_type LIKE '%" + type + "%' ");
			sb.append("and mk_delete is null ");
			break;
		}
		
		
		int startPos = (page - 1) * pageSize;
		sb.append("ORDER BY mk_id DESC ");
		sb.append("LIMIT " + startPos + ", " + pageSize);
		
		//System.out.println(sb.toString());
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		//System.out.println(sb.toString());
        BoardMarketVO board;
		ArrayList<BoardMarketVO> list = new ArrayList<>();
		try
		{
			con = DBManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sb.toString());
			
			while(rs.next())
			{
				
				board = new BoardMarketVO();
				board.setB_id(rs.getInt("mk_id"));
				board.setB_title(rs.getString("mk_title"));
				board.setB_content(rs.getString("mk_content"));
				board.setB_post(rs.getString("mk_post"));
				board.setB_delete(rs.getString("mk_delete"));
				board.setB_count(rs.getInt("mk_count"));
				board.setMk_price(rs.getString("mk_price"));
				board.setMk_area(rs.getString("mk_area"));
				
				
//				BoardTypeVO boardVO = new BoardTypeVO();
//				boardVO.setBt_type(rs.getString("bt_type"));			
//				
//				LowTypeVO lowVO = new LowTypeVO();
//				lowVO.setL_type(rs.getString("l_type"));
//				
//				MarketTypeVO mkVO = new MarketTypeVO();
//				mkVO.setMk_type(rs.getString("mk_type"));
//				
//				TradeWayVO twVO = new TradeWayVO();
//				twVO.setTw_type(rs.getString("tw_type"));
//				
//				MemberVO mVO = new MemberVO();
//				mVO.setM_id(rs.getInt("m_id"));
				
				
				BoardTypeDAO btDAO= (BoardTypeDAO)DAOManager.getDAO(BoardTypeDAO.NAME);
				board.setBoardType(btDAO.getBoardTypeVO(con, rs.getString("bt_type")));
				
				LowTypeDAO lDAO = (LowTypeDAO)DAOManager.getDAO(LowTypeDAO.NAME);
				board.setLowType(lDAO.find(con, rs.getString("l_type")));
				
				MarketTypeDAO mkDAO = (MarketTypeDAO)DAOManager.getDAO(MarketTypeDAO.NAME);
				board.setMarketType(mkDAO.find(con, rs.getString("mk_type")));
				
				TradeWayDAO twDAO = (TradeWayDAO)DAOManager.getDAO(TradeWayDAO.NAME);
				board.setTradeWay(twDAO.find(con, rs.getString("tw_type")));
				
				MemberDAO mDAO = (MemberDAO)DAOManager.getDAO(MemberDAO.NAME);
				board.setMember(mDAO.findMember(con, rs.getInt("m_id")));
				list.add(board);
		
				
				
//				BoardTypeVO boardVO = new BoardTypeVO();
//				boardVO.setBt_type(rs.getString("bt_type"));			
//				bm.setBoardType(boardVO);
//				
//				LowTypeVO lowVO = new LowTypeVO();
//				lowVO.setL_type(rs.getString("l_type"));
//				
//				MarketTypeVO mkVO = new MarketTypeVO();
//				mkVO.setMk_type(rs.getString("mk_type"));
//				
//				TradeWayVO twVO = new TradeWayVO();
//				twVO.setTw_type(rs.getString("tw_type"));
//				
//				MemberVO mVO = new MemberVO();
//				mVO.setM_id(rs.getInt("m_id"));
				
//				String strBoardType = rs.getString("bt_type");
//				BoardTypeDAO boardTypeDAO = (BoardTypeDAO)DAOManager.getDAO(BoardTypeDAO.NAME);
//				BoardTypeVO boardType = boardTypeDAO.getBoardTypeVO(strBoardType);				
//				bm.setBoardType(boardType);
//				
//				String strLowType = rs.getString("l_type");
//				LowTypeDAO lowTypeDAO = (LowTypeDAO)DAOManager.getDAO(LowTypeDAO.NAME);
//				LowTypeVO lowType=lowTypeDAO.find(strLowType);
//				bm.setLowType(lowType);
//				
	

				
				
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
		StringBuffer sb = new StringBuffer("SELECT COUNT(*) FROM boardMarket ");
		
		switch(condition)
		{
		case BoardMarketListService.CONDITION_TITLE:
			sb.append("WHERE mk_delete IS NULL AND (mk_title LIKE '%" + word + "%' AND mk_type LIKE '%" + type + "%') ");
			break;
		case BoardMarketListService.CONDITION_CONTENT:
			sb.append("WHERE mk_delete IS NULL AND (mk_content LIKE '%" + word + "%' AND mk_type LIKE '%" + type + "%') ");
			break;
		default: 
			sb.append("WHERE mk_delete IS NULL AND (mk_title LIKE '%" + word + "%' ");
			sb.append("OR mk_content LIKE '%" + word + "%') AND mk_type LIKE '%" + type + "%' ");
			break;
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
	public int getTotalRecord(String condition, String word) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean count(int b_id) {
		String sql ="UPDATE boardMarket set mk_count = mk_count+1 WHERE mk_id='"+b_id+"'";
	
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
