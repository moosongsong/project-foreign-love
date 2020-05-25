package kr.co.foreignlove.vo;

import java.util.HashMap;

public class BoardTypeVO {
	private String bt_type;
	private String bt_typeName;
	
	public BoardTypeVO() {}

	public BoardTypeVO(String bt_type, String bt_typeName)
	{
		this.bt_type = bt_type;
		this.bt_typeName = bt_typeName;
	}
	
	public String getBt_type() {
		return bt_type;
	}

	public void setBt_type(String bt_type) {
		this.bt_type = bt_type;
	}

	public String getBt_typeName() {
		return bt_typeName;
	}

	public void setBt_typeName(String bt_typeName) {
		this.bt_typeName = bt_typeName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bt_type == null) ? 0 : bt_type.hashCode());
		result = prime * result + ((bt_typeName == null) ? 0 : bt_typeName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardTypeVO other = (BoardTypeVO) obj;
		if (bt_type == null) {
			if (other.bt_type != null)
				return false;
		} else if (!bt_type.equals(other.bt_type))
			return false;
		if (bt_typeName == null) {
			if (other.bt_typeName != null)
				return false;
		} else if (!bt_typeName.equals(other.bt_typeName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BoardTypeVO [bt_type=" + bt_type + ", bt_typeName=" + bt_typeName + "]";
	}
	
	public HashMap<String, Object> convertMap()
	{
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("bt_type", bt_type);
		map.put("bt_typeName", bt_typeName);
		return map;
	}
}
