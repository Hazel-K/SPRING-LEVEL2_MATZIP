package blog.hyojin4588.matzip.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.hyojin4588.matzip.Const;
import blog.hyojin4588.matzip.SecurityUtils;
import blog.hyojin4588.matzip.user.model.UserDMI;
import blog.hyojin4588.matzip.user.model.UserDTO;
import blog.hyojin4588.matzip.user.model.UserVO;

@Service
public class UserService {

	@Autowired
	private UserMapper mapper;
	
	// 1: 로그인 성공, 2: 아이디 없음, 3: 비밀번호 틀림
 	public int login(UserDTO param) {
 		if(param.getUser_id().equals("")) {
 			return Const.NO_ID;
 		}
 		if (param.getUser_pw().equals("")) {
 			return Const.NO_PW;
 		}
 		
 		UserDMI dbUser = mapper.selUser(param);
 		
 		return Const.SUCCESS;
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
