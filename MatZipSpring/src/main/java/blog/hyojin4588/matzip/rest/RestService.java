package blog.hyojin4588.matzip.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import blog.hyojin4588.matzip.CommonUtils;
import blog.hyojin4588.matzip.FileUtils;
import blog.hyojin4588.matzip.model.CodeVO;
import blog.hyojin4588.matzip.model.CommonMapper;
import blog.hyojin4588.matzip.rest.model.RestDMI;
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
		return mapper.delRestMenu(param);
	}
	
	public int insRecMenus(MultipartHttpServletRequest mReq) {
		int i_rest = Integer.parseInt(mReq.getParameter("i_rest"));
		List<MultipartFile> fileList = mReq.getFiles("menu_pic");
		String[] menuNmArr = mReq.getParameterValues("menu_nm");
		String[] menuPriceArr = mReq.getParameterValues("menu_price");
		String path = mReq.getServletContext().getRealPath("resources/img/rest/"+i_rest+"/rec_menu/");

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

			if(mf.isEmpty()) { continue; }

			String originFileNm = mf.getOriginalFilename();
			String ext = FileUtils.getExt(originFileNm);
			String savaFileNm = UUID.randomUUID()+ext;

			try {
				mf.transferTo(new File(path + savaFileNm));
				vo.setMenu_pic(savaFileNm);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for(RestRecMenuVO vo: list) {
			mapper.insRestRecMenu(vo);
		}
		
		return i_rest;
	}

}
