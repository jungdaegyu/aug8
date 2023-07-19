package com.gyu.pro1;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
	//user -> Controller -> Service -> DAO -> mybatis -> DB
	
	
	//Autowired(데이터타입이 맞으면 ㅇㅋ) 말고 Resource로 연결해보기.. Resource는 이름으로 연결
	@Resource(name="boardService")
	private BoardService boardService;
	
	//@Autowired
	//private Util util; //util.java랑 연결해서 ip 검사하려는듯.... 컴포넌트 util과 연결했음.. 원래는 Util new 막 이렇게 했어야했는데
	
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
	
	@GetMapping("/write") //화면만 보여주는 녀석
	public String write() { 
		return "write";
	}
	
	
	
	@PostMapping("/write") //글쓰기 클릭하면 포스트로 들어옴
	public String write(HttpServletRequest request) { //write 메소드가 똑같기 때문에 HttpServletRequest 씀
		//사용자가 입력한 데이터 변수에 담기
		//System.out.println(request.getParameter("title")); //wirte.jsp에서 지정했었음
		//System.out.println(request.getParameter("content"));
		//System.out.println("===============================");
			
		BoardDTO dto = new BoardDTO();
		dto.setBtitle(request.getParameter("title")); //write.jsp에서 지정을 해주었음
		dto.setBcontent(request.getParameter("content"));
		dto.setBwrite("평택남");//이건 임시로 적음.. 로그인 추가되면 변경
		
		//Service -> DAO -> maybatis -> DB로 보내서 저장하기
		boardService.write(dto);
		
		return "redirect:board"; //글을 쓰면 다시 보드를 실행시켜라는 뜻.. 다시 컨트롤러 지나가기 get방식으로 감
	}
	
	
	//삭제를 누르면 어떻게 해야할지 하는 것
	@GetMapping("/delete") //****************board-mapper에서 설정해줘서 컨트롤러에서 서비스로 서비스에서 디에이오로 그 다음 매퍼에서 다시 역순으로 돌아감 **************
	public String delete(@RequestParam(value = "bno") int bno) { // @RequestParam("bno") 자동으로 int로 바꿔줘서 int bno에 들어감 //HttpServletRequest의 getParameter(); 사실 있어도 없어도 괜찮을듯 true, false 오류 방지하려고 하는거..
		// System.out.println("bno : " + bno);
		
		
		BoardDTO dto = new BoardDTO();
		dto.setBno(bno);
		// dto.setBwrite(null) 사용자 정보
		//추후 로그인을 하면 사용자의 정보도 담아서 보냅니다. 글을 쓴 사람만 자기 글을 삭제할 수 있도록 하기 위함
		
		
		boardService.delete(dto);
		
		return "redirect:board"; //삭제를 완료한 후에 다시 보드로 갑니다.
		
	}
	
	
	
}
