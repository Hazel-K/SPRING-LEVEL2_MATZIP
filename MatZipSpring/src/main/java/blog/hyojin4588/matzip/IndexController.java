package blog.hyojin4588.matzip;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index(HttpServletRequest req) {
		if(Const.REALPATH == null) {
			Const.REALPATH = req.getServletContext().getRealPath("");
		}
		System.out.println("root Booting");
		return "redirect:/restaurant/resMap";
	}

}
