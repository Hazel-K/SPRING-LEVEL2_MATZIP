package blog.hyojin4588.matzip.rest;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import blog.hyojin4588.matzip.Const;
import blog.hyojin4588.matzip.SecurityUtils;
import blog.hyojin4588.matzip.ViewRef;
import blog.hyojin4588.matzip.rest.model.RestDMI;
import blog.hyojin4588.matzip.rest.model.RestPARAM;

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
	
	@RequestMapping(value="/ajaxGetList")
	@ResponseBody
	public List<RestDMI> ajaxGetList(RestPARAM param) { // vo의 형태로 주고받더라도 json으로 parse는 자동(현재 Spring 기능에 포함)
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
		param.setI_user(SecurityUtils.getLoginUserPk(hs));
		int result = service.insRest(param);
		if(result == Const.SUCCESS) {
			return "redirect:/restaurant/resReg";
		}
		return "redirect:/restaurant/resReg";
	}
	
	@RequestMapping(value="/resDetail", method=RequestMethod.GET)
	public String resDetail(Model model, RestPARAM param) {
		RestDMI data = service.selRest(param);
		
		model.addAttribute("data", data);
		model.addAttribute(Const.TITLE, data.getNm());
		model.addAttribute(Const.VIEW, "/restaurant/resDetail");
		return ViewRef.TYPE_1;
	}

}
