package blog.hyojin4588.matzip.user;

import org.apache.ibatis.annotations.Mapper;

import blog.hyojin4588.matzip.user.model.UserVO;

@Mapper
public interface UserMapper {
	
	public int insUser(UserVO p);

}
