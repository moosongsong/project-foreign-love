package kr.co.foreignlove.vo;

import java.util.HashMap;

public class SchoolVO
{
	private String s_id;
	private String s_nation;
	private String s_state;
	private String s_name;
	
	public SchoolVO() {}
	
	public SchoolVO(String s_id, String s_nation, String s_state, String s_name) {
		super();
		this.s_id = s_id;
		this.s_nation = s_nation;
		this.s_state = s_state;
		this.s_name = s_name;
	}
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getS_nation() {
		return s_nation;
	}
	public void setS_nation(String s_nation) {
		this.s_nation = s_nation;
	}
	public String getS_state() {
		return s_state;
	}
	public void setS_state(String s_state) {
		this.s_state = s_state;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((s_id == null) ? 0 : s_id.hashCode());
		result = prime * result + ((s_name == null) ? 0 : s_name.hashCode());
		result = prime * result + ((s_nation == null) ? 0 : s_nation.hashCode());
		result = prime * result + ((s_state == null) ? 0 : s_state.hashCode());
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
		SchoolVO other = (SchoolVO) obj;
		if (s_id == null) {
			if (other.s_id != null)
				return false;
		} else if (!s_id.equals(other.s_id))
			return false;
		if (s_name == null) {
			if (other.s_name != null)
				return false;
		} else if (!s_name.equals(other.s_name))
			return false;
		if (s_nation == null) {
			if (other.s_nation != null)
				return false;
		} else if (!s_nation.equals(other.s_nation))
			return false;
		if (s_state == null) {
			if (other.s_state != null)
				return false;
		} else if (!s_state.equals(other.s_state))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("s_id : '"+s_id+"', ");
		sb.append("s_nation : '"+s_nation+"', ");
		sb.append("s_state : '"+s_state+"', ");
		sb.append("s_name : '"+ s_name+"', ");
		sb.append("}");
		return sb.toString();
	}	
	
	public HashMap<String, Object> convertMap()
	{
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("s_id", s_id);
		map.put("s_nation", s_nation);
		map.put("s_state", s_state);
		map.put("s_name", s_name);
		
		return map;
	}
}
