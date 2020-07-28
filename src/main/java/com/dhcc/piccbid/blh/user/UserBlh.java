package com.dhcc.piccbid.blh.user;


import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.dto.user.UserDto;
import com.dhcc.piccbid.entity.user.User;
import com.dhcc.piccbid.service.user.UserService;
import com.dhcc.piccbid.utils.session.SessionContext;
import com.dhcc.piccbid.utils.session.SessionManage;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author CodeGenUtils
 * @version V1.0
 */
@Component
public class UserBlh extends BaseAbstractBlh<UserDto> {

		private static Log logger = LogFactory.getLog(UserBlh.class);
	
		@Resource
		private UserService userService;
	
		public UserBlh() {
			logger.debug("UserBlh Init");
		}
	
		/**
		 * 
		 * 进入某个列表的入口方法（分页查询方法）
		 * @param basedto
		 */
		public void list(BaseAbstractDto basedto) {
			UserDto dto = super.getExactlyDto(basedto);
			//调用service查询方法
			PageModel pageModel=userService.list(dto);
			//不要删除这行代码，调用set是为以后 service层要加缓存
			dto.setPageModel(pageModel);
		}
		
		public String userTree(BaseAbstractDto baseAbstractDto) {
			UserDto dto = super.getExactlyDto(baseAbstractDto);
			String jsonString = userService.userTree(dto);
			return jsonString;
		}
		
		public void listUserVo(BaseAbstractDto basedto) {
			UserDto dto = super.getExactlyDto(basedto);
			if(dto.getPageModel() == null){
				dto.setPageModel(new PageModel());
			}
			userService.listUserVo(dto);
			
		}
		
		public void query(BaseAbstractDto basedto) {
			UserDto dto = super.getExactlyDto(basedto);
			String keyDom = WebContextHolder.getContext().getRequest().getParameter("keyDom");
			if(keyDom!=null &&!"".equals(keyDom)) {
				dto.setKeyDom(keyDom);
			}
			if(dto.getPageModel() == null){
				dto.setPageModel(new PageModel());
			}
			userService.query(dto);
		}
		
		
		public void latestLogin(BaseAbstractDto basedto) {
			UserDto dto = super.getExactlyDto(basedto);
			if(dto.getPageModel() == null){
				dto.setPageModel(new PageModel());
			}
			userService.latestLogin(dto);
		}

		public void listTreeTable(BaseAbstractDto baseAbstractDto) {
			UserDto dto = super.getExactlyDto(baseAbstractDto);
			userService.listTreeTable(dto);
	
		}
		
		/**
		 * 
		 * 用户登录操作
		 * @param: basedto
		 */
		public void login(BaseAbstractDto basedto) {
			UserDto dto = super.getExactlyDto(basedto);
			String userNamePage = dto.getInFlag7();
			String passWordPage = dto.getInFlag8();
	        //根据获取的数据向数据库发送数据请求
			User userDb = null;
			try {
				userDb = userService.checkPassword(userNamePage);
			} catch (Exception e) {
				//这里抛出登录时数据库连接异常
				e.printStackTrace();
				dto.setMsgStr("0");//error 
	            return;
			}
			if(userDb!=null){//有这个用户名
				//判断查询结果
		        if (passWordPage.equals(userDb.getPassword())) {
					// session role对象
					WebContextHolder.getContext().getRequest().getSession().setAttribute("user", userDb);
					//密码正确
		            // 设置成功后就登陆操作,跳转到相应的主页上去
					dto.setMsgStr("1");//success
					//获取status的值传到前端
					dto.setStatus(userDb.getStatus());
					dto.setUser(userDb);
					System.out.println(userDb.getPhoto());
					//将登录时间放入到session中
					Date loginTime = new Date();
					WebContextHolder.getContext().getRequest().getSession(true).setAttribute("loginTime",loginTime);
					//将头像放入到session中
					String headimgurl="/storeFile/headimg/";
					WebContextHolder.getContext().getRequest().getSession(true).setAttribute("headimg",userDb.getPhoto());
					WebContextHolder.getContext().getRequest().getSession(true).setAttribute("headimgurl",headimgurl+userDb.getPhoto());
					
					//这里记录登录者的sessionId,当异地登录时记录新的sessionId,将老的sessionId移除
			    	if(SessionManage.USERID_SESSIONID.get(userDb.getId())!=null){
			    		String sessionOldId =  SessionManage.USERID_SESSIONID.get(userDb.getId());
			    		String sessionThisId = WebContextHolder.getContext().getRequest().getSession().getId();
			    		if(sessionOldId.equals(sessionThisId)) {
			    			//两个一样，说明是同一个地址
			    			//不做处理
			    		}else {
			    			//异地登录
			    			//Map中记录的sessionId为空，不处理
				    		//处理当不为空的时候，移除掉之前map中的session
				    		SessionContext sessionContext= SessionContext.getInstance();
					    	HttpSession session = sessionContext.getSession(SessionManage.USERID_SESSIONID.get(userDb.getId()));
					    	if(session!=null){
					    		//判断session是否为空，如果不为空，则删除对应的session
					    		session.invalidate();
					    	}
			    		}
			    	}
			    	SessionManage.USERID_SESSIONID.put(userDb.getId(), WebContextHolder.getContext().getRequest().getSession().getId());
		        } else {
		        	//密码不正确
		        	dto.setMsgStr("2");
		        	return;
		        }
			}else{
				//用户名（手机号或邮箱地址）不存在
				dto.setMsgStr("3");
				return;
			}
		}
		
