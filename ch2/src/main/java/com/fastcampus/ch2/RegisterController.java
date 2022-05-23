package com.fastcampus.ch2;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("register")
public class RegisterController {
	
	@InitBinder // 이 컨트롤러 내에서만 적용 가능.
	public void toDate(WebDataBinder binder) {
		ConversionService conversionservice = binder.getConversionService();
//		System.out.println("conversionservice="+conversionservice);
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df,false));
		binder.registerCustomEditor(String[].class, "hobby" ,new StringArrayPropertyEditor("#"));
//		binder.setValidator(new UserValidator()); // UserValidator를 WebDataBinder의 로컬 validator로 등록
//		binder.addValidators(new UserValidator()); // UserValidator를 WebDataBinder의 로컬 validator로 등록
		List<Validator> validatorList = binder.getValidators();
		System.out.println("validatorList="+validatorList);
	}
	
	@RequestMapping(value="/add", method= {RequestMethod.GET, RequestMethod.POST})
//	@RequestMapping("/register/add") // 신규회원 가입 화면
//	@GetMapping("/register/add")
	public String register() {
		return "registerForm"; //Web-Inf / views / registerForm.jsp
	}
	
//	@RequestMapping(value="/register/save", method=RequestMethod.POST)
	@PostMapping("/save") // 4.3 부터 
	public String save(@Valid User user, BindingResult result, Model m)throws Exception {
		
		System.out.println("result="+result);
		System.out.println("user="+user);
		
		
		// 수동 검증 - Validator를  직접 생성하고, validate()를 직접 호출 
//		UserValidator userValidator = new UserValidator();
//		userValidator.validate(user, result); // BindingResult는 Errors의 자손 
		
		// User 객체를 검증한 결과 에러가 있으면, registerForm 을 이용해서 에러를 보여줘야 함. 
		if(result.hasErrors()) {
			return "registerForm";
		}
		
//		// 1. 유효성 검사 
//		if(!isValid(user)) {
//			String msg = URLEncoder.encode("정보를 잘못 입력하셨습니다.","utf-8");
//			
//			m.addAttribute("msg", msg); 
////			model 은 redirect 같은 재요청에는 원래 사용 불가능. spring 이 알아서 아래와 같은 코드로 변경해주는 것.
//			return "forward:/register/add";
////			return "redirect:/register/add?msg="+msg; // URL 재작성 (rewriting)
//		}
		
		// 2. DB에 신규회원 저장
		return "registerInfo";
	}

private boolean isValid(User user) {
	return true;
}

}
