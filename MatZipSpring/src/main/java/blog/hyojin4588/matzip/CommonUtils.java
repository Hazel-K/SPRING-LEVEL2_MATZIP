package blog.hyojin4588.matzip;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

public class CommonUtils {
	
	// 세션을 가져오는 메소드
	public static HttpSession getSession(HttpServletRequest request) {
		return request.getSession();
	}
	// 세션을 가져오는 메소드
	
	// String을 int형으로 변환시키는 메소드
	public static int parseStringToInt(String arg0) {
		return parseStringToInt(arg0, 0);
	}
	
	public static int parseStringToInt(String arg0, int arg1) {
		try {
			return Integer.parseInt(arg0);
		} catch (Exception e) {
			return arg1;
		}
	}
	// String을 int형으로 변환시키는 메소드
	
	// String을 double형으로 변환시키는 메소드
	public static double parseStringToDouble(String arg0) {
		return parseStringToDouble(arg0, 0);
	}
	
	public static double parseStringToDouble(String arg0, int arg1) {
		try {
			return Double.parseDouble(arg0);
		} catch (Exception e) {
			return arg1;
		}
	}
	// String을 double형으로 변환시키는 메소드
	
	// Parameter를 int로 변환하여 가져오는 메소드
	public static int getIntParameter(String key, HttpServletRequest request) {
		return parseStringToInt(request.getParameter(key));
	}
	public static int getIntParameter(String key, MultipartRequest multi) {
		return parseStringToInt(multi.getParameter(key));
	}
	
	// Parameter를 double로 변환하여 가져오는 메소드
	public static double getDoubleParameter(String key, HttpServletRequest request) {
		return parseStringToDouble(request.getParameter(key));
	}
	
	// Parameter를 File로 변환하여 가져오는 메소드
	public static File getFileParameter(String key, MultipartRequest multi) {
		return null;
	}

}
