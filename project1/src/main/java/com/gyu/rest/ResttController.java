package com.gyu.rest;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gyu.login.LoginService;

@RestController //ajax를 쓸 것이기 때문에 이렇게 적음 이렇게 적어놔서 조인jsp에서 에이작스로 작성해준 성공시 결과값 : 1이 출력이 된다??
public class ResttController {
	
	@Autowired 
	private LoginService loginService;

	
	//아이디 중복검사 2023-08-02 
	@PostMapping("/checkID") //리퀘스트 파람은 필수로 들어와야 하는거.. httpservlet으로 하면 안되나..
	public String checkID(@RequestParam("id") String id) { // System.out.println("id: " + id); .. 	 															
		int result = loginService.checkID(id);
		//json
		JSONObject json = new JSONObject();
		json.put("result", result);

		
		return json.toString(); ////{result : 1} ... 조인jsp에서 데이터 타입을 제이슨으로 바꾸었기때문에
		
	}
	
	//boardList2
	
	@GetMapping("/boardList2")
	public String boardList2() {
		List<Map<String, Object>> list = loginService.boardList2();
		System.out.println(list);
		return "";
	}
	
	
}
