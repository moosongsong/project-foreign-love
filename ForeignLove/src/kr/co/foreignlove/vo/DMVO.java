package kr.co.foreignlove.vo;

import java.util.HashMap;

public class DMVO {
	private int dm_id;
	private String dm_content;
	private String dm_sendDate;
	private int dm_isChecked;
	private MemberVO receiver_id;
	private MemberVO sender_id;
	
	public DMVO() {
		// TODO Auto-generated constructor stub
	}

	public DMVO(int dm_id, String dm_content, String dm_sendDate, int dm_isChecked, MemberVO receiver_id,
			MemberVO sender_id) {
		super();
		this.dm_id = dm_id;
		this.dm_content = dm_content;
		this.dm_sendDate = dm_sendDate;
		this.dm_isChecked = dm_isChecked;
		this.receiver_id = receiver_id;
		this.sender_id = sender_id;
	}

	public int getDm_id() {
		return dm_id;
	}

	public void setDm_id(int dm_id) {
		this.dm_id = dm_id;
	}

	public String getDm_content() {
		return dm_content;
	}

	public void setDm_content(String dm_content) {
		this.dm_content = dm_content;
	}

	public String getDm_sendDate() {
		return dm_sendDate;
	}

	public void setDm_sendDate(String dm_sendDate) {
		this.dm_sendDate = dm_sendDate;
	}

	public int getDm_isChecked() {
		return dm_isChecked;
	}

	public void setDm_isChecked(int dm_isChecked) {
		this.dm_isChecked = dm_isChecked;
	}

	public MemberVO getReceiver_id() {
		return receiver_id;
	}

	public void setReceiver_id(MemberVO receiver_id) {
		this.receiver_id = receiver_id;
	}

	public MemberVO getSender_id() {
		return sender_id;
	}

	public void setSender_id(MemberVO sender_id) {
		this.sender_id = sender_id;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("dm_id : '"+dm_id+"', ");
		sb.append("dm_content : '"+dm_content+"', ");
		sb.append("dm_sendDate : '"+dm_sendDate+"', ");
		sb.append("dm_isChecked : '"+ dm_isChecked+"', ");
		sb.append("receiver_id : '"+receiver_id+"', ");
		sb.append("sender_id : '"+sender_id+"', ");	
		sb.append("}");
		return sb.toString();
	}
	
	public HashMap<String, Object> convertMap()
	{
		HashMap<String, Object> map = new HashMap<>();

		map.put("dm_id", dm_id);
		map.put("dm_content", dm_content);
		map.put("dm_sendDate", dm_sendDate);
		map.put("dm_isChecked", dm_isChecked);
		map.put("receiver_id", receiver_id.getM_nick());
		map.put("sender_id", sender_id.getM_nick());
		
		return map;
	}
}
