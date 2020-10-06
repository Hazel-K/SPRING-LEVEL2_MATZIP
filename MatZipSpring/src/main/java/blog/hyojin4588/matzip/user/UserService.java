package blog.hyojin4588.matzip.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.hyojin4588.matzip.Const;
import blog.hyojin4588.matzip.SecurityUtils;
import blog.hyojin4588.matzip.rest.RestMapper;
import blog.hyojin4588.matzip.rest.model.RestPARAM;
import blog.hyojin4588.matzip.rest.model.RestRecMenuVO;
import blog.hyojin4588.matzip.user.model.UserDMI;
import blog.hyojin4588.matzip.user.model.UserPARAM;
import blog.hyojin4588.matzip.user.model.UserVO;

@Service
public class UserService {

	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private RestMapper restMapper;

	// 1: 로그인 성공, 2: 아이디 없음, 3: 비밀번호 틀림
	public int login(UserPARAM param) {
		if ("".equals(param.getUser_id())) { return Const.NO_ID; }
		UserDMI dbUser = mapper.selUser(param);
		if (null == dbUser) { return Const.NO_ID; }
		
		String inputPw = SecurityUtils.getEncrypt(param.getUser_pw(), dbUser.getSalt());
// 		System.out.println(param.getUser_pw());
// 		System.out.println(dbUser.getSalt());
// 		System.out.println(inputPw);
// 		System.out.println(dbUser.getUser_pw());
		if (!inputPw.equals(dbUser.getUser_pw())) { return Const.NO_PW; }
		
		param.setUser_pw(null);
		param.setNm(dbUser.getNm());
		param.setProfile_img(dbUser.getProfile_img());
		param.setI_user(dbUser.getI_user());
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

	public int ajaxToggleFavorite(UserPARAM param) { // i_user, i_rest, proc_type
		switch(param.getProc_type()) {
		case"ins":
			return mapper.insFavorite(param);
		case"del":
			return mapper.delFavorite(param);
		}
		return 0;
	}
	
	public List<UserDMI> selFavoriteList(UserPARAM param) {
		List<UserDMI> list = mapper.selFavoriteList(param);
		for(UserDMI vo : list) {
			RestPARAM param2 = new RestPARAM();
			param2.setI_rest(vo.getI_rest());
			
			List<RestRecMenuVO> eachRecMenuList = restMapper.selRecMenus(param2);
			vo.setMenuList(eachRecMenuList);
		}
		return list;
	}

}
