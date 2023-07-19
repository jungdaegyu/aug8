package com.gyu.pro1;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//Controller Service Repository 
//Component = 객체



@Component //이제 new를 쓰는건 dto밖에 없다고 생각해도 무방
public class Util { //따로 객체를 만들어준 이유는 계속 재활용하기 위해서
	
	public String exchange(String str) {
		str = str.replaceAll("<", "&lt;"); 
		str = str.replaceAll(">", "&gt;");	
		return str;
	}
	
	public String getIp() {
		HttpServletRequest request = (
				(ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		//상대방 ip 가져오기 2023-07-19 09시 27분
				String ip = null; //192.168.0.0 -> HttpServletRequest request가 있어야함
				ip = request.getHeader("X-Forwarded-For");

			      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			         ip = request.getHeader("Proxy-Client-IP");
			      }
			      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			         ip = request.getHeader("WL-Proxy-Client-IP");
			      }
			      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			         ip = request.getHeader("HTTP_CLIENT_IP");
			      }
			      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			         ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			      }
			      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			         ip = request.getHeader("X-Real-IP");
			      }
			      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			         ip = request.getHeader("X-RealIP");
			      }
			      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			         ip = request.getHeader("REMOTE_ADDR");
			      }
			      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			         ip = request.getRemoteAddr();
			      }
			      return ip;
		
	}
	
	
	
}
