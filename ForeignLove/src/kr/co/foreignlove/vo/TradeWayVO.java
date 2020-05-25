package kr.co.foreignlove.vo;

import java.util.HashMap;

public class TradeWayVO {
	
	private String tw_type;
	private String tw_typeName;
	
	public TradeWayVO() {}

	public String getTw_type() {
		return tw_type;
	}

	public void setTw_type(String tw_type) {
		this.tw_type = tw_type;
	}

	public String getTw_typeName() {
		return tw_typeName;
	}

	public void setTw_typeName(String tw_typeName) {
		this.tw_typeName = tw_typeName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TradeWayVO other = (TradeWayVO) obj;
		if (tw_type == null) {
			if (other.tw_type != null)
				return false;
		} else if (!tw_type.equals(other.tw_type))
			return false;
		if (tw_typeName == null) {
			if (other.tw_typeName != null)
				return false;
		} else if (!tw_typeName.equals(other.tw_typeName))
			return false;
		return true;
	}
	
	public HashMap<String,Object>convertMap(){
		HashMap<String,Object>map = new HashMap<>();
		map.put("tw_type",tw_type);
		map.put("tw_typeName",tw_typeName);
		
		return map;
	}
	
	
	

}
