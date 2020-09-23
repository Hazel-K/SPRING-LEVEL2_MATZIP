package blog.hyojin4588.matzip.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import blog.hyojin4588.matzip.model.CodeVO;
import blog.hyojin4588.matzip.model.CommonMapper;
import blog.hyojin4588.matzip.rest.model.RestDMI;
import blog.hyojin4588.matzip.rest.model.RestPARAM;

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

}
