package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class YoilTellerMVC4 {
	
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		ex.printStackTrace();
		return "yoilError";
	}
	
	@RequestMapping("/getYoilMVC4")
//	public void main(HttpServletRequest request, HttpServletResponse response)throws IOException {
	public String main(MyDate date, Model model)throws IOException {
		
		// 1. 유효성 검사 
		if(!isVaild(date)) {
			return "yoilError";
		}

		// 2. 요일 계산
		char yoil = getYoil(date);
		
		// 3. 계산환 결과를 model 에 저장
		model.addAttribute("myDate", date);
		model.addAttribute("yoil", yoil);
		
		return "yoil"; // /WEB-INF/views/yoil.jsp
		


	}

	private boolean isVaild(MyDate date) {
		return isVaild(date.getYear(),date.getMonth(),date.getDay());
	}

	private char getYoil(MyDate date) {
		return getYoil(date.getYear(),date.getMonth(),date.getDay());
	}

	private boolean isVaild(int year, int month, int day) {
		// TODO Auto-generated method stub
		return true;
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1:일요일, 2:월요일 ...
		return " 일월화수목금토".charAt(dayOfWeek);
	}

}
