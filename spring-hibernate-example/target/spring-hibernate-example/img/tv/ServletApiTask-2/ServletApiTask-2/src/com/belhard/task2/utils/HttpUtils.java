package com.belhard.task2.utils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

public class HttpUtils {
	
	private final static String emptyString = "";
	private final static String checkedString = "checked";
	private final static String selectedString = "selected";
	private final static String hiddenString = "hidden";
	private final static String regexPatternStandart = "[a-zA-Z]{1,100}";
	private final static String regexPatternAge = "[1]{1}[8-9]{1}|[2-9]{1}[0-9]{1}";

	public static String getStringOrEmpty (HttpServletRequest request, String parameter){
		String param = request.getParameter(parameter);
		if (param == null) {
			param = emptyString;
		}
		return param;
	}
	
	public static Map<String, Object> addValue(HttpServletRequest request,
			String parameter, Map<String, Object> map) {
		Map<String, Object> mapValue = map;
		String strRegex = HttpUtils.getStringOrEmpty(request, parameter);
		Pattern pattern = Pattern.compile(regexPatternStandart);
		Matcher matcher = pattern.matcher(strRegex);
		while (matcher.find()) {
			strRegex = matcher.group();
		}
		if (matcher.matches() == false) {
			strRegex = emptyString;
			mapValue.put(parameter, strRegex);
		}
		if (matcher.matches() == true) {
			mapValue.put(parameter, strRegex);
		}
		return mapValue;
	}

	public static Map<String, Object> addAge(HttpServletRequest request,
			String parameter, Map<String, Object> map) {
		Map<String, Object> mapValue = map;
		String ageRegex = getStringOrEmpty(request, parameter);
		Pattern pattern = Pattern.compile(regexPatternAge);
		Matcher matcher = pattern.matcher(ageRegex);
		while (matcher.find()) {
			ageRegex = matcher.group();
		}
		if (matcher.matches() == false) {
			ageRegex = emptyString;
			mapValue.put(parameter, ageRegex);
		}
		if (matcher.matches() == true) {
			mapValue.put(parameter, ageRegex);
		}
		return mapValue;
	}

