package com.gyu.pro1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	
	@GetMapping("/login") //화면만 보여줄거야
	public String login() {
		return "login";
	}
	
	
	@PostMapping("/login")
	public String login(HttpServletRequest request) {
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("pw"));
		LoginDTO dto = new LoginDTO();
		
		dto.setM_id(request.getParameter("id"));
		dto.setM_pw(request.getParameter("pw"));
		
		//생각해주세요. id/pw를 보냈을 때 무엇이 왔으면 좋을까요?
		
		//로그인을 했을때 이름을 가져온다.. 누구님 안녕하세요.
		
		//이름 + count(*)
		LoginDTO result = loginService.login(dto); 
		
		//result는 다 실행시키고 받은 값
		
		//System.out.println(result.getM_name());
		//System.out.println(result.getCount());
		
		
		if (result.getCount() == 1) { //1이면 정상적으로 로그인한거임
			//세션을 만들어서 로글인을 지정 시간 동안 유지시킵니다.
			HttpSession session = request.getSession(); //그냥 외우자 세션을 만드는거고 여기에 담는거임 로그인을 유지시키려고..?
			session.setAttribute("mname", result.getM_name()); // menu.jsp로 감 
			session.setAttribute("mid", request.getParameter("id")); //result에선 못끄냄 왜냐하면 mapper에 sql
			//세션 : 서버, 쿠키 : 클라이언트
			return "redirect:index"; //정상적으로 로그인 했다면 인덱스로 가기
			
		} else {
			
			return "login"; //암호/ 아이디가 일치하지 않은 사람은 다시 로그인 하기
		}	
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		if (session.getAttribute("mname") != null) {
			// session.invalidate(); //세션 삭제하기
			session.removeAttribute("mname");
		}
		if (session.getAttribute("mid") != null) {
			// session.invalidate(); //세션 삭제하기
			session.removeAttribute("mid");
		}
		
		session.setMaxInactiveInterval(0); //세션 유지 시간을 0으로 종료시키기
		
		session.invalidate();//세션 초기화 = 종료 = 세션의 모든 속성 값을 제거 --> 대부분 이거 씀
		
		return "redirect:index";
	}

}



