package edu.spring.java.lesson.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class HttpUtils {

	public static boolean StringOrEmpty (HttpServletRequest request, String parameter){
		String param = request.getParameter(parameter);
		if (param == null) {
			return false;
		} else if (param.equals("")){
			return false;
		}
		else return true;
	}
	
	public static boolean StringOrEmpty (String parameter){
		String param = parameter;
		if (param == null) {
			return false;
		} else if (param.equals("")){
			return false;
		}
		else return true;
	}
	
	public static boolean CheckPrincipal (){
		String param = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		if (param == null) {
			return false;
		} else if (param.equals("")){
			return false;
		}
		else return true;
	}
	
	public static boolean IntegerOrEmpty (HttpSession session){
		Integer i = (int)session.getAttribute("totalCost");
		if (i == null) {
			return false;
		} 
		else return true;
	}
	
	public static String UsersId (){
		String userData = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 if (principal instanceof UserDetails) {
		 userData = ((UserDetails)principal).toString();
		 } else {
		 userData = principal.toString();
		 }
		 return userData;
	}
	
	public static int StringSplitter (String line){
		int users_id = 0;
		String [] dataUsers = line.split(" "); 
		if (dataUsers.length!=1){
			dataUsers = line.split(" ");
			String user_id = dataUsers[0];
			users_id = Integer.parseInt(user_id);
		}
		return users_id;
	}

}
