package kr.co.foreignlove.vo;

import java.util.HashMap;

public class ReplyVO
{
	private int r_id;
	private String r_post;
	private String r_delete;
	private String r_content;
	private ReplyVO r_highId;
	private MemberVO m_id;
	private BoardVO b_id;
	
	public int getR_id()
	{
		return r_id;
	}
	public void setR_id(int r_id)
	{
		this.r_id = r_id;
	}
	public String getR_post()
	{
		return r_post;
	}
	public void setR_post(String r_post)
	{
		this.r_post = r_post;
	}
	public String getR_delete()
	{
		return r_delete;
	}
	public void setR_delete(String r_delete)
	{
		this.r_delete = r_delete;
	}
	public String getR_content()
	{
		return r_content;
	}
	public void setR_content(String r_content)
	{
		this.r_content = r_content;
	}
	public ReplyVO getR_highId()
	{
		return r_highId;
	}
	public void setR_highId(ReplyVO r_highId)
	{
		this.r_highId = r_highId;
	}
	public MemberVO getM_id()
	{
		return m_id;
	}
	public void setM_id(MemberVO m_id)
	{
		this.m_id = m_id;
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
		result = prime * result + ((r_content == null) ? 0 : r_content.hashCode());
		result = prime * result + ((r_delete == null) ? 0 : r_delete.hashCode());
		result = prime * result + ((r_highId == null) ? 0 : r_highId.hashCode());
		result = prime * result + r_id;
		result = prime * result + ((r_post == null) ? 0 : r_post.hashCode());
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
		ReplyVO other = (ReplyVO) obj;
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
		if (r_content == null)
		{
			if (other.r_content != null)
				return false;
		} else if (!r_content.equals(other.r_content))
			return false;
		if (r_delete == null)
		{
			if (other.r_delete != null)
				return false;
		} else if (!r_delete.equals(other.r_delete))
			return false;
		if (r_highId == null)
		{
			if (other.r_highId != null)
				return false;
		} else if (!r_highId.equals(other.r_highId))
			return false;
		if (r_id != other.r_id)
			return false;
		if (r_post == null)
		{
			if (other.r_post != null)
				return false;
		} else if (!r_post.equals(other.r_post))
			return false;
		return true;
	}
	
	@Override
	public String toString()
	{
		return "ReplyVO [r_id=" + r_id + ", r_post=" + r_post + ", r_delete=" + r_delete + ", r_content=" + r_content
				+ ", r_highId=" + r_highId + ", m_id=" + m_id + ", b_id=" + b_id + "]";
	}
	
	public HashMap<String, Object> convertMap()
	{
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("r_id", r_id);
		map.put("r_post", r_post);
		map.put("r_delete", r_delete);
		map.put("r_content", r_content);
		if(r_highId != null)
		{
			map.put("r_highId", r_highId.convertMap());	
		}
		// null일 때도 null을 넣어야 하는지
		
		
		if(m_id != null)
		{
			map.put("m_id", m_id.convertMap());			
		}
		
		if(b_id != null)
		{
			map.put("b_id", b_id.convertMap());			
		}
		
		return map;
	}
}
