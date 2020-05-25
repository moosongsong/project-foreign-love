package kr.co.foreignlove.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.NamingException;

import kr.co.foreignlove.controller.DBManager;
import kr.co.foreignlove.vo.AttachedVO;
import kr.co.foreignlove.vo.BoardTypeVO;

public class AttachedDAO {
	public final static String NAME = "Attached";
	
	
	public boolean insert(AttachedVO attached) {
		String sql = "insert into attached values (default, ?, ?, ?) ";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql); 
			pstmt.setString(1, attached.getA_name());
			pstmt.setString(2, attached.getBoardType().getBt_type());
			pstmt.setInt(3, attached.getB_id());
			
			result = (pstmt.executeUpdate()> 0);
			int id = getLastInsertId(con);
			attached.setA_id(id);
			
		}
		catch(NamingException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBManager.close(pstmt, con);
		}
		return result;
		
	}
	
	public int getLastInsertId(Connection con) {
		String sql = "select last_insert_id()";
		Statement stmt = null;
		ResultSet rs = null;
		int id = 0;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				id = rs.getInt(1);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBManager.close(rs, stmt);
		}
		return id;
	}
	
	public boolean delete(int a_id) {
		String sql = "delete from attached where a_id like '"+a_id+"' ";
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
	
	public ArrayList<AttachedVO> getAttached(BoardTypeVO boardType, int b_id) {
		String bt = boardType.getBt_type();
		String sql = "select * from attached where bt_type= '"+ bt+ "' and b_id= "+b_id;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<AttachedVO> attachedList = new ArrayList<>();
		AttachedVO attached = null;
		
		try {
			con = DBManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				attached = new AttachedVO();
				attached.setA_id(rs.getInt("a_id"));
				attached.setA_name(rs.getString("a_name"));
				attached.setBoardType(boardType);
				attached.setB_id(rs.getInt("b_id"));
				attachedList.add(attached);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			DBManager.close(rs, stmt, con);
		}
		return attachedList;
	}
}
