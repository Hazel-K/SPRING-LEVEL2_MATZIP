package blog.hyojin4588.matzip.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import blog.hyojin4588.matzip.Const;
import blog.hyojin4588.matzip.SecurityUtils;
import blog.hyojin4588.matzip.ViewRef;
import blog.hyojin4588.matzip.user.model.UserDMI;
import blog.hyojin4588.matzip.user.model.UserPARAM;
import blog.hyojin4588.matzip.user.model.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired // 하나만 있으면 자동 등록
	private UserService service;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute(Const.TITLE, "로그인");
		model.addAttribute(Const.VIEW, "/user/login");
		return ViewRef.DEFAULT;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(UserPARAM param, HttpSession hs, RedirectAttributes ra) {
//		System.out.println("id : " + param.getUser_id());
//		System.out.println("pw : " + param.getUser_pw());
		int result = service.login(param);
//		System.out.println("login : " + result);
//		return "redirect:/user/login";
		if (result == Const.SUCCESS) {
			hs.setAttribute(Const.LOGIN_USER, param);
			return "redirect:/";
		}

		String msg = null;
		if (result == Const.NO_ID) {
			msg = "아이디를 확인해주세요.";
		} else if (result == Const.NO_PW) {
			msg = "비밀번호를 확인해주세요.";
		}

		param.setMsg(msg);
		ra.addFlashAttribute("data", param); // 쿼리스트링의 흔적이 남지 않음. 메모리는 세션에 저장 후, 사용하고 지움

		return "redirect:/user/login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession hs) {
		hs.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model, @RequestParam(value = "err", required = false, defaultValue = "0") Integer err) {
//		System.out.println("err : " + err);

		if (err > 0) {
			model.addAttribute("msg", "에러가 발생했습니다. 에러코드:" + err);
		}

		model.addAttribute(Const.TITLE, "회원가입");
		model.addAttribute(Const.VIEW, "/user/join");
		return ViewRef.DEFAULT;
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserVO param, RedirectAttributes ra) {
		int result = service.join(param);

		if (result == 1) {
			return "redirect:/user/login";
		}

		ra.addAttribute("err", result);
		return "redirect:/user/join";
	}

	@RequestMapping(value = "/ajaxIdChk", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody // 이 자체의 내용이 응답이란 것을 알림
	public String ajaxIdChk(@RequestBody UserPARAM param) {
		int result = service.login(param);
		return String.valueOf(result);
	}
	
	@RequestMapping(value="/ajaxToggleFavorite", method=RequestMethod.GET)
	@ResponseBody
	public int ajaxToggleFavorite(UserPARAM param, HttpSession hs) {
		int i_user = SecurityUtils.getLoginUserPk(hs);
		param.setI_user(i_user);
		return service.ajaxToggleFavorite(param);
	}
	
	@RequestMapping(value="/favorite", method=RequestMethod.GET)
	public String favorite(Model model, HttpSession hs) {
		int i_user = SecurityUtils.getLoginUserPk(hs);
		UserPARAM param = new UserPARAM();
		param.setI_user(i_user);
		
		model.addAttribute("data", service.selFavoriteList(param));
		model.addAttribute(Const.CSS, new String[]{"userFavorite"});
		model.addAttribute(Const.TITLE, "찜 리스트");
		model.addAttribute(Const.VIEW, "/user/favorite");
		return ViewRef.TYPE_1;
	}

}
