package kr.co.foreignlove.dao;

import kr.co.foreignlove.vo.BoardVO;

public interface BoardDAO {
	
	public boolean insert(BoardVO board);
	public boolean delete(int b_id);
	public boolean update(BoardVO board);
	public BoardVO getBoard(int b_id);
	public int getTotalRecord(String condition, String word);
}
