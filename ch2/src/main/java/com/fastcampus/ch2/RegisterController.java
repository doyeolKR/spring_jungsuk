package com.fastcampus.ch2;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
	@RequestMapping(value="/register/add", method= {RequestMethod.GET, RequestMethod.POST})
//	@RequestMapping("/register/add") // 신규회원 가입 화면
//	@GetMapping("/register/add")
	public String register() {
		return "registerForm"; //Web-Inf / views / registerForm.jsp
	}
	
//	@RequestMapping(value="/register/save", method=RequestMethod.POST)
	@PostMapping("/register/save") // 4.3 부터 
	public String save(User user, Model m)throws Exception {
		// 1. 유효성 검사 
		if(!isValid(user)) {
			String msg = URLEncoder.encode("정보를 잘못 입력하셨습니다.","utf-8");
			
			m.addAttribute("msg", msg); 
//			model 은 redirect 같은 재요청에는 원래 사용 불가능. spring 이 알아서 아래와 같은 코드로 변경해주는 것.
			return "forward:/register/add";
//			return "redirect:/register/add?msg="+msg; // URL 재작성 (rewriting)
		}
		
		// 2. DB에 신규회원 저장
		return "registerInfo";
	}

private boolean isValid(User user) {
	return false;
}

}
