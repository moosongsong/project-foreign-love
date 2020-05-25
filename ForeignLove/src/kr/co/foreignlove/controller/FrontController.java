package kr.co.foreignlove.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FrontController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	HashMap<String, BackController> services;
	
	public void init(ServletConfig config) throws ServletException
	{	//ServletConfig�� ServletContext�� web.xml ������ �����Ͽ� ������ ������ �����ϱ� ���� �뵵�� ���Ǵ� �������̽�. 
		
		services = new HashMap<>();
		Properties prop = new Properties();
		String path = config.getServletContext().getRealPath("/controller.ini");
		String cp = config.getServletContext().getContextPath();
		if (!cp.endsWith("/")) cp = cp + "/";
		
		try
		{
			prop.load(new FileReader(path));
			
			Enumeration<Object> keys = prop.keys();
			
			while(keys.hasMoreElements())
			{
				String key = (String)keys.nextElement();
				String value = prop.getProperty(key);
				try
				{
					services.put(cp + key, (BackController)Class.forName(value).newInstance());
				}
				catch(ClassNotFoundException e)
				{
//					e.printStackTrace();
				}
				catch(IllegalAccessException e)
				{
//					e.printStackTrace();
				}
				catch(InstantiationException e)
				{
//					e.printStackTrace();
				}
			}
		}
		catch(IOException e)
		{
//			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String uri = request.getRequestURI();
		services.get(uri).execute(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
}