		/**
		 * 
		 * 用户注册操作
		 * @param: basedto
		 */
		@Transactional
		public void register(BaseAbstractDto basedto) {
			UserDto dto = super.getExactlyDto(basedto);
			User user=dto.getUser();
			//判断用户名是否被注册过
			if(user.getLoginName()!=null&&!"".equals(user.getLoginName())){
				User user1 = userService.checkUserLoginName(user);
				if(user1!=null){
					//说明用户名已经被注册过了
					dto.setInFlag("1");
					return;
				}else{
					//判断手机号码是否已注册过
					if(user.getPhone()!=null && !"".equals(user.getPhone())){
						User user2=userService.checkUserPhone(user);
						if(user2!=null){
							//手机号码已存在
							dto.setInFlag("2");
							return;
						}else {
							//手机号码不存在，再去判断邮箱
							if(user.getEmail()!=null && !"".equals(user.getEmail())){
								User user3=userService.checkUserEmail(user);
								if(user3!=null){
									//说明邮箱已经存在了
									dto.setInFlag("3");
									return;
								}else {
										//用户名、手机、邮箱都没有被注册过
										dto.setInFlag("0");
										//默认注册用户roleCode都是"yk（游客）"
										user.setRoleId("001");
										user.setUnitId("yk");
										//默认注册用户的状态码为"1"审核通过
										user.setStatus("1");
										//添加注册时间
										dto.getUser().setAddDate(new Date());
										User userNow = userService.save(user);
										//根据角色添加初始权限
										//userService.addInitialPermissions(userNow);
									 	}
									}
								}
							}
						}
					}
		}
		
		/**
		 * 
		 * 忘记密码及密码找回操作
		 * @param: basedto
		 */
		public void forget(BaseAbstractDto basedto) {
			UserDto dto = super.getExactlyDto(basedto);
			User user=dto.getUser();
			String phonePage = user.getPhone();//页面输入的手机号
			String namePage = user.getName();//页面输入的姓名
			User userDb1 = null;
			try {
				//根据输入的邮箱查询
				userDb1 = userService.checkUserEmail1(user.getEmail());
			} catch (Exception e) {
				//这里抛出登录时数据库连接异常
				e.printStackTrace();
				dto.setInFlag("0");//error
	            return;
			}
			
			if(userDb1 != null){
				
				//邮箱地址正确，根据邮箱查出来的手机号和姓名与输入的对比
				if(phonePage.equals(userDb1.getPhone()) && namePage.equals(userDb1.getName())){
					
					dto.setInFlag("1");
					dto.setOldPassword(userDb1.getId());//获取id，传回给前端页面
					
				}else {
					//手机号或姓名错误，请重新输入
					dto.setInFlag("2");
					return;
				}
			}else{
				dto.setInFlag("3");
				return;
			}
		}
		
