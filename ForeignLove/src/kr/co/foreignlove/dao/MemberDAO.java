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
import kr.co.foreignlove.service.member.MemberListService;
import kr.co.foreignlove.vo.MemberVO;

public class MemberDAO
{
	public static final String NAME = "Member";

	public boolean insert(MemberVO member)
	{
		boolean result = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO members VALUES(?, ?, password(?), ?, ?, null, ?, DEFAULT, ?, null, ?, ?, ?, ?, ? )";
		
		try
		{
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getM_email());
			pstmt.setString(2, member.getM_name());
			pstmt.setString(3, member.getM_pass());
			pstmt.setString(4, member.getM_phone());
			pstmt.setString(5, member.getM_birth());
			//null
			pstmt.setString(6, member.getM_nick());
			//default
			pstmt.setString(7, member.getM_regDate());
			//remdate
			pstmt.setString(8, member.getM_sex());
			pstmt.setString(9, member.getM_startDate());
			pstmt.setString(10, member.getM_addr());
			pstmt.setString(11, member.getS_id().getS_id());
			pstmt.setString(12, member.getM_type().getM_type());

			
			if (pstmt.executeUpdate()>0)
			{
				result = true;
			}
		} catch (NamingException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBManager.close(pstmt,con);
		}
		return result;
	}

	public boolean delete(String m_email)
	{
		boolean result = false;

		Connection con = null;
		Statement stmt = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		
		String m_remDate = sdf.format(cal.getTime());
		try
		{
			con = DBManager.getConnection();
			stmt = con.createStatement();
			String sql = "UPDATE members SET m_remDate ='"+m_remDate+"' where m_email like '" + m_email + "';";
			
			if (stmt.executeUpdate(sql) > 0)
			{
				result = true;
			}
		} catch (NamingException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBManager.close(stmt, con);
		}
		return result;
	}

	public boolean update(MemberVO member)
	{
		boolean result = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE members SET m_pass=password(?), m_phone=?, m_nick=?, m_startDate=?, m_addr=?, s_id=?, m_type=? where m_email=?;";
		try
		{
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getM_pass());
			pstmt.setString(2, member.getM_phone());
			pstmt.setString(3, member.getM_nick());
			pstmt.setString(4, member.getM_startDate());
			pstmt.setString(5, member.getM_addr());
			pstmt.setString(6, member.getS_id().getS_id());
			pstmt.setString(7, member.getM_type().getM_type());
			pstmt.setString(8, member.getM_email());

			if (pstmt.executeUpdate()>0)
			{
				result = true;
			}
		} catch (NamingException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBManager.close(pstmt, con);
		}
		return result;
	}

	public MemberVO findMember(int m_id)
	{
		MemberVO member = null;

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try
		{
			con = DBManager.getConnection();
			stmt = con.createStatement();
			String sql = "Select * from members where m_id=" + m_id + ";";
			rs = stmt.executeQuery(sql);

			if (rs.next())
			{
				member = new MemberVO();
				member.setM_addr(rs.getString("m_addr"));
				member.setM_birth(rs.getString("m_birth"));
				member.setM_email(rs.getString("m_email"));
				member.setM_id(rs.getInt("m_id"));
				member.setM_name(rs.getString("m_name"));
				member.setM_nick(rs.getString("m_nick"));
				member.setM_pass(rs.getString("m_pass"));
				member.setM_phone(rs.getString("m_phone"));
				member.setM_regDate(rs.getString("m_regDate"));
				member.setM_remDate(rs.getString("m_remDate"));
				
				if(member.getM_remDate() != null) {
					member.setM_nick("(알수없음)");
				}
				
				member.setM_sex(rs.getString("m_sex"));
				member.setM_startDate(rs.getString("m_startDate"));

				String s_id = rs.getString("s_id");
				String m_type = rs.getString("m_type");

				MemberTypeDAO mDao = (MemberTypeDAO)DAOManager.getDAO(MemberTypeDAO.NAME);
				member.setM_type(mDao.find(con, m_type));

				SchoolDAO sDao = (SchoolDAO)DAOManager.getDAO(SchoolDAO.NAME);
				member.setS_id(sDao.find(s_id)); 
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
		return member;
	}
	
	public MemberVO findMember(Connection con, int m_id)
	{
		MemberVO member = null;

		Statement stmt = null;
		ResultSet rs = null;

		try
		{
			stmt = con.createStatement();
			String sql = "Select * from members where m_id = "+ m_id +" ;";
			rs = stmt.executeQuery(sql);

			if (rs.next())
			{
				member = new MemberVO();
				member.setM_addr(rs.getString("m_addr"));
				member.setM_birth(rs.getString("m_birth"));
				member.setM_email(rs.getString("m_email"));
				member.setM_id(rs.getInt("m_id"));
				member.setM_name(rs.getString("m_name"));
				member.setM_nick(rs.getString("m_nick"));
				member.setM_pass(rs.getString("m_pass"));
				member.setM_phone(rs.getString("m_phone"));
				member.setM_regDate(rs.getString("m_regDate"));
				member.setM_remDate(rs.getString("m_remDate"));
				
				if(member.getM_remDate() != null) {
					member.setM_nick("(알수없음)");
				}
				
				member.setM_sex(rs.getString("m_sex"));
				member.setM_startDate(rs.getString("m_startDate"));

				String s_id = rs.getString("s_id");
				String m_type = rs.getString("m_type");

				MemberTypeDAO mDao = (MemberTypeDAO)DAOManager.getDAO(MemberTypeDAO.NAME);
				member.setM_type(mDao.find(con, m_type));

				SchoolDAO sDao = (SchoolDAO)DAOManager.getDAO(SchoolDAO.NAME);
				member.setS_id(sDao.find(s_id));
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBManager.close(rs, stmt);
		}
		return member;
	}

	public MemberVO findMember(String email, String pass)
	{
		MemberVO member = null;
		String sql = "Select * from members where m_email like ? and m_pass = password(?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();

			if (rs.next())
			{
				member = new MemberVO();
				member.setM_addr(rs.getString("m_addr"));
				member.setM_birth(rs.getString("m_birth"));
				member.setM_email(rs.getString("m_email"));
				member.setM_id(rs.getInt("m_id"));
				member.setM_name(rs.getString("m_name"));
				member.setM_nick(rs.getString("m_nick"));
				member.setM_pass(rs.getString("m_pass"));
				member.setM_phone(rs.getString("m_phone"));
				member.setM_regDate(rs.getString("m_regDate"));
				member.setM_remDate(rs.getString("m_remDate"));
				
				if(member.getM_remDate() != null) {
					member.setM_nick("(알수없음)");
				}
				
				member.setM_sex(rs.getString("m_sex"));
				member.setM_startDate(rs.getString("m_startDate"));

				String s_id = rs.getString("s_id");
				String m_type = rs.getString("m_type");

				MemberTypeDAO mDao = (MemberTypeDAO) DAOManager.getDAO("MemberType");
				member.setM_type(mDao.find(con, m_type));

				SchoolDAO sDao = (SchoolDAO) DAOManager.getDAO("School");
				member.setS_id(sDao.find(s_id));
			}
		} catch (NamingException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBManager.close(rs, pstmt, con);
		}
		return member;
	}
	
	public MemberVO findForEmail(String email) {
		MemberVO member = null;
		String sql = "Select * from members where m_email like ? ;";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if (rs.next())
			{
				member = new MemberVO();
				member.setM_addr(rs.getString("m_addr"));
				member.setM_birth(rs.getString("m_birth"));
				member.setM_email(rs.getString("m_email"));
				member.setM_id(rs.getInt("m_id"));
				member.setM_name(rs.getString("m_name"));
				member.setM_nick(rs.getString("m_nick"));
				member.setM_pass(rs.getString("m_pass"));
				member.setM_phone(rs.getString("m_phone"));
				member.setM_regDate(rs.getString("m_regDate"));
				member.setM_remDate(rs.getString("m_remDate"));
				
				if(member.getM_remDate() != null) {
					member.setM_nick("(알수없음)");
				}
				
				member.setM_sex(rs.getString("m_sex"));
				member.setM_startDate(rs.getString("m_startDate"));

				String s_id = rs.getString("s_id");
				String m_type = rs.getString("m_type");

				MemberTypeDAO mDao = (MemberTypeDAO) DAOManager.getDAO("MemberType");
				member.setM_type(mDao.find(con, m_type));

				SchoolDAO sDao = (SchoolDAO) DAOManager.getDAO("School");
				member.setS_id(sDao.find(s_id));
			}
		} catch (NamingException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBManager.close(rs, pstmt, con);
		}
		return member;
	}
	
	public MemberVO findForNick (String nick) {
		MemberVO member = null;
		String sql="Select * from members where m_nick like ? ;";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nick);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new MemberVO();
				member.setM_addr(rs.getString("m_addr"));
				member.setM_birth(rs.getString("m_birth"));
				member.setM_email(rs.getString("m_email"));
				member.setM_id(rs.getInt("m_id"));
				member.setM_name(rs.getString("m_name"));
				member.setM_nick(rs.getString("m_nick"));
				member.setM_pass(rs.getString("m_pass"));
				member.setM_phone(rs.getString("m_phone"));
				member.setM_regDate(rs.getString("m_regDate"));
				member.setM_remDate(rs.getString("m_remDate"));
				
				if(member.getM_remDate() != null) {
					member.setM_nick("(알수없음)");
				}
				
				member.setM_sex(rs.getString("m_sex"));
				member.setM_startDate(rs.getString("m_startDate"));
				
				String s_id = rs.getString("s_id");
				String m_type = rs.getString("m_type");
				
				MemberTypeDAO mDao = (MemberTypeDAO) DAOManager.getDAO("MemberType");
				member.setM_type(mDao.find(con, m_type));

				SchoolDAO sDao = (SchoolDAO) DAOManager.getDAO("School");
				member.setS_id(sDao.find(s_id));
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs, pstmt, con);
		}		
		return member;
	}
	
	public int dateDiff(String m_startDate , String m_regDate) {
		int result = 0;
		String sql="Select DATEDIFF(?, ?) as res;";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(2, m_regDate);
			pstmt.setString(1, m_startDate);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("res");
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs, pstmt, con);
		}		
		return result;
	}
	
	public boolean resetPassword(MemberVO member, String str)
	{
		boolean result = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE members SET m_pass=password(?) where m_email=?;";
		try
		{
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, str);
			pstmt.setString(2, member.getM_email());

			if (pstmt.executeUpdate()>0)
			{
				result = true;
			}
		} catch (NamingException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBManager.close(pstmt, con);
		}
		return result;
	}
	
	@SuppressWarnings("unused")
	public ArrayList<MemberVO> getList(MemberVO member, String condition) {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		String school = member.getS_id().getS_id();
		String state = member.getS_id().getS_state();
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM members, school ");
		
		if(member == null) {
			return null;
		}
		
		switch (condition) {
		case MemberListService.SCHOOL_NAME:
			sb.append("WHERE s_id LIKE '" + school +"' ");
			break;
		case MemberListService.SCHOOL_STATE:
			sb.append("WHERE s_state LIKE '" + state + "' ");
			break;
		default:
			sb.append("WHERE members.s_id LIKE '" + school + "' AND s_state LIKE '" + state + "' ");
			break;
		}
		sb.append("AND members.s_id=school.s_id AND m_remDate IS NULL AND members.m_id!="+member.getM_id()+" ORDER BY m_type DESC, m_nick ASC ");
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			stmt = con.createStatement();
			System.out.println(sb.toString());
			rs = stmt.executeQuery(sb.toString());
			
			while(rs.next()) {
				MemberVO temp = new MemberVO();
				
				temp.setM_addr(rs.getString("m_addr"));
				temp.setM_birth(rs.getString("m_birth"));
				temp.setM_email(rs.getString("m_email"));
				temp.setM_id(rs.getInt("m_id"));
				temp.setM_name(rs.getString("m_name"));
				temp.setM_nick(rs.getString("m_nick"));
				temp.setM_pass(rs.getString("m_pass"));
				temp.setM_phone(rs.getString("m_phone"));
				temp.setM_regDate(rs.getString("m_regDate"));
				temp.setM_remDate(rs.getString("m_remDate"));
				
				if(member.getM_remDate() != null) {
					member.setM_nick("(알수없음)");
				}
				
				temp.setM_sex(rs.getString("m_sex"));
				temp.setM_startDate(rs.getString("m_startDate"));
				
				String s_id = rs.getString("s_id");
				String m_type = rs.getString("m_type");
				
				MemberTypeDAO mDao = (MemberTypeDAO) DAOManager.getDAO("MemberType");
				temp.setM_type(mDao.find(con, m_type));

				SchoolDAO sDao = (SchoolDAO) DAOManager.getDAO("School");
				temp.setS_id(sDao.find(con, s_id));
				
				list.add(temp);
			}

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs, stmt, con);
		}		
		return list;
	}
}
