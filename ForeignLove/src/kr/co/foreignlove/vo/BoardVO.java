package kr.co.foreignlove.vo;

import java.util.ArrayList;
import java.util.HashMap;

public class BoardVO {
	protected int b_id;
	protected String b_title;
	protected String b_content;
	protected String b_post;//작성일자
	protected String b_delete;//삭제일자
	protected int b_count;//조회수
	protected BoardTypeVO boardType;//게시판 종류
	protected MemberVO member;//작성자mNick
	protected ArrayList<AttachedVO> attachedList;
	
	public BoardVO() 
	{
		attachedList = new ArrayList<>();
	}

	
	public int getB_id() {
		return b_id;
	}


	public void setB_id(int b_id) {
		this.b_id = b_id;
	}


	public String getB_title() {
		return b_title;
	}


	public void setB_title(String b_title) {
		this.b_title = b_title;
	}


	public String getB_content() {
		return b_content;
	}


	public void setB_content(String b_content) {
		this.b_content = b_content;
	}


	public String getB_post() {
		return b_post;
	}


	public void setB_post(String b_post) {
		this.b_post = b_post;
	}


	public String getB_delete() {
		return b_delete;
	}


	public void setB_delete(String b_delete) {
		this.b_delete = b_delete;
	}


	public int getB_count() {
		return b_count;
	}


	public void setB_count(int b_count) {
		this.b_count = b_count;
	}


	public BoardTypeVO getBoardType() {
		return boardType;
	}


	public void setBoardType(BoardTypeVO boardType) {
		this.boardType = boardType;
	}


	public MemberVO getMember() {
		return member;
	}


	public void setMember(MemberVO member) {
		this.member = member;
	}
	public ArrayList<AttachedVO> getAttachedList() {
		return attachedList;
	}
	public void setAttachedList(ArrayList<AttachedVO> attachedList) {
		this.attachedList= attachedList;
	}
	public void setAttached(AttachedVO attached) {
		this.attachedList.add(attached);
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attachedList == null) ? 0 : attachedList.hashCode());
		result = prime * result + ((b_content == null) ? 0 : b_content.hashCode());
		result = prime * result + b_count;
		result = prime * result + ((b_delete == null) ? 0 : b_delete.hashCode());
		result = prime * result + b_id;
		result = prime * result + ((b_post == null) ? 0 : b_post.hashCode());
		result = prime * result + ((b_title == null) ? 0 : b_title.hashCode());
		result = prime * result + ((boardType == null) ? 0 : boardType.hashCode());
		result = prime * result + ((member == null) ? 0 : member.hashCode());
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
		BoardVO other = (BoardVO) obj;
		if (attachedList == null) {
			if (other.attachedList != null)
				return false;
		} else if (!attachedList.equals(other.attachedList))
			return false;
		if (b_content == null) {
			if (other.b_content != null)
				return false;
		} else if (!b_content.equals(other.b_content))
			return false;
		if (b_count != other.b_count)
			return false;
		if (b_delete == null) {
			if (other.b_delete != null)
				return false;
		} else if (!b_delete.equals(other.b_delete))
			return false;
		if (b_id != other.b_id)
			return false;
		if (b_post == null) {
			if (other.b_post != null)
				return false;
		} else if (!b_post.equals(other.b_post))
			return false;
		if (b_title == null) {
			if (other.b_title != null)
				return false;
		} else if (!b_title.equals(other.b_title))
			return false;
		if (boardType == null) {
			if (other.boardType != null)
				return false;
		} else if (!boardType.equals(other.boardType))
			return false;
		if (member == null) {
			if (other.member != null)
				return false;
		} else if (!member.equals(other.member))
			return false;
		return true;
	}


	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{")
		.append("b_id: ").append(b_id).append(", ")
		.append("b_title: ").append(b_title).append(", ")
		.append("b_content: ").append(b_content).append(", ")
		.append("b_post: ").append(b_post).append(", ")
		.append("b_delete: ").append(b_delete).append(", ")
		.append("b_count: ").append(b_count).append(", ")
		.append("boardType: ").append(boardType).append(", ")
		.append("member: ").append(member).append(", ")
		.append("attachedList: ").append(attachedList).append(" ")
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
		map.put("boardType", boardType.convertMap());
		map.put("member", member.convertMap());
		HashMap<Integer, Object> aMap = new HashMap<>();
		
		for(int i = 0; i<attachedList.size(); i++)
		{
			
			AttachedVO a = attachedList.get(i);
			aMap.put(a.getA_id(), a);
		}
		
		map.put("attachedList", aMap);
		
		return map;
	}
}
