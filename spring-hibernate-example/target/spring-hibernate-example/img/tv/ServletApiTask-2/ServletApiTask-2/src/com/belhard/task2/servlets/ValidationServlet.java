package com.belhard.task2.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.belhard.task2.utils.BuilderFormUtils;
import com.belhard.task2.utils.HttpUtils;

@WebServlet(urlPatterns = "/ValidationServlet")
public class ValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sessionValue = "sessionValue";
		String sessionErr = "sessionErr";
		
		String firstName = "firstName";
		String secondName = "secondName";
		String middleName = "middleName";
		String secretePhraze = "secretePhraze";
		String age = "age";
		String gender = "gender";
		String course = "course";
		String teacher = "teacher";
		String mark = "mark";
		String orderTest = "orderTest";
		String otherRecommendations = "otherRecommendations";
		String commercialOtherText = "commercialOtherText";
		String orderCommercial = "orderCommercial";
		String formValidation = "formValidation";
		
		Map<String, Object> mapValue = new HashMap<String, Object>();
		HttpUtils.addValue(request, firstName, mapValue);
		HttpUtils.addValue(request, secondName, mapValue);
		HttpUtils.addValue(request, middleName, mapValue);
		HttpUtils.addValue(request, secretePhraze, mapValue);
		HttpUtils.addAge(request, age, mapValue);
		HttpUtils.addGender(request, gender, mapValue);
		HttpUtils.addCourse(request, course, mapValue);
		HttpUtils.addTeacher(request, teacher, mapValue);
		HttpUtils.addMark(request, mark, mapValue);
		HttpUtils.addOtherCourses(request, orderTest, mapValue);
		HttpUtils.addAdversting(request, orderCommercial, mapValue);
		HttpUtils.addValue(request, commercialOtherText, mapValue);
		HttpUtils.addValue(request, otherRecommendations, mapValue);
		HttpUtils.addJavascriptValidation(request, formValidation, mapValue);
		
		Map<String, Object> mapErr = new HashMap<String, Object>();
		HttpUtils.validationValue(request, firstName, mapErr);
		HttpUtils.validationValue(request, secondName, mapErr);
		HttpUtils.validationValue(request, secretePhraze, mapErr);
		HttpUtils.validationAge(request, age, mapErr);
		HttpUtils.validationEmptyOrString(request, gender, mapErr);
		HttpUtils.validationEmptyOrString(request, course, mapErr);
		HttpUtils.validationEmptyOrString(request, orderTest, mapErr);
		HttpUtils.validationEmptyOrString(request, orderCommercial, mapErr);
		HttpUtils.validationEmptyOrString(request, mark, mapErr);
		HttpUtils.validationEmptyOrString(request, teacher, mapErr);
		HttpUtils.validationFieldOtherString(request, mapValue, mapErr);
		BuilderFormUtils.resultAllInformation(mapErr, mapValue, response);
		 
		HttpSession session = request.getSession();
		session.setAttribute(sessionValue, mapValue);
		session.setAttribute(sessionErr, mapErr);
		
	}
}
