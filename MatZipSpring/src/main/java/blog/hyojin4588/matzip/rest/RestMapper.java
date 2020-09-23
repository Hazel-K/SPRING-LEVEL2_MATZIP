package blog.hyojin4588.matzip.rest;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import blog.hyojin4588.matzip.rest.model.RestDMI;
import blog.hyojin4588.matzip.rest.model.RestPARAM;
import blog.hyojin4588.matzip.rest.model.RestRecMenuVO;

@Mapper
public interface RestMapper {
	
	List<RestDMI> selRestList(RestPARAM p);
	int insRest(RestPARAM p);
	RestDMI selRest(RestPARAM p);
	int delRest(RestPARAM p);
	int delRestRecMenu(RestPARAM p);
	int delRestMenu(RestPARAM p);
	int insRestRecMenu(RestRecMenuVO p);

}
