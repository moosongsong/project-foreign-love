package kr.co.foreignlove.vo;

import java.util.HashMap;

public class TagVO
{
	private int t_id;
	private String t_name;
	
	public int getT_id()
	{
		return t_id;
	}
	
	public String getT_name()
	{
		return t_name;
	}
	
	public void setT_id(int t_id)
	{
		this.t_id = t_id;
	}
	
	public void setT_name(String t_name)
	{
		this.t_name = t_name;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + t_id;
		result = prime * result + ((t_name == null) ? 0 : t_name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TagVO other = (TagVO) obj;
		if (t_id != other.t_id)
			return false;
		if (t_name == null)
		{
			if (other.t_name != null)
				return false;
		} else if (!t_name.equals(other.t_name))
			return false;
		return true;
	}

	public String toString()
	{
		return "TagVO [t_id=" + t_id + ", t_name=" + t_name + "]";
	}
	
	public HashMap<String, Object> convertMap()
	{
		HashMap<String, Object> map = new HashMap<>();
		map.put("t_id", t_id);
		map.put("t_name", t_name);
		return map;
	}
}
