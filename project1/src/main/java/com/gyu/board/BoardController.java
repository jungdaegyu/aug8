package com.gyu.board;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gyu.page.PageDTO;
import com.gyu.util.Util;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class BoardController {
	//user -> Controller -> Service -> DAO -> mybatis -> DB
	
	
	//Autowired(데이터타입이 맞으면 오케) 말고 Resource로 연결해보기.. Resource는 이름으로 연결
	@Resource(name="boardService")
	private BoardService boardService;
	
	@Autowired //7월 20일 12시
	private Util util; //우리가 만든 숫자 변환을 사용하기 위해서 객체 연결했어요.
	
	
	@GetMapping("/board")
	public String board(@RequestParam(value="pageNo", required = false, defaultValue = "1") int pageNo, Model model) { //서비스에서 값 가져옵시다
		//페이지네이션인포 -> 값 넣고 -> DB -> jsp //7.26
		// paginationInfo에 필수 정보를 넣어준다.
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageNo);//현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10); //한 페이지에 게시되는 게시물 건수
		paginationInfo.setPageSize(10); //페이징 리스트의 사이즈
		//전체 글 수 가져오는 명령문장
		int totalCount = boardService.totalCount();
		paginationInfo.setTotalRecordCount(totalCount); //전체 게시물 건수
		
		int firstRecordIndex = paginationInfo.getFirstRecordIndex(); //시작 위치
		int recordCountPerPage = paginationInfo.getRecordCountPerPage();//페이지당 몇 개?
		
		//System.out.println(firstRecordIndex);
		//System.out.println(recordCountPerPage);
		//System.out.println(pageNo);
		//System.out.println(totalCount);
		
		PageDTO page = new PageDTO();
		page.setFirstRecordIndex(firstRecordIndex);
		page.setRecordCountPerPage(recordCountPerPage);
		
		//보드 서비스 수정합니다. 7.26
		List<BoardDTO> list = boardService.boardList(page);
		
		model.addAttribute("list", list); //list란 이름으로 보드서비스에서 보드리스트를 불러옴
		//페이징 관련 정보가 있는 PaginationInfo 객체를 모델에 반드시 넣어준다. //7.26
		model.addAttribute("paginationInfo", paginationInfo);
		//boardService.boardList();
		return "board";
	}
	
	//http://localhost:8080/pro1/detail?bno=118
	//파라미터 잡기.. 각 페이지마다 bno 숫자가 다르니까
	@GetMapping("/detail")
	public String detail(HttpServletRequest request, Model model) { //디테일이 들어올때 값까지 같이 들어오게 하는거.. 사용자가 118번 글을 클릭했을때 그 내용이 HttpServletRequest request에 들어있는거..
		// String bno = request.getParameter("bno");
		int bno = util.strToInt(request.getParameter("bno")); //7월 20일 12시 수정
		//bno에 요청하는 값이 있습니다. 이 값을 db까지 보내겠습니다.
		// Systemout.println("bno : " + bno);
		
		
		//DTO로 변경합니다.
		BoardDTO dto = new BoardDTO();
		dto.setBno(bno);
		
		BoardDTO result = boardService.detail(dto);  //08-04 //들어가서 디테일을 볼건데 댓글이 0보다 크네? 그러면 댓글(commentsList)를 번호와 같이 실어서 보내줌
		// System.out.println(result.getCommentcount()); //댓글 갯수 찍어보기
		if (result.getCommentcount() > 0) {
			//데이터베이스에 물어봐서 jsp로 보냅니다.
			List<Map<String, Object>> commentsList = boardService.commentsList(bno);
			model.addAttribute("commentsList", commentsList);
		}
		model.addAttribute("dto", result);
		
		return "detail";
	}
	
	@GetMapping("/write") //화면만 보여주는 녀석
	public String write(HttpServletRequest request) { 
		HttpSession session = request.getSession(); //7.24 로그인 안한 사람은 글을 못쓰게 막는거 주소창에 write라고 쳐도 안들어가지게
		if (session.getAttribute("mname") != null) {
		
			return "write";
		} else {
			return "redirect:/login"; //슬러시 넣어주세요
		}

		
	}
	
	
	@PostMapping("/write") //글쓰기 클릭하면 포스트로 들어옴
	public String write2(HttpServletRequest request) { //write 메소드가 똑같기 때문에 HttpServletRequest 씀
		//사용자가 입력한 데이터 변수에 담기
		//System.out.println(request.getParameter("title")); //wirte.jsp에서 지정했었음
		//System.out.println(request.getParameter("content"));
		//System.out.println("===============================");
			
		HttpSession session = request.getSession();
		if (session.getAttribute("mid") != null) {
			//로그인 했습니다. = 아래 로직을 여기로 가져오세요.
			BoardDTO dto = new BoardDTO();
			dto.setBtitle(request.getParameter("title")); //write.jsp에서 지정을 해주었음
			dto.setBcontent(request.getParameter("content"));
			//세션에서 불러오겠습니다.
			dto.setM_id((String)session.getAttribute("mid"));//세션에서 가져옴
			dto.setM_name((String)session.getAttribute("mname"));//세션에서 가져옴 //강사님은 현재 주석처리
			dto.setUuid(UUID.randomUUID().toString());
			

			
			//Service -> DAO -> maybatis -> DB로 보내서 저장하기
			boardService.write(dto);
			
			return "redirect:board"; //글을 쓰면 다시 보드를 실행시켜라는 뜻.. 다시 컨트롤러 지나가기 get방식으로 감
			
		} else {
			//로그인 안했어요. = 로그인 하세요.
			return "redirect:/login";
		}

	}
	
	
	@GetMapping("/delete") //****************board-mapper에서 설정해줘서 컨트롤러에서 서비스로 서비스에서 디에이오로 그 다음 매퍼에서 다시 역순으로 돌아감 **************
	public String delete(@RequestParam(value = "bno") int bno, HttpSession session) { // @RequestParam("bno") 자동으로 int로 바꿔줘서 int bno에 들어감 //HttpServletRequest의 getParameter(); 사실 있어도 없어도 괜찮을듯 true, false 오류 방지하려고 하는거..
		//로그인 여부 확인해주세요.
		// System.out.println("mid : "+ session.getAttribute("mid"));
		
		if (session.getAttribute("mid") != null) { //여기 만들어주고 있음@@@@@@@@@@@@@@@@@
			BoardDTO dto = new BoardDTO();
			
			
		}
		
		
		BoardDTO dto = new BoardDTO();
		dto.setBno(bno);
		dto.setM_id((String)session.getAttribute("mid"));
		
		// dto.setBwrite(null) 사용자 정보
		//추후 로그인을 하면 사용자의 정보도 담아서 보냅니다. 글을 쓴 사람만 자기 글을 삭제할 수 있도록 하기 위함
		
		
		boardService.delete(dto);
		
		return "redirect:board"; //삭제를 완료한 후에 다시 보드로 갑니다.
		
	}
	
	
	@GetMapping("/edit") //수정하기를 만드는거임.. 일단 수정하려면 내가 썼던 글을 다시 보여줘야 하니까 get으로 보여줌
	public ModelAndView edit(HttpServletRequest request) { //bno가 반드시 들어와야함
		//로그인 하지 않으면 로그인 화면으로 던져주세요.
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		
		if (session.getAttribute("mid") != null) {	
			//dto를 하나 만들어서 거기에 담겠습니다. = bno, mid
			BoardDTO dto = new BoardDTO();
			dto.setBno(util.strToInt(request.getParameter("bno")));
			//내 글만 수정할 수 있도록 세션에 있는 mid도 보냅니다.
			dto.setM_id((String)session.getAttribute("mid"));
			
			//데이터베이스에 bno를 보내서 dto를 얻어옵니다. 	
			BoardDTO result = boardService.detail(dto);
			
			if (result != null) {//내 글을 수정했습니다.
				mv.addObject("dto", result);//mv에 실어보냅니다
				mv.setViewName("edit");//이동할 jsp명을 적어줍니다.			
			} else {//다른 사람 글이라면 null입니다. 경고창으로 이동합니다.
				mv.setViewName("warning");
			}
	
					
		} else {
			//로그인 안했다. = login컨트롤러
			mv.setViewName("redirect:/login");
			
		}
		return mv;
	}
	
	@PostMapping("/edit")
	public String edit(BoardDTO dto) { 
		// System.out.println("map : " + map);
		
		//System.out.println(dto.getBtitle());
		//System.out.println(dto.getBcontent());
		//System.out.println(dto.getBno());	
		
		boardService.edit(dto);

		return "redirect:detail?bno=" + dto.getBno(); //보드가 아니라 글을 수정해서 다시 수정한 글이 들어오게..
		
	}
	
	//2023-08-07 입추, 프레임워크 프로그래밍	
	@GetMapping("/cdel") //bno랑 cno가 필수로 들어와야함
	public String cdel(@RequestParam Map<String, Object> map, HttpSession session) {
		//로그인 여부 검사
		if (session.getAttribute("mid") != null) {
			//값 들어왔는지 여부 검사			
			
			System.out.println(map);
			if (map.containsKey("bno") && map.get("cno") != null &&
					!(map.get("bno").equals("")) && !(map.get("cno").equals("")) &&
					util.isNum(map.get("bno")) && util.isNum(map.get("cno"))) {
				// System.out.println("여기로 들어왔습니다.");
				map.put("mid", session.getAttribute("mid"));
				int result = boardService.cdel(map);
				System.out.println("삭제 결과 : " +  result);
			}
			
		} 

		return "redirect:/detail?bno=" +map.get("bno");
	}
	

	
	
	
}
