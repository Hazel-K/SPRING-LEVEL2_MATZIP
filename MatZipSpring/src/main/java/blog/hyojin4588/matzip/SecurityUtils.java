package blog.hyojin4588.matzip;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import blog.hyojin4588.matzip.user.model.UserVO;

public class SecurityUtils {
	
	// 비밀번호와 salt를 조합한 최종 비밀번호를 리턴하는 메소드
	public static String getEncrypt(String source, String salt) {
        return BCrypt.hashpw(source, salt);
    }

    // 비밀번호와 함께 사용하는 salt를 생성하는 메소드
	public static String generateSalt() {
		return BCrypt.gensalt();
	}

// 출처: https://javannspring.tistory.com/105 [JiGyeong's study room]
	
	// 로그인 정보가 있는지를 확인하는 메소드
	public static boolean isLogout(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute(Const.LOGIN_USER);
		return loginUser == null;
	}
	
	public static UserVO getLoginUser(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute(Const.LOGIN_USER);
		return loginUser;
	}
	
	// 로그인한 사람의 id번호를 가져오는 메소드
	public static int getLoginUserPk(HttpServletRequest request) {
		return getLoginUser(request).getI_user();
	}
}