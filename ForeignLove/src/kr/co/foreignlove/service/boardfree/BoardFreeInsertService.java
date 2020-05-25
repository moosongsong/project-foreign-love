package kr.co.foreignlove.service.boardfree;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.AttachedDAO;
import kr.co.foreignlove.dao.BoardDAO;
import kr.co.foreignlove.dao.BoardFreeDAO;
import kr.co.foreignlove.dao.BoardTypeDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.AttachedVO;
import kr.co.foreignlove.vo.BoardFreeVO;
import kr.co.foreignlove.vo.BoardTypeVO;
import kr.co.foreignlove.vo.MemberVO;

public class BoardFreeInsertService implements Service{
	BoardDAO dao;
	AttachedDAO adao;
	public BoardFreeInsertService() {
		dao =(BoardDAO)(DAOManager.getDAO(BoardFreeDAO.NAME));
		adao = (AttachedDAO)(DAOManager.getDAO(AttachedDAO.NAME));
	}
	public void doService(HttpServletRequest request, HttpServletResponse respobnse) throws Exception{
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String[] files =request.getParameterValues("files");
		
		String path = request.getServletContext().getRealPath("/uploadtemp");
		String pathOrig = request.getServletContext().getRealPath("/uploads");
		File uploadtemp = new File(path);
		File move_dir = new File(pathOrig);
		
		
		
		//여기에 파일배열을 Stirng[]로 받은 다음 (여기에 오는 이미지들은 실제가 아닌 이름만 가져오고 uploadtemp랑 비교후 
		//이 이름들과 일치하는 image파일들을 db에 넣어준다. 
		//File forder = new File(path); 을 해서 path는 uploads폴더의 경로를 넣는다.
		//그 후 File의 메소드를 자바에서 참고하고 list()로 String[]으로 뽑아와서 받은 배열과 일일히 확인후에 동일한것만 db로 보내면 어떨까.
		
		if(title == null || title.trim().equals("")) {
			throw new Exception ("제목을 입력하세요.");
		}
		if(content == null || content.trim().equals("")) {
			throw new Exception ("내용을 입력하세요.");
		}
		
		BoardTypeVO bt = new BoardTypeVO();		
		bt = ((BoardTypeDAO)(DAOManager.getDAO(BoardTypeDAO.NAME))).getBoardTypeVO("FR");
		BoardFreeVO board = new BoardFreeVO();
		board.setB_title(title);
		board.setB_content(content);
		
		board.setBoardType(bt);
		MemberVO member = new MemberVO();
		//session에 member를 넣어놨음 :
		HttpSession session = request.getSession();
		
		member = (MemberVO)session.getAttribute("loginUserInfo");
		
		board.setMember(member);
		dao.insert(board);
//		if(dao.insert(board)) {
//			request.setAttribute("board", board);
//		}
//		else {
//			throw new Exception("게시물등록에 실패하였습니다.");
//		}
		
		//attached 넣는곳
		
		AttachedVO attached = new AttachedVO();
		
		for(int i =1 ; i< files.length; i++) {
			
			for(int j=0; j< uploadtemp.list().length ; j++) {
				
				if(uploadtemp.list()[j].equals(files[i])) {
					System.out.println("---------------------");
					System.out.println(files[i]);
					System.out.println(uploadtemp.listFiles()[j]);
					System.out.println("성공");
					
					File moveFile = new File(move_dir, uploadtemp.listFiles()[j].getName());
					uploadtemp.listFiles()[j].renameTo(moveFile);
					
					attached.setA_name(files[i]);
					attached.setB_id(board.getB_id());
					attached.setBoardType(bt);
					
					boolean result = adao.insert(attached);
					System.out.println(result);
					
					board.setAttached(attached);
					System.out.println(board.getAttachedList().get(0));
					System.out.println("---------------------");
				}
			}
			
		}
		
		//request.setAttribute("board", board);
		//the reason why using session is that request cannot maintain information we have.
		
	}
}
