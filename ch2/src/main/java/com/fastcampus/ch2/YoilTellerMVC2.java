package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// ������� �Է��ϸ� ������ �˷��ִ� ���α׷�
@Controller
public class YoilTellerMVC2 {
	
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		ex.printStackTrace();
		return "yoilError";
	}
	
	@RequestMapping("/getYoilMVC2")
//	public void main(HttpServletRequest request, HttpServletResponse response)throws IOException {
	public String main(@RequestParam(required=true)int year, 
			@RequestParam(required=true)int month, 
			@RequestParam(required=true)int day, Model model)throws IOException {
		
		// 1. ��ȿ�� �˻� 
		if(!isVaild(year, month, day)) {
			return "yoilError";
		}

		// 2. ���� ���
		char yoil = getYoil(year, month, day);
		
		// 3. ���ȯ ����� model �� ����
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("yoil", yoil);
		
		return "yoil"; // /WEB-INF/views/yoil.jsp
		


	}

	private boolean isVaild(int year, int month, int day) {
		// TODO Auto-generated method stub
		return true;
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1:�Ͽ���, 2:������ ...
		return " �Ͽ�ȭ�������".charAt(dayOfWeek);
	}

}