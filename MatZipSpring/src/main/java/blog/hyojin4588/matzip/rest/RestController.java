package blog.hyojin4588.matzip.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import blog.hyojin4588.matzip.Const;
import blog.hyojin4588.matzip.ViewRef;
import blog.hyojin4588.matzip.user.UserService;
import blog.hyojin4588.matzip.user.model.UserPARAM;

@Controller
@RequestMapping("/restaurant")
public class RestController {
	
	@Autowired // 하나만 있으면 자동 등록
	private RestService service;
	
	@RequestMapping(value="resMap", method=RequestMethod.GET)
	public String resMap(Model model) {
		model.addAttribute(Const.TITLE, "지도");
		model.addAttribute(Const.VIEW, "/restaurant/resMap");
		return ViewRef.TYPE_1;
	}

}
