package blog.hyojin4588.matzip.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import blog.hyojin4588.matzip.user.model.UserDMI;
import blog.hyojin4588.matzip.user.model.UserPARAM;
import blog.hyojin4588.matzip.user.model.UserVO;

@Mapper
public interface UserMapper {
	
	int insUser(UserVO p);
	UserDMI selUser(UserPARAM p);
	int insFavorite(UserPARAM p);
	int delFavorite(UserPARAM p);
	List<UserDMI> selFavoriteList(UserPARAM param);

}
