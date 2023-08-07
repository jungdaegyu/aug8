package com.gyu.board;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyu.page.PageDTO;
import com.gyu.util.Util;

@Service("boardService") //괄호안에 boardService라고 적은건 이름을 지정하기 위해.. //BoardController에서 씀
public class BoardService {

	
	@Inject // 다 오토와이어드 해도 상관 없음
	@Named("boardDAO") //보드 디에이오랑 연결한거야
	private BoardDAO boardDAO;
		
	@Autowired
	private Util util; //util.java랑 연결해줬어영 오후..
	
	//보드 리스트 불러오는 메소드
	public List<BoardDTO> boardList(PageDTO page){ //boardList를 부르면 DTO 나옴
		
		
		return boardDAO.boardList(page);
	}

	
	public BoardDTO detail(BoardDTO dto2) {
		//좋아요 수 +1하기 기능을 넣어주겠습니다.
		boardDAO.likeUp(dto2); //안에 bno값이 들어있음
		
		
		
		BoardDTO dto = boardDAO.detail(dto2);
		System.out.println(dto);
		// System.out.println(dto.getBno());
		// System.out.println(dto.getBip());
		
		if (dto != null) { //내 글이 아닐때는 null 들어옵니다. 즉, null이 아닐때라고 검사해주세요.
			if (dto.getBip() != null && dto.getBip().indexOf(".") != -1) {		
				String ip = dto.getBip(); //ip 뽑아서 string ip에 넣어주기		
				String[] ipA = ip.split("\\."); // .을 기준으로 배열을 나누기
				ipA[1] = "♥"; // 배열1을 하트로 바꾸기
				dto.setBip(String.join(".", ipA)); //배열을 다시 스트링 타입으로 바꿔주기

		}
		
		}
		return dto;
	}

	//글쓰기입니다.
	public void write(BoardDTO dto) { //글 쓰면 2번째로 바로 여기옴
		//btitle을 꺼내줍니다.
		// dto.getBtitle();
		String btitle = dto.getBtitle();
		//값을 변경하겠습니다. replace() <-> &lt; <-> &gt;
		
		
		
		//btitle = btitle.replaceAll("<", "&lt;"); 
		//btitle = btitle.replaceAll(">", "&gt;");
	    //글을 썼을때 창이 깨지거나 오류나는것을 막기 위해 "<" 이걸 다른 문자열로 바꿔줌 //그리고 다시 Btitle을 세팅해줌
		
		// a.replaceAll("<", "&lt"); 
		// a.replaceAll(">", "&gt");
		// dto.setBtitle(a); 
			
		btitle = util.exchange(btitle); //본문 내용을 오류 안나게 설정해준거야 util.java 메소드를 활용해서
		
		dto.setBtitle(btitle); //다시 저장
		
		dto.setBip(util.getIp()); //얻어온 ip도 저장해서 데이터베이스로 보낼거야 ..(7월19일) // 값이 타이틀 본문 내용 이렇다가 여기서 값이 4개가 되는거일듯

		boardDAO.write(dto); //실행만 시키고 결과를 결과를 받지 않습니다.
		//select를 제외한 나머지는 영향받은 행의 수 (int)를 받아오기도 합니다.
	}


	public void delete(BoardDTO dto) {
		boardDAO.delete(dto);
		
	}


	public void edit(BoardDTO dto) {
		boardDAO.edit(dto);
		
	}


	public int totalCount() { //7.26
		return boardDAO.totalCount();
	}


	public List<Map<String, Object>> commentsList(int bno) {
		
		return boardDAO.commentsList(bno);
	}


	public int cdel(Map<String, Object> map) {
	
		return boardDAO.cdel(map);
	}
		
}
