package kr.co.foreignlove.vo;

import java.util.HashMap;

public class BoardMarketVO extends BoardVO{

	public static final String UPFOLDER = "../marketuploads/";
	
	private String mk_price;
	private String mk_area;
	private LowTypeVO lowType;
	private MarketTypeVO marketType;
	private TradeWayVO tradeWay;
	
	public BoardMarketVO() {}

	public String getMk_price() {
		return mk_price;
	}

	public void setMk_price(String mk_price) {
		this.mk_price = mk_price;
	}

	public String getMk_area() {
		return mk_area;
	}

	public void setMk_area(String mk_area) {
		this.mk_area = mk_area;
	}

	public LowTypeVO getLowType() {
		return lowType;
	}

	public void setLowType(LowTypeVO lowType) {
		this.lowType = lowType;
	}

	public MarketTypeVO getMarketType() {
		return marketType;
	}

	public void setMarketType(MarketTypeVO marketType) {
		this.marketType = marketType;
	}

	public TradeWayVO getTradeWay() {
		return tradeWay;
	}

	public void setTradeWay(TradeWayVO tradeWay) {
		this.tradeWay = tradeWay;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{")
		  .append("b_id : ").append(b_id).append(",")
		  .append("b_title :").append(b_title).append(",")
		  .append("b_content :").append(b_content).append(",")
		  .append("b_post : ").append(b_post).append(",")
		  .append("b_delete :").append(b_delete).append(",")
		  .append("b_count :").append(b_count).append(",")
		  .append("mk_price :").append(mk_price).append(",")
		  .append("mk_area :").append(mk_area).append(",")
		  .append("bt_type : ").append(boardType).append(",")
		  .append("l_type :").append(lowType).append(",")
		  .append("mk_type :").append(marketType).append(",")
		  .append("tw_type :").append(tradeWay).append(",")
		  .append("member : ").append(member).append("")
		  .append("}");
		
		
		return sb.toString();
	}
	
	public HashMap<String, Object>convertMap(){
		HashMap<String, Object>map = new HashMap<>();
		map.put("b_id",b_id);
		map.put("b_title",b_title);
		map.put("b_content",b_content);
		map.put("b_post",b_post);
		map.put("b_delete", b_delete);
		map.put("b_count",b_count);
		map.put("mk_price",mk_price);
		map.put("mk_area",mk_area);
		map.put("bt_type",boardType.convertMap());
		map.put("l_type",lowType.convertMap());
		map.put("mk_type",marketType.convertMap());
		map.put("tw_type",tradeWay.convertMap());
		map.put("member",member.convertMap());
		return map;
	}
	
}