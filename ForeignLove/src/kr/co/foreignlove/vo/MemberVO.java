package kr.co.foreignlove.vo;

import java.util.HashMap;

public class MemberVO {
	private int m_id;
	private String m_email;
	private String m_name;
	private String m_pass;
	private String m_phone;
	private String m_birth;
	private String m_photo;
	private String m_nick;
	private String m_regDate;
	private String m_remDate;
	private String m_sex;
	private String m_startDate;
	private String m_addr;
	private SchoolVO s_id;
	private MemberTypeVO m_type;
	
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}
	
	public MemberVO(int m_id, String m_email, String m_name, String m_pass, String m_phone, String m_birth,
			String m_photo, String m_nick, String m_regDate, String m_remDate, String m_sex, String m_startDate,
			String m_addr, SchoolVO s_id, MemberTypeVO m_type) {
		super();
		this.m_id = m_id;
		this.m_email = m_email;
		this.m_name = m_name;
		this.m_pass = m_pass;
		this.m_phone = m_phone;
		this.m_birth = m_birth;
		this.m_photo = m_photo;
		this.m_nick = m_nick;
		this.m_regDate = m_regDate;
		this.m_remDate = m_remDate;
		this.m_sex = m_sex;
		this.m_startDate = m_startDate;
		this.m_addr = m_addr;
		this.s_id = s_id;
		this.m_type = m_type;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getM_pass() {
		return m_pass;
	}
	public void setM_pass(String m_pass) {
		this.m_pass = m_pass;
	}
	public String getM_phone() {
		return m_phone;
	}
	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}
	public String getM_birth() {
		return m_birth;
	}
	public void setM_birth(String m_birth) {
		this.m_birth = m_birth;
	}
	public String getM_photo() {
		return m_photo;
	}
	public void setM_photo(String m_photo) {
		this.m_photo = m_photo;
	}
	public String getM_nick() {
		return m_nick;
	}
	public void setM_nick(String m_nick) {
		this.m_nick = m_nick;
	}
	public String getM_regDate() {
		return m_regDate;
	}
	public void setM_regDate(String m_regDate) {
		this.m_regDate = m_regDate;
	}
	public String getM_remDate() {
		return m_remDate;
	}
	public void setM_remDate(String m_remDate) {
		this.m_remDate = m_remDate;
	}
	public String getM_sex() {
		return m_sex;
	}
	public void setM_sex(String m_sex) {
		this.m_sex = m_sex;
	}
	public String getM_startDate() {
		return m_startDate;
	}
	public void setM_startDate(String startDate) {
		this.m_startDate = startDate;
	}
	public String getM_addr() {
		return m_addr;
	}
	public void setM_addr(String m_addr) {
		this.m_addr = m_addr;
	}
	public SchoolVO getS_id() {
		return s_id;
	}
	public void setS_id(SchoolVO s_id) {
		this.s_id = s_id;
	}
	public MemberTypeVO getM_type() {
		return m_type;
	}
	public void setM_type(MemberTypeVO m_type) {
		this.m_type = m_type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m_addr == null) ? 0 : m_addr.hashCode());
		result = prime * result + ((m_birth == null) ? 0 : m_birth.hashCode());
		result = prime * result + ((m_email == null) ? 0 : m_email.hashCode());
		result = prime * result + m_id;
		result = prime * result + ((m_name == null) ? 0 : m_name.hashCode());
		result = prime * result + ((m_nick == null) ? 0 : m_nick.hashCode());
		result = prime * result + ((m_pass == null) ? 0 : m_pass.hashCode());
		result = prime * result + ((m_phone == null) ? 0 : m_phone.hashCode());
		result = prime * result + ((m_photo == null) ? 0 : m_photo.hashCode());
		result = prime * result + ((m_regDate == null) ? 0 : m_regDate.hashCode());
		result = prime * result + ((m_remDate == null) ? 0 : m_remDate.hashCode());
		result = prime * result + ((m_sex == null) ? 0 : m_sex.hashCode());
		result = prime * result + ((m_type == null) ? 0 : m_type.hashCode());
		result = prime * result + ((s_id == null) ? 0 : s_id.hashCode());
		result = prime * result + ((m_startDate == null) ? 0 : m_startDate.hashCode());
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
		MemberVO other = (MemberVO) obj;
		if (m_addr == null) {
			if (other.m_addr != null)
				return false;
		} else if (!m_addr.equals(other.m_addr))
			return false;
		if (m_birth == null) {
			if (other.m_birth != null)
				return false;
		} else if (!m_birth.equals(other.m_birth))
			return false;
		if (m_email == null) {
			if (other.m_email != null)
				return false;
		} else if (!m_email.equals(other.m_email))
			return false;
		if (m_id != other.m_id)
			return false;
		if (m_name == null) {
			if (other.m_name != null)
				return false;
		} else if (!m_name.equals(other.m_name))
			return false;
		if (m_nick == null) {
			if (other.m_nick != null)
				return false;
		} else if (!m_nick.equals(other.m_nick))
			return false;
		if (m_pass == null) {
			if (other.m_pass != null)
				return false;
		} else if (!m_pass.equals(other.m_pass))
			return false;
		if (m_phone == null) {
			if (other.m_phone != null)
				return false;
		} else if (!m_phone.equals(other.m_phone))
			return false;
		if (m_photo == null) {
			if (other.m_photo != null)
				return false;
		} else if (!m_photo.equals(other.m_photo))
			return false;
		if (m_regDate == null) {
			if (other.m_regDate != null)
				return false;
		} else if (!m_regDate.equals(other.m_regDate))
			return false;
		if (m_remDate == null) {
			if (other.m_remDate != null)
				return false;
		} else if (!m_remDate.equals(other.m_remDate))
			return false;
		if (m_sex == null) {
			if (other.m_sex != null)
				return false;
		} else if (!m_sex.equals(other.m_sex))
			return false;
		if (m_type == null) {
			if (other.m_type != null)
				return false;
		} else if (!m_type.equals(other.m_type))
			return false;
		if (s_id == null) {
			if (other.s_id != null)
				return false;
		} else if (!s_id.equals(other.s_id))
			return false;
		if (m_startDate == null) {
			if (other.m_startDate != null)
				return false;
		} else if (!m_startDate.equals(other.m_startDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("m_id : '"+m_id+"', ");
		sb.append("m_email : '"+m_email+"', ");
		sb.append("m_name : '"+m_name+"', ");
		sb.append("m_pass : '"+ m_pass+"', ");
		sb.append("m_phone : '"+m_phone+"', ");
		sb.append("m_birth : '"+m_birth+"', ");	
		sb.append("m_photo : '"+m_photo+"', ");
		sb.append("m_nick : '"+m_nick+"', ");
		sb.append("m_regDate : '"+m_regDate+"', ");
		sb.append("m_remDate : '"+m_remDate+"', ");
		sb.append("m_sex : '"+m_sex+"', ");
		sb.append("m_startDate : '"+m_startDate+"', ");
		sb.append("m_addr : '"+m_addr+"', ");
		sb.append("s_id : '"+s_id+"', ");
		sb.append("m_type : '"+m_type+"'");
		sb.append("}");
		return sb.toString();
	}
	
	public HashMap<String, Object> convertMap()
	{
		HashMap<String, Object> map = new HashMap<>();

		map.put("m_id", m_id);
		map.put("m_email", m_email);
		map.put("m_name", m_name);
		map.put("m_pass", m_pass);
		map.put("m_phone", m_phone);
		map.put("m_birth", m_birth);
		map.put("m_photo", m_photo);
		map.put("m_nick", m_nick);
		map.put("m_regDate", m_regDate);
		map.put("m_remDate", m_remDate);
		map.put("m_sex", m_sex);
		map.put("m_startDate", m_startDate);
		map.put("m_addr", m_addr);
		map.put("s_id", s_id.getS_name());
		map.put("m_type", m_type.getM_typeName());
		
		return map;
	}
}
