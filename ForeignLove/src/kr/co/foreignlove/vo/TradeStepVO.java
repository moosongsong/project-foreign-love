package kr.co.foreignlove.vo;

import java.util.HashMap;

public class TradeStepVO {
	
	private String ts_type;
	private String ts_typeName;
	
	public TradeStepVO() {}

	public String getTs_type() {
		return ts_type;
	}

	public void setTs_type(String ts_type) {
		this.ts_type = ts_type;
	}

	public String getTs_typeName() {
		return ts_typeName;
	}

	public void setTs_typeName(String ts_typeName) {
		this.ts_typeName = ts_typeName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TradeStepVO other = (TradeStepVO) obj;
		if (ts_type == null) {
			if (other.ts_type != null)
				return false;
		} else if (!ts_type.equals(other.ts_type))
			return false;
		if (ts_typeName == null) {
			if (other.ts_typeName != null)
				return false;
		} else if (!ts_typeName.equals(other.ts_typeName))
			return false;
		return true;
	}
	
	public HashMap<String,Object>convertMap(){
		HashMap<String,Object>map = new HashMap<>();
		map.put("ts_type",ts_type);
		map.put("ts_typeName",ts_typeName);
		
		return map;
	}
	

}
