package com.gyu.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	@Autowired	
	private LoginDAO loginDAO;
	
	
	public LoginDTO login(LoginDTO dto) {
		return loginDAO.login(dto);
		
	}


	public int join(JoinDTO joinDTO) {
		
		return loginDAO.join(joinDTO);
	}


	public List<JoinDTO> members() { //전체 회원 뽑기
		
		return loginDAO.members();
	}	
	

	
}
