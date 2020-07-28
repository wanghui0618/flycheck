package com.dhcc.piccbid.dto.user;

import java.util.List;

import com.dhcc.piccbid.entity.user.User;
import com.dhcc.piccbid.entity.user.UserVo;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wyz
 * @date 2019-01-09 12:41:56
 * @version V1.0
 */
public class UserDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private User user;
	private List<User> users;
	private String msgStr; 
	private String inFlag;
	private String name;
	private String oldPassword;
	private String role;
	private String status;
	private String remark;
	private String inFlag1;
	private String inFlag2;
	private String inFlag3;
	private String inFlag4;
	private String inFlag5;
	private String inFlag6;
	private String inFlag7;
	private String inFlag8;
	private UserVo userVo;
	private String cityName;
	private String keyDom;
	
	
	public String getKeyDom() {
		return keyDom;
	}

	public void setKeyDom(String keyDom) {
		this.keyDom = keyDom;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	public String getInFlag8() {
		return inFlag8;
	}

	public void setInFlag8(String inFlag8) {
		this.inFlag8 = inFlag8;
	}

	public String getInFlag7() {
		return inFlag7;
	}

	public void setInFlag7(String inFlag7) {
		this.inFlag7 = inFlag7;
	}

	public String getInFlag4() {
		return inFlag4;
	}

	public void setInFlag4(String inFlag4) {
		this.inFlag4 = inFlag4;
	}

	public String getInFlag5() {
		return inFlag5;
	}

	public void setInFlag5(String inFlag5) {
		this.inFlag5 = inFlag5;
	}

	public String getInFlag6() {
		return inFlag6;
	}

	public void setInFlag6(String inFlag6) {
		this.inFlag6 = inFlag6;
	}

	public String getInFlag3() {
		return inFlag3;
	}

	public void setInFlag3(String inFlag3) {
		this.inFlag3 = inFlag3;
	}

	public String getInFlag1() {
		return inFlag1;
	}

	public void setInFlag1(String inFlag1) {
		this.inFlag1 = inFlag1;
	}

	public String getInFlag2() {
		return inFlag2;
	}

	public void setInFlag2(String inFlag2) {
		this.inFlag2 = inFlag2;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getInFlag() {
		return inFlag;
	}

	public void setInFlag(String inFlag) {
		this.inFlag = inFlag;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	/**  
	 * @return msgStr 
	 */
	public String getMsgStr() {
		return msgStr;
	}
	/**  
	 * @param msgStr msgStr 
	 */
	public void setMsgStr(String msgStr) {
		this.msgStr = msgStr;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
}
