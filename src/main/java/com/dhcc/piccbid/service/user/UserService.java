package com.dhcc.piccbid.service.user;


import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.user.UserDto;
import com.dhcc.piccbid.entity.user.User;
import com.dhcc.piccbid.entity.user.UserVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author gzw
 * @version V1.0
 * @date 2019-01-09 12:41:56
 */
public interface UserService extends BaseService<User, String> {

    PageModel list(UserDto dto);

    User checkPassword(String userNamePage);

    User checkUserPhone(User user);

    User checkUserEmail(User user);

    User checkUserEmail1(String email);

    Boolean updatePassword(String newpassPage, String id);

    Boolean updateNewInformation(String newnamePage, String newphonePage, String newemailPage, String id);

    User findById(User user);

    Boolean setNewPassword(String newPassword, String id);

    User findUserById(String id);

    Boolean setAuditing(String id, String remark, String name);

    Boolean setReauditing(String id, String remark, String name);

    UserVo listUserVo(UserDto dto);
    
    UserVo query(UserDto dto);

    User checkUserLoginName(User user);

    String userTree(UserDto dto);

    List<UserVo> users(UserDto dto);

    PageModel listTreeTable(UserDto dto);

    void saveAutho(UserDto dto);

    void saveDataAutho(UserDto dto);

    void saveButtonAutho(UserDto dto);


    void addInitialPermissions(User userNow);

    void saveLogInformation(String id, Date log, Date loginoutTime);

	void updateLoginStatus(String id);

	void updateLoginoutStatus(String id);

	void updatePhoto(String id, String photo);

	List<UserVo> latestLogin(UserDto dto);

	PageModel listNumber(UserDto dto);

	void deleteUserAuthority(String userId);

	void roleChange(User user);

	String roleDelete(String id,String key);

	/**
	 * @param dto 
	 * @param num 
	 * @return
	 */
	PageModel listNewLogin(UserDto dto, String num);

	//获取角色变更后的全息信息
    Map getNewUserAuthority(List<String> roleAuthorityIdsBefore, List<String> roleAuthorityIdsAfter, List<String> userAuthorityIds);

    void creatHospUsers();

}
