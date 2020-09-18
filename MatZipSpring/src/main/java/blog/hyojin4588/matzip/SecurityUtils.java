package blog.hyojin4588.matzip;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import blog.hyojin4588.matzip.user.model.UserVO;

public class SecurityUtils {
	
	// 비밀번호와 salt를 조합한 최종 비밀번호를 리턴하는 메소드
	public static String getEncrypt(String source, String salt) {
        return getEncrypt(source, salt.getBytes());
    }
    
	// 비밀번호를 16진수를 이용하여 암호화시키는 메소드
    public static String getEncrypt(String source, byte[] salt) {
        String result = "";
        
        byte[] a = source.getBytes();
        byte[] bytes = new byte[a.length + salt.length];
        
        System.arraycopy(a, 0, bytes, 0, a.length);
        System.arraycopy(salt, 0, bytes, a.length, salt.length);
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(bytes);
            
            byte[] byteData = md.digest();
            
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
            }
            
            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return result;
    }

    // 비밀번호와 함께 사용하는 salt를 생성하는 메소드
	public static String generateSalt() {
		Random random = new Random(); // 난수를 지정하는 랜덤 객체 생성
		byte[] salt = new byte[8]; // 크기가 8인 byte 배열 생성
		random.nextBytes(salt); // 제공받은 salt Array의 크기만큼의 난수를 생성
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < salt.length; i++) {
			// byte 값을 hex로 바꾸기
//			System.out.println(String.format("%d : %s", i, salt[i]));
			sb.append(String.format("%02x", salt[i]));
		}
		
		return sb.toString();
	}

// 출처: https://javannspring.tistory.com/105 [JiGyeong's study room]
	
	// 로그인 정보가 있는지를 확인하는 메소드
	public static boolean isLogout(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute(Const.LOGIN_USER);
		return loginUser == null;
	}
	
	// 로그인한 사람의 id번호를 가져오는 메소드
	public static UserVO getLoginUser(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute(Const.LOGIN_USER);
		return loginUser;
	}
	
	public static int getLoginUserPk(HttpServletRequest request) {
		return getLoginUser(request).getI_user();
	}
}