package blog.hyojin4588.matzip.rest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import blog.hyojin4588.matzip.Const;
import blog.hyojin4588.matzip.ViewRef;
import blog.hyojin4588.matzip.rest.model.RestPARAM;
import blog.hyojin4588.matzip.user.model.UserPARAM;

@Controller
@RequestMapping("/restaurant")
public class RestController {
	
	@Autowired // 하나만 있으면 자동 등록
	private RestService service;
	
	@RequestMapping("/resMap")
	public String resMap(Model model) {
		model.addAttribute(Const.TITLE, "지도");
		model.addAttribute(Const.VIEW, "/restaurant/resMap");
		return ViewRef.TYPE_1;
	}
	
	@RequestMapping(value="/ajaxGetList", produces="application/json; charset=utf-8")
	@ResponseBody
	public String ajaxGetList(RestPARAM param) {
//		System.out.println(param.getSw_lat());
//		System.out.println(param.getNe_lng());
		return service.selRestList(param);
	}
	
	@RequestMapping(value="/resReg", method=RequestMethod.GET)
	public String resReg(Model model) {
		model.addAttribute("categoryList", service.selCategoryList());
		
		model.addAttribute(Const.TITLE, "가게 등록");
		model.addAttribute(Const.VIEW, "/restaurant/resReg");
		return ViewRef.TYPE_1;
	}
	
	@RequestMapping(value="/resReg", method=RequestMethod.POST)
	public String resReg(RestPARAM param, HttpSession hs) {
		UserPARAM user = (UserPARAM)hs.getAttribute(Const.LOGIN_USER);
//		System.out.println("iuser:" + user.getI_user());
		int result = service.insRest(param);
		if(result == Const.SUCCESS) {
			return "redirect:/restaurant/resReg";
		}
		return "redirect:/restaurant/resReg";
	}

}
