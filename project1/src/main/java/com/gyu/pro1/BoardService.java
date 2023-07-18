package com.gyu.pro1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Service;

@Service("boardService") //괄호안에 boardService라고 적은건 이름을 지정하기 위해.. //BoardController에서 씀
public class BoardService {

	
	@Inject // 다 오토와이어드 해도 상관 없음
	@Named("boardDAO") //보드 디에이오랑 연결한거야
	private BoardDAO boardDAO;
	
	
	//보드 리스트 불러오는 메소드
	public List<Map<String, Object>> boardList(){ //boardList를 부르면 map이 나옴
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		return boardDAO.boardList();
	}


	public BoardDTO detail(String bno) {
		
		
		return boardDAO.detail(bno);
	}

	//글쓰기입니다.
	public void write(BoardDTO dto) {
		boardDAO.write(dto);
		//select를 제외한 나머지는 영향받은 행의 수 (int)를 받아오기도 합니다.
	}
	
	
}
