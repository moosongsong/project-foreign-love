package kr.co.foreignlove.controller;

import java.util.HashMap;

public class DAOManager
{
	public static HashMap<String, Object> daos;
	
	static
	{
		daos = new HashMap<>();
	}
	
	private DAOManager() {}
	
	public static void addDAO(String key, Object o)
	{
		daos.put(key, o);
	}
	
	public static Object getDAO(String key)
	{
		return daos.get(key);
	}
}
