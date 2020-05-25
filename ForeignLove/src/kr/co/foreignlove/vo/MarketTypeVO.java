package kr.co.foreignlove.vo;

import java.util.HashMap;

public class MarketTypeVO {

	private String mk_type;
	private String mk_typeName;
	
	public MarketTypeVO() {}

	public String getMk_type() {
		return mk_type;
	}

	public void setMk_type(String mk_type) {
		this.mk_type = mk_type;
	}

	public String getMk_typeName() {
		return mk_typeName;
	}

	public void setMk_typeName(String mk_typeName) {
		this.mk_typeName = mk_typeName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarketTypeVO other = (MarketTypeVO) obj;
		if (mk_type == null) {
			if (other.mk_type != null)
				return false;
		} else if (!mk_type.equals(other.mk_type))
			return false;
		if (mk_typeName == null) {
			if (other.mk_typeName != null)
				return false;
		} else if (!mk_typeName.equals(other.mk_typeName))
			return false;
		return true;
	}
	
	public HashMap<String,Object>convertMap(){
		HashMap<String,Object>map = new HashMap<>();
		map.put("mk_type",mk_type);
		map.put("mk_typeName",mk_typeName);
		
		return map;
	}
	
	
}
