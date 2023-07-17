package com.gyu.pro1;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	//user -> Controller -> Service -> DAO -> mybatis -> DB
	
	
	//Autowired(데이터타입이 맞으면 ㅇㅋ) 말고 Resource로 연결해보기.. Resource는 이름으로 연결
	@Resource(name="boardService")
	private BoardService boardService;
	
	
	@GetMapping("/board")
	public String board(Model model) { //서비스에서 값 가져옵시다//서비스에서 값 가져옵시다
		model.addAttribute("list", boardService.boardList()); //list란 이름으로 보드서비스에서 보드리스트를 불러옴
		boardService.boardList();
		return "board";
	}
	
	//http://localhost:8080/pro1/detail?bno=118
	//파라미터 잡기.. 각 페이지마다 bno 숫자가 다르니까
	@GetMapping("/detail")
	public String detail(HttpServletRequest request, Model model) { //디테일이 들어올때 값까지 같이 들어오게 하는거.. 사용자가 118번 글을 클릭했을때 그 내용이 HttpServletRequest request에 들어있는거..
		String bno = request.getParameter("bno");
		//bno에 요청하는 값이 있습니다. 이 값을 db까지 보내겠습니다.
		// System.out.println("bno : " + bno);
		
		BoardDTO dto = boardService.detail(bno); //보드서비스를 실행하면 dto가 나와 이런 느낌
		model.addAttribute("dto", dto);
		
		return "detail";
	}
	
}
