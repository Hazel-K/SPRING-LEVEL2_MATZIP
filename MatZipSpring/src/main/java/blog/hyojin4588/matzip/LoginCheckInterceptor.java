package blog.hyojin4588.matzip;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import blog.hyojin4588.matzip.user.model.UserPARAM;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession hs = request.getSession();
		UserPARAM loginUser = (UserPARAM)hs.getAttribute(Const.LOGIN_USER);
		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");
		boolean isLogout = SecurityUtils.isLogout(request);
		
		if("res".equals(uriArr[1])) {
			return true;
		} else if (uriArr.length < 3) { // 주소 이상한 경우
			return false;
		}
		
		switch(uriArr[1]) {
		case ViewRef.URI_USER:
			switch(uriArr[2]) {
			case "login": case "join":
				if(!isLogout) { // 로그인 되어 있는 상태
					response.sendRedirect("/restaurant/resMap");
					return false;
				}
			}
		case ViewRef.URI_RESTAURANT:
			switch(uriArr[2]) {
			case "resReg":
				if(isLogout) { // 로그아웃 상태
					response.sendRedirect("/user/login");
					return false;
				}
			}
		}
		
		return true;
	}
	
}
