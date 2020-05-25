package kr.co.foreignlove.vo;

import java.util.HashMap;

public class PromotionTypeVO
{
	private String p_type;
	private String p_name;
	
	public PromotionTypeVO() {}
	
	public PromotionTypeVO(String p_type, String p_name)
	{
		this.p_type = p_type;
		this.p_name = p_name;
	}
	
	public String getP_type()
	{
		return p_type;
	}
	
	public String getP_name()
	{
		return p_name;
	}
	
	public void setP_type(String p_type)
	{
		this.p_type = p_type;
	}
	
	public void setP_name(String p_name)
	{
		this.p_name = p_name;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p_name == null) ? 0 : p_name.hashCode());
		result = prime * result + ((p_type == null) ? 0 : p_type.hashCode());
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
		PromotionTypeVO other = (PromotionTypeVO) obj;
		if (p_name == null)
		{
			if (other.p_name != null)
				return false;
		} else if (!p_name.equals(other.p_name))
			return false;
		if (p_type == null)
		{
			if (other.p_type != null)
				return false;
		} else if (!p_type.equals(other.p_type))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "PromotionTypeVO [p_type=" + p_type + ", p_name=" + p_name + "]";
	}
	
	public HashMap<String, Object> convertMap()
	{
		HashMap<String, Object> map = new HashMap<>();
		map.put("p_type", p_type);
		map.put("p_name", p_name);
		return map;
	}
}