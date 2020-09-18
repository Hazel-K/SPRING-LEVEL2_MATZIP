package blog.hyojin4588.matzip.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.hyojin4588.matzip.SecurityUtils;
import blog.hyojin4588.matzip.user.model.UserVO;

@Service
public class UserService {

	@Autowired
	private UserMapper mapper;
	
 	public int login(UserVO param) {
 		
 		return 2;
 	}
 	
 	public int join(UserVO param) {
 		String pw = param.getUser_pw();
 		String salt = SecurityUtils.generateSalt();
 		String cryptPw = SecurityUtils.getEncrypt(pw, salt);
 		
 		param.setSalt(salt);
 		param.setUser_pw(cryptPw);
 		
 		return mapper.insUser(param);
 	}
	
}
