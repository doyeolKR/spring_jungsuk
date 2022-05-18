package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

// ������� �Է��ϸ� ������ �˷��ִ� ���α׷�
@Controller
public class YoilTellerMVC5 {
	
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		ex.printStackTrace();
		return "yoilError";
	}
	
	@RequestMapping("/getYoilMVC5")
//	public void main(HttpServletRequest request, HttpServletResponse response)throws IOException {
//	public String main(@ModelAttribute("myDate") Mydate date, Model model)throws IOException {
	public String main(@ModelAttribute MyDate date, Model model)throws IOException {
		
		// 1. ��ȿ�� �˻� 
		if(!isVaild(date)) {
			return "yoilError";
		}

		// 2. ���� ���
//		char yoil = getYoil(date);
		
		// 3. ���ȯ ����� model �� ����
//		model.addAttribute("myDate", date);
//		model.addAttribute("yoil", yoil);
		
		return "yoil"; // /WEB-INF/views/yoil.jsp
		


	}

	private boolean isVaild(MyDate date) {
		return isVaild(date.getYear(),date.getMonth(),date.getDay());
	}

	private @ModelAttribute("yoil") char getYoil(MyDate date) {
		return getYoil(date.getYear(),date.getMonth(),date.getDay());
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