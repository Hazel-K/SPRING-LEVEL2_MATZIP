package blog.hyojin4588.matzip.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import blog.hyojin4588.matzip.Const;
import blog.hyojin4588.matzip.ViewRef;

@Controller
@RequestMapping("/user")
public class UserController {
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String _login(Model model) {
		model.addAttribute(Const.TITLE, "로그인");
		model.addAttribute(Const.VIEW, "/user/login");
		return ViewRef.DEFAULT;
	}
}