	public static Map<String, Object> addGender(HttpServletRequest request,
			String parameter, Map<String, Object> map) {
		Map<String, Object> mapValue = map;
		String[] genderArray = new String[2];
		String[] array = { "Male", "Female", emptyString };
		String gender = getStringOrEmpty(request, parameter);
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < genderArray.length; j++) {
				if (gender.equals(array[j])) {
					genderArray[j] = checkedString;
				} else {
					genderArray[j] = emptyString;
				}
				mapValue.put(parameter, genderArray);
			}
		}
		return mapValue;
	}
	
	public static Map<String, Object> addCourse(HttpServletRequest request,
			String parameter, Map<String, Object> map) {
		Map<String, Object> mapValue = map;
		String course = getStringOrEmpty(request, parameter);
		String[] courseArray = new String[3];
		String[] array = { "JavaSE", "JavaEE", "Testing", emptyString };
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < courseArray.length; j++) {
				if (course.equals(array[j])) {
					courseArray[j] = selectedString;
				} else {
					courseArray[j] = emptyString;
				}
				mapValue.put(parameter, courseArray);
			}
		}
		return mapValue;
	}
	
	public static Map<String, Object> addTeacher(HttpServletRequest request,
			String parameter, Map<String, Object> map) {
		Map<String, Object> mapValue = map;
		String[] teacherArray = new String[3];
		String[] array = { "Ivanov Ivan Ivanovich", "Petrov Petr Petrovich",
				"Sidorov Sidor Sidorovich", emptyString };
		String teacher = getStringOrEmpty(request, parameter);
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < teacherArray.length; j++) {
				if (teacher.equals(array[j])) {
					teacherArray[j] = selectedString;
				} else {
					teacherArray[j] = emptyString;
				}
				mapValue.put(parameter, teacherArray);
			}
		}
		return mapValue;
	}
	
	public static Map<String, Object> addMark(HttpServletRequest request,
			String parameter, Map<String, Object> map) {
		Map<String, Object> mapValue = map;
		String[] markArray = new String[10];
		String mark = getStringOrEmpty(request, parameter);
		if(mark.equals(emptyString)){
			mark="0";
		}
		for (int i = 0; i < markArray.length; i++) { 
			markArray[i] = emptyString;
		}
		for (int i = 1; i < markArray.length+1; i++) {
			if ((Integer.parseInt(mark) ) == i) {
				markArray[i-1] = selectedString;
			}
		}
		mapValue.put(parameter, markArray);
		return mapValue;
	}
	
	public static Map<String, Object> addOtherCourses(
			HttpServletRequest request, String parameter,
			Map<String, Object> map) {
		Map<String, Object> mapValue = map;
		String[] courseArray = new String[10];
		String[] otherCourses = { "HTML", "CSS", "JavaScript", "PHP", "MySQL",
				"Python", "Flash", "JavaSE", "JavaEE", "Тестирование" };
		String course = getStringOrEmpty(request, parameter);
		if (course == null) {
			for (int i = 0; i < courseArray.length; i++) {
				courseArray[i] = emptyString;
			}
			mapValue.put(parameter, courseArray);
		}
		for (int i = 0; i < courseArray.length; i++) {
			courseArray[i] = emptyString;
		}
		String[] coursesSplit = course.split(", ");
		for (int i = 0; i < otherCourses.length; i++) {
			for (int j = 0; j < coursesSplit.length; j++) {
				if (coursesSplit[j].equals(otherCourses[i])) {
					courseArray[i] = selectedString;
				}
			}
		}
		mapValue.put(parameter, courseArray);
		return mapValue;
	}
	
	public static Map<String, Object> addAdversting(HttpServletRequest request,
			String parameter, Map<String, Object> map) {
		Map<String, Object> mapValue = map;
		String[] adverstingArray = new String[6];
		String[] array = { "Advertising in the Tv", "Advertising in the radio",
				"Advertising in the internet",
				"Advertising in the underground",
				"Advertising in the newspaper", "Other" };
		String adversting = getStringOrEmpty(request, parameter);
		if (adversting == null) {
			for (int i = 0; i < adverstingArray.length; i++) {
				adverstingArray[i] = emptyString;
			}
			mapValue.put(parameter, adverstingArray);
		}
		for (int i = 0; i < adverstingArray.length; i++) {
			adverstingArray[i] = emptyString;
		}
		String[] adverstingSplit = adversting.split(", ");
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < adverstingSplit.length; j++) {
				if (adverstingSplit[j].equals(array[i])) {
					adverstingArray[i] = checkedString;
				}
			}
		}
		mapValue.put(parameter, adverstingArray);
		return mapValue;
	}
	
	public static Map<String, Object> addJavascriptValidation(
			HttpServletRequest request, String parameter,
			Map<String, Object> map) {
		Map<String, Object> mapValue = map;
		String chekboxValue = emptyString;
		String formValidationTrue = "true";
		String formValidation = HttpUtils.getStringOrEmpty(request, parameter);
		if (formValidation.equals(formValidationTrue)) {
			chekboxValue = checkedString; 
		}
		mapValue.put(parameter, chekboxValue);
		return mapValue; 
	}

	public static Map<String, Object> validationValue(
			HttpServletRequest request, String parameter,
			Map<String, Object> map) {
		Map<String, Object> mapValue = map;
		String strRegex = getStringOrEmpty(request, parameter);
		Pattern pattern = Pattern.compile(regexPatternStandart);
		Matcher matcher = pattern.matcher(strRegex);
		while (matcher.find()) {
			strRegex = matcher.group();
		}
		if (matcher.matches() == false) {
			strRegex = emptyString;
			mapValue.put(parameter, strRegex);
		}
		if (matcher.matches() == true) {
			strRegex = hiddenString;
			mapValue.put(parameter, strRegex);
		}
		return mapValue;
	}
	
	public static Map<String, Object> validationAge(HttpServletRequest request,
			String parameter, Map<String, Object> map) {
		Map<String, Object> mapValue = map;
		String strRegex = getStringOrEmpty(request, parameter);
		Pattern pattern = Pattern.compile(regexPatternAge);
		Matcher matcher = pattern.matcher(strRegex);
		while (matcher.find()) {
			strRegex = matcher.group();
		}
		if (matcher.matches() == false) {
			strRegex = emptyString;
			mapValue.put(parameter, strRegex);
		}
		if (matcher.matches() == true) {
			strRegex = hiddenString;
			mapValue.put(parameter, strRegex);
		}
		return mapValue;
	}
	
	public static Map<String, Object> validationEmptyOrString(
			HttpServletRequest request, String parameter,
			Map<String, Object> map) {
		Map<String, Object> mapValue = map;
		String param = HttpUtils.getStringOrEmpty(request, parameter);
		if (param.equals(emptyString)) {
			param = emptyString;
			mapValue.put(parameter, param);
		} else {
			mapValue.put(parameter, hiddenString);
		}
		return mapValue;
	}

	public static Map<String, Object> validationFieldOtherString(
			HttpServletRequest request, Map<String, Object> map1,
			Map<String, Object> map2) {
		Map<String, Object> mapValue = map1;
		Map<String, Object> mapErr = map2;
		int numberPositionOfChekboxOrder = 5;
		String parameter = "fieldOtherString";
		String commercialOtherText = "commercialOtherText";
		String orderCommercial = "orderCommercial";
		String adverstingOtherField = (String) mapValue.get(commercialOtherText);
		String[] arrayAdversting = (String[]) mapValue.get(orderCommercial);
		String checkedFieldOther = arrayAdversting[numberPositionOfChekboxOrder];
		if (checkedFieldOther.equals(checkedString)
				&& adverstingOtherField.equals(emptyString)) {
			mapErr.put(parameter, emptyString);
		} else {
			mapErr.put(parameter, hiddenString);
		}
		return mapErr;
	}

	public static String getParameterValue(Map<String, Object> map, String name){
		Map<String, Object> mapValue = map;
		name = (String) mapValue.get(name);
		return name;
	}

	public static String getParameterValueGender(Map<String, Object> map, String name){
		Map<String, Object> mapValue = map;
		String[] gender = {"Male", "Female"};
		int[] positionGender = {0, 1};
		String[] arrayTest=(String[])mapValue.get(name);		
		for (int i = 0; i < arrayTest.length; i++) {
			if (arrayTest[positionGender[i]].equals(checkedString)){
				name = gender[i];
			}
		}
		return name;
	}
	
	public static String getParameterValueCourse(Map<String, Object> map, String name){
		Map<String, Object> mapValue = map;
		String[] course = {"JavaSE", "JavaEE", "Testing"};
		int[] positionCourse = {0, 1, 2};
		String[] arrayTest=(String[])mapValue.get(name);		
		for (int i = 0; i < arrayTest.length; i++) {
			if (arrayTest[positionCourse[i]].equals(selectedString)){
				name = course[i];
			}
		}
		return name;
	}
	
	public static String getParameterValueTeacher(Map<String, Object> map, String name){
		Map<String, Object> mapValue = map;
		String[] teacher = {"Ivanov Ivan Ivanovich", "Petrov Petr Petrovich", "Sidorov Sidor Sidorovich"};
		int [] positionTeacher = {0, 1, 2};
		String[] arrayTest=(String[])mapValue.get(name);		
		for (int i = 0; i < arrayTest.length; i++) {
			if (arrayTest[positionTeacher[i]].equals(selectedString)){
				name = teacher[i];
			}
		}
		return name;
	}
	
	public static String getParameterValueMark(Map<String, Object> map, String name){
		Map<String, Object> mapValue = map;
		String[] mark = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
		int[] positionMark = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		String[] arrayTest=(String[])mapValue.get(name);
		for (int i = 0; i < arrayTest.length; i++) {
			if (arrayTest[positionMark[i]].equals(selectedString)){
				name = mark[i];
			}
		}
		return name;
	}
	
	public static String getParameterValueAdversting(Map<String, Object> map,
			String name) {
		Map<String, Object> mapValue = map;
		String[] adversting = { "Advertising in the Tv",
				"Advertising in the radio", "Advertising in the internet",
				"Advertising in the underground",
				"Advertising in the newspaper", "Other" };
		int[] positionAdversting = { 0, 1, 2, 3, 4, 5 };
		String line = emptyString;
		String[] arrayTest = (String[]) mapValue.get(name);
		for (int i = 0; i < arrayTest.length; i++) {
			if (arrayTest[positionAdversting[i]].equals(checkedString)) {
				line = adversting[i] + ", " + line;
			}
		}
		if (!line.equals(emptyString)) {
			line = line.substring(0, line.length() - 2);
		}
		return line;
	}
	
	public static String getParameterValueAdverstingOther(
			Map<String, Object> map, String nameOrderCommercial,
			String nameOrderCommercialOther) {
		String line = emptyString;
		Map<String, Object> mapValue = map;
		String adversting = "Other";
		int positionAdversting = 5;
		String[] arrayTest = (String[]) mapValue.get(nameOrderCommercial);
		if (arrayTest[positionAdversting].equals(checkedString)) {
			line = adversting + ": "
					+ getParameterValue(mapValue, nameOrderCommercialOther)
					+ ". " + "<br>";
		}
		return line;
	}
	
	public static String getParameterValueOtherCourses(Map<String, Object> map,
			String name) {
		Map<String, Object> mapValue = map;
		String line = emptyString;
		String[] otherCourses = { "HTML", "CSS", "JavaScript", "PHP", "MySQL",
				"Python", "Flash", "JavaSE", "JavaEE", "Тестирование" };
		int[] positionOtherCourses = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		String[] arrayTest = (String[]) mapValue.get(name);
		for (int i = 0; i < arrayTest.length; i++) {
			if (arrayTest[positionOtherCourses[i]].equals(selectedString)) {
				line = otherCourses[i] + ", " + line;
			}
		}
		if (!line.equals(emptyString)) {
			line = line.substring(0, line.length() - 2);
		}
		return line;
	}

	public static String getParameterValueOtherRecommendations(
			Map<String, Object> map, String name) {
		Map<String, Object> mapValue = map;
		String line = getParameterValue(mapValue, name);
		String text = "Other recomendations - ";
		if (!line.equals(emptyString)){
			line = text + line + ". " + "<br>";
		}
		return line;
	}
}
