package kr.co.foreignlove.vo;

import java.util.HashMap;

public class LowTypeVO {
	
	private String l_type;
	private String l_typeName;
	
	public LowTypeVO() {}
	
	public String getL_type() {
		return l_type;
	}
	public void setL_type(String l_type) {
		this.l_type = l_type;
	}
	public String getL_typeName() {
		return l_typeName;
	}
	public void setL_typeName(String l_typeName) {
		this.l_typeName = l_typeName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LowTypeVO other = (LowTypeVO) obj;
		if (l_type == null) {
			if (other.l_type != null)
				return false;
		} else if (!l_type.equals(other.l_type))
			return false;
		if (l_typeName == null) {
			if (other.l_typeName != null)
				return false;
		} else if (!l_typeName.equals(other.l_typeName))
			return false;
		return true;
	}
	
	
	public HashMap<String,Object>convertMap(){
		HashMap<String,Object>map = new HashMap<>();
		map.put("l_type",l_type);
		map.put("l_typeName",l_typeName);
		
		return map;
	}
	
	

}
