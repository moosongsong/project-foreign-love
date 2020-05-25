package kr.co.foreignlove.vo;

import java.util.HashMap;

public class AttachedVO {
	private int a_id;
	private String a_name;
	private BoardTypeVO boardType;
	private int b_id;
	
	
	

	
	public int getA_id() {
		return a_id;
	}

	public void setA_id(int a_id) {
		this.a_id = a_id;
	}

	public String getA_name() {
		return a_name;
	}

	public void setA_name(String a_name) {
		this.a_name = a_name;
	}

	public BoardTypeVO getBoardType() {
		return boardType;
	}

	public void setBoardType(BoardTypeVO boardType) {
		this.boardType = boardType;
	}

	public int getB_id() {
		return b_id;
	}

	public void setB_id(int b_id) {
		this.b_id = b_id;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a_id;
		result = prime * result + ((a_name == null) ? 0 : a_name.hashCode());
		result = prime * result + b_id;
		result = prime * result + ((boardType == null) ? 0 : boardType.hashCode());
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
		AttachedVO other = (AttachedVO) obj;
		if (a_id != other.a_id)
			return false;
		if (a_name == null) {
			if (other.a_name != null)
				return false;
		} else if (!a_name.equals(other.a_name))
			return false;
		if (b_id != other.b_id)
			return false;
		if (boardType == null) {
			if (other.boardType != null)
				return false;
		} else if (!boardType.equals(other.boardType))
			return false;
		return true;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{")
		.append("a_id: ").append(a_id).append(", ")
		.append("a_name: ").append(a_name).append(", ")
		.append("boardType: ").append(boardType).append(", ")
		.append("b_id: ").append(b_id).append("")
		.append("}");
		return sb.toString();
	}	
	
	public HashMap<String, Object> convertMap(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("a_id", a_id);
		map.put("a_name", a_name);
		map.put("boardType", boardType.convertMap());
		map.put("b_id", b_id);
		return map;
	}
	
}
