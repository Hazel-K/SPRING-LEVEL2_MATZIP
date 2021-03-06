package blog.hyojin4588.matzip.rest;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import blog.hyojin4588.matzip.rest.model.RestDMI;
import blog.hyojin4588.matzip.rest.model.RestPARAM;
import blog.hyojin4588.matzip.rest.model.RestRecMenuVO;

@Mapper
public interface RestMapper {
	// 인터페이스는 public abstract가 생략되어 있음
	List<RestDMI> selRestList(RestPARAM p);
	RestDMI selRest(RestPARAM p);
	List<RestRecMenuVO> selRecMenus(RestPARAM p);
	List<RestRecMenuVO> selMenus(RestPARAM p);
	int insRest(RestPARAM p);
	int insRestRecMenu(RestRecMenuVO p);
	int insMenu(RestRecMenuVO p);
	int delRest(RestPARAM p);
	int delRestRecMenu(RestPARAM p);
	int delRestMenu(RestPARAM p);
	int selRestChkUser(int p);
	int updAddHits(RestPARAM p);

}
