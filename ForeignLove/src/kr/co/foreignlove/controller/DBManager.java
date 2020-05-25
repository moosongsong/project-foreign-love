package kr.co.foreignlove.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBManager
{
	public static Connection getConnection() throws NamingException, SQLException
	{
		InitialContext ic = new InitialContext();
		DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/moosong");
		return ds.getConnection();
	}

	public static void close(ResultSet rs)
	{
		try
		{
			rs.close();
		} catch (Exception e) {}
	}

	public static void close(Statement stmt)
	{
		try
		{
			stmt.close();
		} catch (Exception e) {}
	}

	public static void close(Connection con)
	{
		try
		{
			con.close();
		} catch (Exception e) {}
	}
	
	public static void close(ResultSet rs, Statement stmt)
	{
		close(rs);
		close(stmt);
	}
	
	public static void close(Statement stmt, Connection con)
	{
		close(stmt);
		close(con);
	}
	
	public static void close(ResultSet rs, Statement stmt, Connection con)
	{
		close(rs);
		close(stmt);
		close(con);
	}
}
