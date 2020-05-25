package kr.co.foreignlove.vo;

import java.util.HashMap;

public class LikeyVO
{
	private MemberVO m_id;
	private ReplyVO r_id;
	private BoardVO b_id;

	public MemberVO getM_id()
	{
		return m_id;
	}

	public void setM_id(MemberVO m_id)
	{
		this.m_id = m_id;
	}

	public ReplyVO getR_id()
	{
		return r_id;
	}

	public void setR_id(ReplyVO r_id)
	{
		this.r_id = r_id;
	}

	public BoardVO getB_id()
	{
		return b_id;
	}

	public void setB_id(BoardVO b_id)
	{
		this.b_id = b_id;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((b_id == null) ? 0 : b_id.hashCode());
		result = prime * result + ((m_id == null) ? 0 : m_id.hashCode());
		result = prime * result + ((r_id == null) ? 0 : r_id.hashCode());
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
		LikeyVO other = (LikeyVO) obj;
		if (b_id == null)
		{
			if (other.b_id != null)
				return false;
		} else if (!b_id.equals(other.b_id))
			return false;
		if (m_id == null)
		{
			if (other.m_id != null)
				return false;
		} else if (!m_id.equals(other.m_id))
			return false;
		if (r_id == null)
		{
			if (other.r_id != null)
				return false;
		} else if (!r_id.equals(other.r_id))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "LikeyVO [m_id=" + m_id + ", r_id=" + r_id + ", b_id=" + b_id + "]";
	}

	public HashMap<String, Object> convertMap()
	{
		HashMap<String, Object> map = new HashMap<>();

		map.put("m_id", m_id.convertMap());
		map.put("r_id", r_id.convertMap());
		map.put("b_id", b_id.convertMap());

		return map;
	}
}
