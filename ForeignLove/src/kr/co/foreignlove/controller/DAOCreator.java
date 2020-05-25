package kr.co.foreignlove.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DAOCreator implements ServletContextListener
{
	public void contextInitialized(ServletContextEvent sce)
	{	
		Properties prop = new Properties();
		String path = sce.getServletContext().getRealPath("/dao.ini");
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
					@SuppressWarnings("rawtypes")
					Class cls = Class.forName(value);
					DAOManager.addDAO(key, cls.newInstance());
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
			e.printStackTrace();
		}
	}
	
	public void contextDestroyed(ServletContextEvent sce)
	{
		
	}
}