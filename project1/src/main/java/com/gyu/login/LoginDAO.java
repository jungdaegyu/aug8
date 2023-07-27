package com.gyu.login;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAO {

	@Autowired
	private SqlSession sqlSession;

	public LoginDTO login(LoginDTO dto) {
		
		return sqlSession.selectOne("login.login", dto); 
		//네임스페이스가 로그인인 로그인을 만들어야함 로그인 매퍼도 만들고
	}

	public LoginDTO join(LoginDTO dto) {
		return sqlSession.selectOne("login.join", dto);
	}
	
	
	
}
