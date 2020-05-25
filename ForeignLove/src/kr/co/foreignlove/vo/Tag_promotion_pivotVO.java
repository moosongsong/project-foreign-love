package kr.co.foreignlove.vo;


public class Tag_promotion_pivotVO
{
	private TagVO t_id;
	private BoardPromotionVO p_id;
	
	public TagVO getT_id()
	{
		return t_id;
	}
	public void setT_id(TagVO t_id)
	{
		this.t_id = t_id;
	}
	public BoardPromotionVO getP_id()
	{
		return p_id;
	}
	public void setP_id(BoardPromotionVO p_id)
	{
		this.p_id = p_id;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p_id == null) ? 0 : p_id.hashCode());
		result = prime * result + ((t_id == null) ? 0 : t_id.hashCode());
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
		Tag_promotion_pivotVO other = (Tag_promotion_pivotVO) obj;
		if (p_id == null)
		{
			if (other.p_id != null)
				return false;
		} else if (!p_id.equals(other.p_id))
			return false;
		if (t_id == null)
		{
			if (other.t_id != null)
				return false;
		} else if (!t_id.equals(other.t_id))
			return false;
		return true;
	}
	
	@Override
	public String toString()
	{
		return "Tag_promotion_pivotVO [t_id=" + t_id + ", p_id=" + p_id + "]";
	}
}
