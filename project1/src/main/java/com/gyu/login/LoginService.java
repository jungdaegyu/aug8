package com.gyu.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	@Autowired	
	private LoginDAO loginDAO;
	
	
	public LoginDTO login(LoginDTO dto) {
		return loginDAO.login(dto);
		
	}	
	

	public LoginDTO join(LoginDTO dto) {
		return loginDAO.join(dto);
		
	}
	
	
}
