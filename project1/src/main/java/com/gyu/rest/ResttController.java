package com.gyu.rest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gyu.board.BoardService;
import com.gyu.login.LoginService;
import com.gyu.util.Util;

@RestController //ajax를 쓸 것이기 때문에 이렇게 적음 이렇게 적어놔서 조인jsp에서 에이작스로 작성해준 성공시 결과값 : 1이 출력이 된다??
public class ResttController {
	
	@Autowired 
	private LoginService loginService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private Util util;

	
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
	
	@GetMapping(value = "/boardList2", produces = "application/json; charset=UTF-8") //한글처리를 위해서 value이런걸 씀..
	public String boardList2(@RequestParam("pageNo") int pageNo) {
		System.out.println("jp가 보내주는 : " + pageNo);
		
		List<Map<String, Object>> list = loginService.boardList2((pageNo - 1) * 10);
		//System.out.println(list);
		JSONObject json = new JSONObject();
		JSONArray arr = new JSONArray(list); //10개짜리 맵 리스트를 다시 제이슨 배열에 담음
		json.put("totalCount", loginService.totalCount()); //제이슨 배열을 제이슨에 담음.... ajax를 하기 위해서 해야함.. 서로 통신하기 위해서 접점이 있어야하기 때문에
		json.put("pageNo", pageNo);
		json.put("list", arr);
		//System.out.println(json.toString());
		
		return json.toString(); //마지막에 toStirng으로 넘김
	}

	
	@PostMapping("/cdelR")
	public String cdelR(@RequestParam Map<String, Object> map, HttpSession session) {
		int result =0;
		
		if (session.getAttribute("mid") != null) {
			if (map.containsKey("bno") && map.get("cno") != null &&
					!(map.get("bno").equals("")) && !(map.get("cno").equals("")) &&
					util.isNum(map.get("bno")) && util.isNum(map.get("cno"))) {
				
				System.out.println(map);
				
				
				map.put("mid", session.getAttribute("mid"));
				result = boardService.cdel(map);
				System.out.println("삭제 결과 : " + result);
				

			}
		}
		JSONObject json = new JSONObject();
	      json.put("result", result);
	      return json.toString();
		
		
	}
	
	
	
}
