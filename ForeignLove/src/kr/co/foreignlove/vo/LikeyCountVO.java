package kr.co.foreignlove.vo;

import java.util.HashMap;

public class LikeyCountVO
{
	private int count;
	private int id;
	
	public int getCount()
	{
		return count;
	}
	
	public void setCount(int count)
	{
		this.count = count;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + id;
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
		LikeyCountVO other = (LikeyCountVO) obj;
		if (count != other.count)
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "LikeyCountVO [count=" + count + ", id=" + id + "]";
	}
	
	public HashMap<String, Object> convertMap()
	{
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("count", count);
		map.put("id", id);
		
		return map;
	}
}
