package com.fastcampus.ch2;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 1. ������ ���� 
		session.invalidate();
		// 2. Ȩ���� �̵� 
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}
	
	@PostMapping("/login")
	public String login(String id, String pwd, String toURL, boolean rememberId, 
			HttpSession session, HttpServletResponse response) throws Exception {
		// 1. id�� pwd�� Ȯ��
		if(!loginCheck(id,pwd)) {
			// 2-1.	��ġ���� ������, loginForm ���� �̵� 
			String msg = URLEncoder.encode("id �Ǵ� pwd�� ��ġ���� �ʽ��ϴ�.","utf-8");
			
			return "redirect:/login/login?msg="+msg;
		}
		// 2-2. id�� pwd�� ��ġ�ϸ�, 
		// ���� ��ü�� ������
//		HttpSession session = request.getSession();
//		�Ű������� request �� �޾Ƽ� getSession() ���� ���� ��ü�� �����ص� ������ ���ʿ� �Ű������� session �� ��� ��ü �����ص� ��
		// ���� ��ü�� id �� ����
		session.setAttribute("id", id);
		
		if(rememberId) {
//			1. cookie �� ����
			Cookie cookie = new Cookie("id",id);
//			2. ���信 cookie ���� 
			response.addCookie(cookie);			
		} else {
			// ��Ű�� ���� 
			Cookie cookie = new Cookie("id",id);
			cookie.setMaxAge(0); 
			response.addCookie(cookie);
		}
//				3. Ȩ���� �̵� 
		toURL = toURL == null || toURL.equals("") ? "/" : toURL;
		return "redirect:"+toURL;
	}

	private boolean loginCheck(String id, String pwd) {
		return "asdf".equals(id) && "1234".equals(pwd);
	}

}
