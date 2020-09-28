package blog.hyojin4588.matzip.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import blog.hyojin4588.matzip.CommonUtils;
import blog.hyojin4588.matzip.Const;
import blog.hyojin4588.matzip.FileUtils;
import blog.hyojin4588.matzip.SecurityUtils;
import blog.hyojin4588.matzip.model.CodeVO;
import blog.hyojin4588.matzip.model.CommonMapper;
import blog.hyojin4588.matzip.rest.model.RestDMI;
import blog.hyojin4588.matzip.rest.model.RestFile;
import blog.hyojin4588.matzip.rest.model.RestPARAM;
import blog.hyojin4588.matzip.rest.model.RestRecMenuVO;

@Service
public class RestService {

	@Autowired
	private RestMapper mapper;
	
	@Autowired
	private CommonMapper cMapper;

	public List<RestDMI> selRestList(RestPARAM param) {
		return mapper.selRestList(param);
	}
	
	public int insRest(RestPARAM param) {
		return mapper.insRest(param);
	}
	
	public List<CodeVO> selCategoryList() {
		CodeVO p = new CodeVO();
		p.setI_m(1);
		
		return cMapper.selCodeList(p);
	}
	
	public RestDMI selRest(RestPARAM param) {
		return mapper.selRest(param);
	}
	
	@Transactional // 롤백을 가능하게 해줌
	public void delRestTran(RestPARAM param) {
		mapper.delRestRecMenu(param); // 메소드를 넣을 경우, 컨트롤러의 try catch문을 제거
		mapper.delRestMenu(param);
		mapper.delRest(param);
	}
	
	public int delRestRecMenu(RestPARAM param) {
		return mapper.delRestRecMenu(param);
	}
	
	public int delRestMenu(RestPARAM param) {
		if(param.getMenu_pic() != null && !"".equals(param.getMenu_pic())) {
			String path = Const.REALPATH + "resources/img/rest/" + param.getI_rest() + "/menu/";
			if(FileUtils.delFile(path + param.getMenu_pic())) {
				return mapper.delRestMenu(param);
			} else {
				return Const.FAIL;
			}
		}
		return mapper.delRestMenu(param);
	}
	
	public int insRecMenus(MultipartHttpServletRequest mReq) {
		int i_user = SecurityUtils.getLoginUserPk(mReq.getSession());
		int i_rest = Integer.parseInt(mReq.getParameter("i_rest"));
		if(_authFail(i_rest, i_user)) {
			return Const.FAIL;
		}
		List<MultipartFile> fileList = mReq.getFiles("menu_pic");
		String[] menuNmArr = mReq.getParameterValues("menu_nm");
		String[] menuPriceArr = mReq.getParameterValues("menu_price");
		
		
		String path = Const.REALPATH + "/resources/img/rest/" + i_rest + "/rec_menu/";

		List<RestRecMenuVO> list = new ArrayList<RestRecMenuVO>();

		for(int i=0; i<menuNmArr.length; i++) {
			RestRecMenuVO vo = new RestRecMenuVO();
			list.add(vo);

			String menu_nm = menuNmArr[i];
			int menu_price = CommonUtils.parseStringToInt(menuPriceArr[i]);
			
			vo.setI_rest(i_rest);
			vo.setMenu_nm(menu_nm);
			vo.setMenu_price(menu_price);

			MultipartFile mf = fileList.get(i);
			String saveFileNm = FileUtils.saveFile(path, mf);
			vo.setMenu_pic(saveFileNm);
		}
		
		for(RestRecMenuVO vo: list) {
			mapper.insRestRecMenu(vo);
		}
		
		return i_rest;
	}
	
	public List<RestRecMenuVO> selRestRecMenus(RestPARAM param) {
		return mapper.selRecMenus(param);
	}

	public int delRestRecMenu(RestPARAM param, String realPath) {
		List<RestRecMenuVO> list = mapper.selRecMenus(param);
		if(list.size() == 1) {
			RestRecMenuVO item = list.get(0);
			if(item.getMenu_pic() != null && !item.getMenu_pic().equals("")) {
				File file = new File(realPath + item.getMenu_pic());
				if (file.exists()) {
					if (file.delete()) {
						return mapper.delRestRecMenu(param);
					} else {
						return 0;
					}
				}
			}
		}
		return mapper.delRestRecMenu(param);
	}

	public int insMenus(RestFile param, HttpSession hs) {
//		System.out.println("/Service.insMenus/");
		int i_user = SecurityUtils.getLoginUserPk(hs);
		int i_rest = param.getI_rest();
		if (_authFail(i_rest, i_user)) { // 유저가 맞지 않을 경우
			return Const.FAIL;
		}
		
		String path = Const.REALPATH +"resources/img/rest/" + param.getI_rest() + "/menu/";
//		System.out.println("경로 : " + realPath);
//		System.out.println("가게 ID : " + param.getI_rest());
		if (param.getMenu_pic().size() != 0) {
//			System.out.println("이미지 개수 : " + param.getMenu_pic().size());
			for(MultipartFile file : param.getMenu_pic()) {
				RestRecMenuVO vo = new RestRecMenuVO();
				
//				System.out.println("다음 파일이 비었나? " + file.isEmpty());
				if(file.isEmpty()) { continue; }
				
				String saveFileNm = FileUtils.saveFile(path, file);
//				System.out.println("해당 파일 이름 : " + originFileNm);
//				System.out.println("저장되는 파일 이름 : " + saveFileNm);
				
				vo.setMenu_pic(saveFileNm);
				vo.setI_rest(param.getI_rest());
				mapper.insMenu(vo);
			}
			return 1;
		}
		return 0;
	}
	
	private boolean _authFail(int i_rest, int i_user) {
		RestPARAM param = new RestPARAM();
		param.setI_rest(i_rest);
		
		RestDMI dbResult = mapper.selRest(param);
		if(dbResult == null || dbResult.getI_user() != i_user) {
			return true;
		}
		return false;
	}

	public List<RestRecMenuVO> selRestMenus(RestPARAM param) {
		return mapper.selMenus(param);
	}

	public void addHits(RestPARAM param, HttpServletRequest req) {
		String myIp = req.getRemoteAddr();
		ServletContext ctx = req.getServletContext();
		
		int i_user = SecurityUtils.getLoginUserPk(req);
		
		String currentRestReadIp = (String)ctx.getAttribute(Const.CURRNET_REST_READ_IP + param.getI_rest());
		if(currentRestReadIp == null || !"".equals(currentRestReadIp)) {
			param.setI_user(i_user);
			mapper.updAddHits(param);
			ctx.setAttribute(Const.CURRNET_REST_READ_IP + param.getI_rest(), myIp);
		}
	}
	
}
