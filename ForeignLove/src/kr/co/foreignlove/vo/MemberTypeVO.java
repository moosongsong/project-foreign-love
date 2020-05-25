package kr.co.foreignlove.vo;

import java.util.HashMap;

public class MemberTypeVO {
	private String m_type;
	private String m_typeName;
	public MemberTypeVO() {
		// TODO Auto-generated constructor stub
	}
	public MemberTypeVO(String m_type, String m_typeName) {
		super();
		this.m_type = m_type;
		this.m_typeName = m_typeName;
	}
	public String getM_type() {
		return m_type;
	}
	public void setM_type(String m_type) {
		this.m_type = m_type;
	}
	public String getM_typeName() {
		return m_typeName;
	}
	public void setM_typeName(String m_typeName) {
		this.m_typeName = m_typeName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m_type == null) ? 0 : m_type.hashCode());
		result = prime * result + ((m_typeName == null) ? 0 : m_typeName.hashCode());
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
		MemberTypeVO other = (MemberTypeVO) obj;
		if (m_type == null) {
			if (other.m_type != null)
				return false;
		} else if (!m_type.equals(other.m_type))
			return false;
		if (m_typeName == null) {
			if (other.m_typeName != null)
				return false;
		} else if (!m_typeName.equals(other.m_typeName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MemberTypeVO [m_type=" + m_type + ", m_typeName=" + m_typeName + "]";
	}
	
	public HashMap<String, Object> convertMap()
	{
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("m_type", m_type);
		map.put("m_typeName", m_typeName);
		return map;
	}
}
