package blog.hyojin4588.matzip;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import blog.hyojin4588.matzip.rest.model.RestPARAM;

public class RestInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");
		String[] checkKeyword = { "del", "Del", "upd" };
		
		for(String word : checkKeyword) {
			if(uriArr[2].contains(word)) {
				int i_rest = CommonUtils.getIntParameter("i_rest", request);
				if(i_rest == 0) {
					return false;
				}
				int i_user = SecurityUtils.getLoginUserPk(request);
				
				boolean result = _authSuccess(i_rest,i_user);
				return result;
			}
		}
		
		return true;
	}

	private boolean _authSuccess(int i_rest, int i_user) {
		RestPARAM param = new RestPARAM();
		param.setI_rest(i_rest);
		return false;
	}
}
