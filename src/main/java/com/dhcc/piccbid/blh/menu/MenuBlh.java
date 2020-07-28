package com.dhcc.piccbid.blh.menu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.menu.MenuDto;
import com.dhcc.piccbid.entity.menu.Menu;
import com.dhcc.piccbid.service.menu.MenuService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author CodeGenUtils
 * @version V1.0
 */
@Component
public class MenuBlh extends BaseAbstractBlh<MenuDto> {

	private static Log logger = LogFactory.getLog(MenuBlh.class);
	private static  final String ROLE_CODE="admin";
	@Resource
	private MenuService menuService;
	
	/*@Resource
	private UploadController uploadController;*/

	public MenuBlh() {
		logger.debug("MenuBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		MenuDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=menuService.list(dto);
		 if (dto.getMenu() == null) {
	            //调用service查询方法
	            pageModel = menuService.list(dto);
	        }
	        //如果其中一个值不为null，说明是查询
	        else if (dto.getMenu().getMenuName() != null || dto.getMenu().getMenuCode() != null) {
	            pageModel = menuService.search(dto);
	        }
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	public String listTree(BaseAbstractDto baseAbstractDto) {
        MenuDto dto = super.getExactlyDto(baseAbstractDto);
        String jsonString = menuService.menuList(dto);
        return jsonString;
    }

    public String listTreeNew(BaseAbstractDto baseAbstractDto) {
        MenuDto dto = super.getExactlyDto(baseAbstractDto);
        String jsonString = menuService.listTreeNew(dto);
        return jsonString;
    }

	public String listAllTree(BaseAbstractDto baseAbstractDto) {
	        MenuDto dto = super.getExactlyDto(baseAbstractDto);
	        String jsonString = menuService.menuAllList(dto);
	        return jsonString;
	    }

	    public String menuAuthoList(BaseAbstractDto baseAbstractDto) {
	        MenuDto dto = super.getExactlyDto(baseAbstractDto);
	        String jsonString = menuService.menuAuthoList(dto);
	        return jsonString;
	    }

	    public String roleMenuAuthoList(BaseAbstractDto baseAbstractDto) {
	        MenuDto dto = super.getExactlyDto(baseAbstractDto);
	        String jsonString = menuService.roleMenuAuthoList(dto);
	        return jsonString;
	    }

	    public String buttonAuthoList(BaseAbstractDto baseAbstractDto) {
	        MenuDto dto = super.getExactlyDto(baseAbstractDto);
	        String jsonString = menuService.buttonAuthoList(dto);
	        return jsonString;
	    }
	    public String roleButtonAuthoList(BaseAbstractDto baseAbstractDto) {
	        MenuDto dto = super.getExactlyDto(baseAbstractDto);
	        String jsonString = menuService.roleButtonAuthoList(dto);
	        return jsonString;
	    }

	    public String buttonAllTreeList(BaseAbstractDto baseAbstractDto) {
	        MenuDto dto = super.getExactlyDto(baseAbstractDto);
	       String jsonString = menuService.buttonAllTreeList(dto);
	        return jsonString;
	    }
	  
    public void listTreeTable(BaseAbstractDto baseAbstractDto) {
        MenuDto dto = super.getExactlyDto(baseAbstractDto);
        menuService.listTreeTableNew(dto);

    }

    public void getFirstMenu(BaseAbstractDto baseAbstractDto) {
        MenuDto dto = super.getExactlyDto(baseAbstractDto);
        menuService.getFirstMenu(dto);

    }


	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public MenuDto save(BaseAbstractDto basedto) {
		MenuDto dto = super.getExactlyDto(basedto);
		if (dto.getMenu().getMenuName() == null || dto.getMenu().getMenuName() == "") {
			dto.setInFlag("0");
           
        }//当传入的MenuName为null时
        else {
            //拿到新增的MenuName数据库查询，如果存在则新增失败，当使用count
            int count = menuService.isMenuExist(dto);
            if (count > 0) {
            	dto.setInFlag("2");
               
            } else {
                //调用对应的service保存方法
            	dto.setInFlag("1");
                menuService.save(dto.getMenu());
                //保存成功后，更新管理员的权限
               // String name="管理员";
               // updateAdminMenuAuthoForRole(ROLE_CODE);
                dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
            }
        }
		return dto;
	}
    //刷新某个用户为管理员的菜单权限
	public void updateAdminMenuAutho(String name){
        menuService.updateAdminMenuAutho(name);
    }

    //刷新角色为管理员的菜单权限
	public void updateAdminMenuAuthoForRole(String roleCode){
        menuService.updateAdminMenuAuthoForRole(roleCode);
    }
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
/*	public void delete(BaseAbstractDto basedto) {
		MenuDto dto = super.getExactlyDto(basedto);
		String id = dto.getMenu().getId();
		String excuteQuery = dto.getMenu().getMenuCode();
		if("0".equals(excuteQuery)) {
			//调用对应的service删除方法
			menuService.delete(id);
			dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
		}else {
			String[] arrays = getDeleteIds(dto);
			//调用对应的service删除方法
			menuService.deleteArray(arrays);
			dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
		}
	}*/
	//删除数据库内数据时一并删除图片
	public void delete(HttpServletRequest request,BaseAbstractDto basedto) {
		MenuDto dto = super.getExactlyDto(basedto);
		String id = dto.getMenu().getId();
		String excuteQuery = dto.getMenu().getMenuCode();
		//点击前图片路径
		String bef = dto.getMenu().getOnclickBef();
		//点击后图片路径
		String aft = dto.getMenu().getOnclickAft();
		if("0".equals(excuteQuery)) {
			//调用对应的service删除方法
			//uploadController.deleteimg(request, bef, aft);
			this.deleteimg(request, bef, aft);
			menuService.delete(id);
			dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
		}else {
			String[] arrays = getDeleteIds(request,dto);
			//调用对应的service删除方法
			menuService.deleteArray(arrays);
			dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
		}
	}
	private String[] getDeleteIds(HttpServletRequest request,MenuDto dto){
	       List<Menu> menuList=(List<Menu>)menuService.listTreeTable(dto).getPageData();
	       List<String> stringList=new ArrayList<>();
	       for (Menu menu:menuList){
	           stringList.add(menu.getId());
	           String bef=menu.getOnclickBef();
	           String aft=menu.getOnclickAft();
	           //uploadController.deleteimg(request, bef, aft);
	           this.deleteimg(request, bef, aft);
	       }
	       String[] ids=new String[menuList.size()];
	        stringList.toArray(ids);
	        return ids;
	    }
	
	   //删除图片 
    public void deleteimg(HttpServletRequest request,String fileNamebef, String fileNameAft) {
    	String a[]= {"/"};
		// 4.获取要保存的路径文件夹
		String realPath = request.getRealPath("images/main/");
		//System.out.println("realPath==="+realPath);
		if(fileNamebef!=null&&!("".equals(fileNamebef))){
		a=fileNamebef.split("/");
		fileNamebef=a[a.length-1];
    	String befpath = realPath + "\\" + fileNamebef;
    	File filebef = new File(befpath);
    	filebef.delete();
		}
		if(fileNameAft!=null&&!("".equals(fileNameAft))) {
		a=fileNameAft.split("/");
	    fileNameAft=a[a.length-1];
    	String aftpath = realPath + "\\" + fileNameAft;
    	File fileaft = new File(aftpath);
    	fileaft.delete();
		}
    }
    
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		MenuDto dto = super.getExactlyDto(basedto);
		//调用对应的service查询某个实体的方法
		Menu entity = menuService.findOne(dto.getMenu().getId());
//		if(entity.getId()!=null||entity.getId()!="") {
//			menuService.update();
//		}
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setMenu(entity);
	}

	/**
	 * @param
	 */
	public MenuDto update(MenuDto basedto) {
		MenuDto dto = super.getExactlyDto(basedto);
		Menu entity = menuService.findOne(dto.getMenu().getId());
		if(dto.getMenu().getMenuName() == null || dto.getMenu().getMenuName() == "") {
			dto.setInFlag("0");
		}else {
			 int count = menuService.isMenuExist(dto);
			 if(count>0) {
				 dto.setInFlag("2");
			 }else {
				 //判断编辑时没上传新的图片继续用旧图片地址
				 if(dto.getMenu().getOnclickBef()==null||"".equals(dto.getMenu().getOnclickBef())) {
					 dto.getMenu().setOnclickBef(entity.getOnclickBef());
				 }
				 if(dto.getMenu().getOnclickAft()==null||"".equals(dto.getMenu().getOnclickAft())) {
					 dto.getMenu().setOnclickAft(entity.getOnclickAft());
				 }
				 menuService.save(dto.getMenu());
				 dto.setInFlag("1");
			 }
		}
		return dto;
	}
}
