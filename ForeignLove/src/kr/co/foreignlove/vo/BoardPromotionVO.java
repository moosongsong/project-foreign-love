package kr.co.foreignlove.vo;

import java.util.HashMap;

public class BoardPromotionVO extends BoardVO
{
	private String b_startDate;
	private String b_endDate;
	private PromotionTypeVO p_type;
	
	public String getB_startDate()
	{
		return b_startDate;
	}

	public void setB_startDate(String b_startDate)
	{
		this.b_startDate = b_startDate;
	}

	public String getB_endDate()
	{
		return b_endDate;
	}

	public void setB_endDate(String b_endDate)
	{
		this.b_endDate = b_endDate;
	}

	public PromotionTypeVO getP_type()
	{
		return p_type;
	}

	public void setP_type(PromotionTypeVO p_type)
	{
		this.p_type = p_type;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((b_endDate == null) ? 0 : b_endDate.hashCode());
		result = prime * result + ((b_startDate == null) ? 0 : b_startDate.hashCode());
		result = prime * result + ((p_type == null) ? 0 : p_type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardPromotionVO other = (BoardPromotionVO) obj;
		if (b_endDate == null)
		{
			if (other.b_endDate != null)
				return false;
		} else if (!b_endDate.equals(other.b_endDate))
			return false;
		if (b_startDate == null)
		{
			if (other.b_startDate != null)
				return false;
		} else if (!b_startDate.equals(other.b_startDate))
			return false;
		if (p_type == null)
		{
			if (other.p_type != null)
				return false;
		} else if (!p_type.equals(other.p_type))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append("{")
		.append("b_id: ").append(b_id).append(", ")
		.append("b_title: ").append(b_title).append(", ")
		.append("b_content: ").append(b_content).append(", ")
		.append("b_post: ").append(b_post).append(", ")
		.append("b_delete: ").append(b_delete).append(", ")
		.append("b_count: ").append(b_count).append(", ")
		.append("b_startDate: ").append(b_startDate).append(", ")
		.append("b_endDate: ").append(b_endDate).append(", ")
		.append("boardType: ").append(boardType).append(", ")
		.append("p_type: ").append(p_type).append(", ")
		.append("member: ").append(member).append("")
		.append("}");
		
		return sb.toString();
	}
	
	public HashMap<String, Object> convertMap()
	{
		HashMap<String, Object> map = new HashMap<>();
		map.put("b_id", b_id);
		map.put("b_title", b_title);
		map.put("b_content", b_content);
		map.put("b_post", b_post);
		map.put("b_delete", b_delete);
		map.put("b_count", b_count);
		map.put("b_startDate", b_startDate);
		map.put("b_endDate", b_endDate);
		map.put("boardType", boardType.convertMap());
		map.put("p_type", p_type.convertMap());
		map.put("member", member.convertMap());

		return map;
	}
}
