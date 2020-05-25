package kr.co.foreignlove.vo;

import java.util.HashMap;

public class BoardFreeVO extends BoardVO{
	
	public BoardFreeVO() {}

	public HashMap<String , Object> convertMap(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("b_id", b_id);
		map.put("b_title", b_title);
		map.put("b_content", b_content);
		map.put("b_post", b_post);
		map.put("b_delete", b_delete);
		map.put("b_count", b_count);
		map.put("boardType", boardType.convertMap());
		map.put("member", member.convertMap());

		map.put("attachedList", attachedList);
		return map;
	}
	
	
}