		/**
		 * 忘记密码，设置新密码
		 * @param
		 */
		public void setNewPassword(BaseAbstractDto basedto) {
			UserDto dto = super.getExactlyDto(basedto);
			String newPassword = dto.getInFlag1();//页面传过来的新密码
			String id = dto.getInFlag2();//页面传过来的id
			User userDb3 = null;
			try {
				userDb3 = userService.findUserById(id);
			} catch (Exception e) {
				//这里抛出登录时数据库连接异常
				e.printStackTrace();
				dto.setInFlag("0");//error
	            return;
			}
			
			
			if(newPassword.equals(userDb3.getPassword())){//说明设置新密码和老密码是一样的
				dto.setInFlag("2");
				return;
			}else{
				
				Boolean result = userService.setNewPassword(newPassword,userDb3.getId());
				
				if(result){
					//执行成功了
					dto.setInFlag("1");
				}else{
					//执行失败了
					dto.setInFlag("3");

            }
        }
    }

    /**
     * 管理员添加及修改用户信息操作
     *
     * @param: basedto
     */
    @Transactional
    public void save(BaseAbstractDto basedto) {
        UserDto dto = super.getExactlyDto(basedto);
        User user = dto.getUser();
        /*System.out.println(JSON.toJSONString(user));*/
        //通过id能找到用户就是修改，反之没找到就是新增
        if (user.getId() != null && !"".equals(user.getId())) {
            //能找到id说明是修改
            //通过id去获取手机号  user  user
            User user4 = userService.findById(user);
            Date add = user4.getAddDate();
            if (user.getRoleId().equals(user4.getRoleId())) {
                //角色没有变
                //手机号没有改变
                if (user4.getPhone().equals(user.getPhone())) {
                    //邮箱号没有改变
                    if (user4.getEmail().equals(user.getEmail())) {
                        dto.setInFlag("0");
                        user.setUpdateDate(new Date());
                        user.setAddDate(add);
                        userService.save(user);
                    } else {
                        //邮箱变了
                        User user6 = userService.checkUserEmail(user);
                        if (user6 != null) {
                            //邮箱已存在
                            dto.setInFlag("2");
                            return;
                        } else {
                            //邮箱不存在
                            dto.setInFlag("0");
                            user.setUpdateDate(new Date());
                            user.setAddDate(add);
                            userService.save(user);
                        }
                    }
                } else {
                    //手机号变了
                    //邮箱没有变
                    if (user4.getEmail().equals(user.getEmail())) {
                        User user7 = userService.checkUserPhone(user);
                        if (user7 != null) {
                            //说明手机号已经注册过了
                            dto.setInFlag("1");
                            return;
                        } else {
                            //手机号没有被注册
                            dto.setInFlag("0");
                            user.setUpdateDate(new Date());
                            user.setAddDate(add);
                            userService.save(user);
                        }
                    } else {
                        //邮箱变了
                        User user8 = userService.checkUserPhone(user);
                        User user9 = userService.checkUserEmail(user);
                        if (user8 != null) {
                            //说明手机号已经注册过了
                            dto.setInFlag("1");
                            return;
                        } else {
                            if (user9 != null) {
                                //邮箱已存在
                                dto.setInFlag("2");
                                return;
                            } else {
                                dto.setInFlag("0");
                                user.setUpdateDate(new Date());
                                user.setAddDate(add);
                                userService.save(user);
                            }
                        }
                    }
                }
            } else {
                //角色变了
                //手机号不变
                if (user4.getPhone().equals(user.getPhone())) {
                    //邮箱不变
                    if (user4.getEmail().equals(user.getEmail())) {
                        dto.setInFlag("0");
                        user.setUpdateDate(new Date());
                        user.setAddDate(add);
                        //只要角色改变，就需要审核
                        user.setStatus("0");
                        User user89 = userService.findById(user);
                        user.setRoleIdChange(user89.getRoleId());
                        userService.save(user);
                    } else {
                        //邮箱变了
                        User user10 = userService.checkUserEmail(user);
                        if (user10 != null) {
                            //邮箱已存在
                            dto.setInFlag("2");
                            return;
                        } else {
                            dto.setInFlag("0");
                            user.setUpdateDate(new Date());
                            user.setAddDate(add);
                            //只要角色改变，就需要审核
                            user.setStatus("0");
                            userService.save(user);
                        }
                    }
                } else {
                    //手机号变了
                    //邮箱不变
                    if (user4.getEmail().equals(user.getEmail())) {
                        User user11 = userService.checkUserPhone(user);
                        if (user11 != null) {
                            //说明手机号已经注册过了
                            dto.setInFlag("1");
                            return;
                        } else {
                            dto.setInFlag("0");
                            user.setUpdateDate(new Date());
                            user.setAddDate(add);
                            //只要角色改变，就需要审核
                            user.setStatus("0");
                            userService.save(user);
                        }
                    } else {
                        //邮箱变了
                        User user12 = userService.checkUserEmail(user);
                        User user13 = userService.checkUserPhone(user);
                        if (user12 != null) {
                            //邮箱已存在
                            dto.setInFlag("2");
                            return;
                        } else {
                            if (user13 != null) {
                                //说明手机号已经注册过了
                                dto.setInFlag("1");
                                return;
                            } else {
                                dto.setInFlag("0");
                                user.setUpdateDate(new Date());
                                user.setAddDate(add);
                                //只要角色改变，就需要审核
                                user.setStatus("0");
                                userService.save(user);
                            }
                        }
                    }
                }
            }
        } else {
            //新增
            if (user.getLoginName() != null && !"".equals(user.getLoginName())) {
                User user4 = userService.checkUserLoginName(user);
                if (user4 != null) {
                    //用户名已存在
                    dto.setInFlag("4");
                    return;
                } else {
                    if (user.getPhone() != null && !"".equals(user.getPhone())) {
                        User user1 = userService.checkUserPhone(user);
                        if (user1 != null) {
                            //手机号码已存在
                            dto.setInFlag("1");
                            return;
                        } else {
                            if (user.getEmail() != null && !"".equals(user.getEmail())) {
                                User user2 = userService.checkUserEmail(user);
                                if (user2 != null) {
                                    //说明邮箱已经存在了
                                    dto.setInFlag("2");
                                    return;
                                } else {
                                    //手机邮箱都没有被注册过且机构也为被注册过
                                    dto.setInFlag("0");
                                    //默认审核通过
                                    user.setStatus("1");
                                    //添加注册时间
                                    user.setAddDate(new Date());
                                    //默认密码为123qwe
                                    user.setPassword("46f94c8de14fb36680850768ff1b7f2a");
                                    //调用对应的service保存方法
                                   User userNow=userService.save(user);
									//根据角色添加初始权限
									//userService.addInitialPermissions(userNow);
                                    dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 给用户菜单授权
     * @param baseAbstractDto
     */

    public void saveAutho(BaseAbstractDto baseAbstractDto) {
        UserDto dto = super.getExactlyDto(baseAbstractDto);
        try {
            userService.saveAutho(dto);
            dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
        }catch (Exception e){
            dto.setOperateSuccess(BaseConstants.OPERATE_FAIL);
        }

    }
    /**
     * 给用户数据权限授权
     * @param baseAbstractDto
     */

    public void saveDataAutho(BaseAbstractDto baseAbstractDto) {
        UserDto dto = super.getExactlyDto(baseAbstractDto);
        try {
            userService.saveDataAutho(dto);
            dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
        }catch (Exception e){
            dto.setOperateSuccess(BaseConstants.OPERATE_FAIL);
        }

    }
    public void saveButtonAutho(BaseAbstractDto baseAbstractDto) {
        UserDto dto = super.getExactlyDto(baseAbstractDto);
        try {
            userService.saveButtonAutho(dto);
            dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
        }catch (Exception e){
            dto.setOperateSuccess(BaseConstants.OPERATE_FAIL);
        }

    }

    /**
     * 管理员删除用户操作
     *
     * @param basedto
     */
    @Transactional
    public void delete(BaseAbstractDto basedto) {
        UserDto dto = super.getExactlyDto(basedto);
        //调用对应的service删除方法
        userService.delete(dto.getUser().getId());
         // System.out.println(1/0);
        //删除用户授权表中的数据
       // userService.deleteUserAuthority(dto.getUser().getId());
        dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
    }

    /**
     * 根据ID查询实体的方法
     *
     * @param: basedto
     */
    public void findById(BaseAbstractDto basedto) {
        UserDto dto = super.getExactlyDto(basedto);

        //调用对应的service查询某个实体的方法
        User entity = userService.findOne(dto.getUser().getId());
        //不要删除这行代码，调用set是为以后 service层要加缓存
        dto.setUser(entity);
    }

    /**
     * 修改密码操作
     *
     * @param: basedto
     */
    public void changePassword(BaseAbstractDto basedto) {

        UserDto dto = super.getExactlyDto(basedto);

        String oldpassPage = dto.getInFlag1();//页面传过来的老密码
        String newpassPage = dto.getInFlag2();//页面传过来的新密码

        //从session中取出user对象
        User user = (User) WebContextHolder.getContext().getRequest().getSession().getAttribute("user");

        //输入的密码和数据库根据用户名查出来的密码一样
        if (oldpassPage.equals(user.getPassword())) {

            Boolean result = userService.updatePassword(newpassPage, user.getId());

            if (result) {
                //执行成功了
                dto.setInFlag("1");
            } else {
                //执行失败了
                dto.setInFlag("9");

            }
        } else {
            //输入的密码和根据用户查询出来的密码不一样
            dto.setInFlag("3");
            return;
        }
    }

		/**
		 * 
		 * 退出登录操作
		 * @param: basedto
		 */
		public void logout(BaseAbstractDto basedto) {
			
			WebContextHolder.getContext().getRequest().getSession().invalidate();
			
		}
		  
		/**
		 * 
		 * 修改基本信息操作
		 * @param: basedto
		 */
		public void updateNew(BaseAbstractDto basedto) {
			
			UserDto dto = super.getExactlyDto(basedto);
			
			String newnamePage=dto.getInFlag4();//页面传过来的新姓名
			String newphonePage=dto.getInFlag5();//页面传过来的新电话号码
			String newemailPage=dto.getInFlag6();//页面传过来的新邮箱
			
			//从session中取出user对象
			User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
			
			//页面输入的姓名已经有了
			if(newnamePage.equals(user.getName())){
				dto.setInFlag("1");
				return;
			}else if(newphonePage.equals(user.getPhone())){
				dto.setInFlag("2");
				return;
			}else if(newemailPage.equals(user.getEmail())){
				dto.setInFlag("3");
				return;
			}else{
				Boolean result = userService.updateNewInformation(newnamePage,newphonePage,newemailPage,user.getId());
				if(result){
					//执行成功了
					dto.setInFlag("4");
					User user1 =  (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
					user1.setName(newnamePage);
					user1.setPhone(newphonePage);
					user1.setEmail(newemailPage);
					WebContextHolder.getContext().getRequest().getSession().setAttribute("user", user1);
				}else{
					//执行失败了
					dto.setInFlag("9");
					
				}
			}
			
		}

		public void auditing(BaseAbstractDto basedto) {
			UserDto dto = super.getExactlyDto(basedto);
			User user=dto.getUser();
			//从session中取出user对象
			User user1 = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
			Boolean result = userService.setAuditing(user.getId(),user.getRemark(),user1.getName());
			if(result){
				dto.setInFlag("0");
				//执行审核通过后  更改用户的权限为新的角色的权限
                //userService.roleChange(user);
			}else{
				//执行失败了
				dto.setInFlag("1");
			}
			
		}

		public void reauditing(BaseAbstractDto basedto) {
			UserDto dto = super.getExactlyDto(basedto);
			User user=dto.getUser();
			//从session中取出user对象
			User user1 = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
			Boolean result = userService.setReauditing(user.getId(),user.getRemark(),user1.getName());
			if(result){
				dto.setInFlag("0");
			}else{
				//执行失败了
				dto.setInFlag("1");
			}
			
			
		}

		public void listNumber(BaseAbstractDto basedto) {
			UserDto dto = super.getExactlyDto(basedto);
			PageModel pageModel=userService.listNumber(dto);
			dto.setPageModel(pageModel);
		}

		public void updatePhoto(String id, String photo) {
			 userService.updatePhoto(id,photo);
		}

      public UserDto roleChange(UserDto dto){
          try {
             User user=dto.getUser();
              userService.roleChange(user);
              dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
          }
          catch (Exception e){
              dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
          }
          return dto;
      }

		/**
		 * @param num 
		 * @return
		 */
		public void listNewLogin(BaseAbstractDto basedto, String num) {
			// TODO Auto-generated method stub
			UserDto dto = super.getExactlyDto(basedto);
			PageModel pageModel = userService.listNewLogin(dto,num);
			dto.setPageModel(pageModel);
		}

		public void creatHospUsers(){
		    //批量创建医院用户
            userService.creatHospUsers();
        }

}
