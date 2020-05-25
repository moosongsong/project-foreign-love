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
import kr.co.foreignlove.vo.DMVO;

public class DMDAO {
	public static final String NAME = "DM";
	
	public boolean insert(DMVO dm) {
		boolean result = false;
		
		Connection con = null;
		Statement stmt = null;
		String sql = "INSERT INTO dm VALUES(DEFAULT, '"+dm.getDm_content()
					+"', DEFAULT, DEFAULT, "+dm.getReceiver_id().getM_id()+", "+dm.getSender_id().getM_id()+" )";
		
		try {
			con = DBManager.getConnection();
			stmt = con.createStatement();
			if (stmt.executeUpdate(sql)>0)
			{
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(stmt,con);
		}
		return result;
	}
	
	public boolean delete(int dm_id) {
		boolean result = false;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM dm where dm_id = ?;";
		
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dm_id);
			if (pstmt.executeUpdate()>0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(pstmt, con);
		}
		
		return result;
	}
	
	public ArrayList<DMVO> getListReceived(int re_id) {
		ArrayList<DMVO> list = new ArrayList<DMVO>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "Select * from dm where receiver_id = "+re_id+" order by dm_id DESC;";
		
		try {
			con = DBManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				DMVO temp = new DMVO();
				temp.setDm_id(rs.getInt("dm_id"));
				temp.setDm_content(rs.getString("dm_content"));
				temp.setDm_sendDate(rs.getString("dm_sendDate"));
				temp.setDm_isChecked(rs.getInt("dm_isCheck"));
				
				MemberDAO mDao = (MemberDAO) DAOManager.getDAO(MemberDAO.NAME);
				temp.setReceiver_id(mDao.findMember(con, rs.getInt("receiver_id")));	
				temp.setSender_id(mDao.findMember(con, rs.getInt("sender_id")));
				
				list.add(temp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs, stmt, con);
		}
		
		return list;
	}
	
	public ArrayList<DMVO> getListSended(int send_id) {
		ArrayList<DMVO> list = new ArrayList<DMVO>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "Select * from dm where sender_id = "+send_id+" order by dm_id DESC;";
		
		try {
			con = DBManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				DMVO temp = new DMVO();
				temp.setDm_id(rs.getInt("dm_id"));
				temp.setDm_content(rs.getString("dm_content"));
				temp.setDm_sendDate(rs.getString("dm_sendDate"));
				temp.setDm_isChecked(rs.getInt("dm_isCheck"));
				
				MemberDAO mDao = (MemberDAO) DAOManager.getDAO(MemberDAO.NAME);
				temp.setReceiver_id(mDao.findMember(con, rs.getInt("receiver_id")));	
				temp.setSender_id(mDao.findMember(con, rs.getInt("sender_id")));
				
				list.add(temp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs, stmt, con);
		}
		
		return list;
	}
	
	public int isAllReaded(int re_id) {
		int result = 0;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM dm WHERE dm.receiver_id = "+re_id+" AND dm.dm_isCheck=0;";
		
		try {
			con = DBManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				result = rs.getInt("COUNT(*)");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs, stmt, con);
		}
		
		return result;
	}
	
	public DMVO find(int dm_id) {
		DMVO dm = null;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * from dm where dm_id = "+dm_id+";";
		
		try {
			con = DBManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				dm = new DMVO();
				
				dm.setDm_id(rs.getInt("dm_id"));
				dm.setDm_content(rs.getString("dm_content"));
				dm.setDm_isChecked(rs.getInt("dm_isCheck"));
				dm.setDm_sendDate(rs.getString("dm_sendDate"));
				
				MemberDAO mDao = (MemberDAO) DAOManager.getDAO(MemberDAO.NAME);
				dm.setReceiver_id(mDao.findMember(rs.getInt("receiver_id")));
				dm.setSender_id(mDao.findMember(rs.getInt("sender_id")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs, stmt, con);
		}
		
		return dm;
	}

	public boolean makeRead(int dm_id) {
		String sql = "update dm set dm_isCheck = 1 where dm_id = "+dm_id+"; ";
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
