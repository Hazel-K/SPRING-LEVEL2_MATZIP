package blog.hyojin4588.matzip.rest;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import blog.hyojin4588.matzip.rest.model.RestDMI;
import blog.hyojin4588.matzip.rest.model.RestPARAM;

@Mapper
public interface RestMapper {
	
	public List<RestDMI> selRestList(RestPARAM p);
	public int insRest(RestPARAM p);
	public RestDMI selRest(RestPARAM p);
	public int delRest(RestPARAM p);
	public int delRestRecMenu(RestPARAM p);
	public int delRestMenu(RestPARAM p);

}
