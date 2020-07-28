package com.dhcc.piccbid.blh.role;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.dhcc.piccbid.dto.procedure.ProcedureDto;
import com.dhcc.piccbid.entity.procedure.Procedure;
import com.dhcc.piccbid.service.procedure.ProcedureService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.role.RoleDto;
import com.dhcc.piccbid.entity.role.Role;
import com.dhcc.piccbid.service.role.RoleService;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author CodeGenUtils
 * @version V1.0
 */
@Component
public class RoleBlh extends BaseAbstractBlh<RoleDto> {

    private static Log logger = LogFactory.getLog(RoleBlh.class);

    @Resource
    private RoleService roleService;
    @Autowired
    private ProcedureService procedureService;

    public RoleBlh() {
        logger.debug("RoleBlh Init");
    }

    /**
     * 进入某个列表的入口方法（分页查询方法）
     *
     * @param basedto
     */
    public void list(BaseAbstractDto basedto) {
        PageModel pageModel = new PageModel();
        RoleDto dto = super.getExactlyDto(basedto);
        if (dto.getRole() == null) {
            //调用service查询方法
            pageModel = roleService.list(dto);
        }
        //如果其中一个值不为null，说明是查询
        else if (dto.getRole().getRoleName() != null || dto.getRole().getRoleCode() != null) {
            pageModel = roleService.search(dto);
        }
        //不要删除这行代码，调用set是为以后 service层要加缓存
        dto.setPageModel(pageModel);
    }

    public String listTree(BaseAbstractDto baseAbstractDto) {
        RoleDto dto = super.getExactlyDto(baseAbstractDto);
        String jsonString = roleService.roleList(dto);
        return jsonString;
    }

    public String listAllTree(BaseAbstractDto baseAbstractDto) {
        RoleDto dto = super.getExactlyDto(baseAbstractDto);
        String jsonString = roleService.roleAllList(dto);
        return jsonString;
    }
    /**
     * 给角色菜单授权
     * @param baseAbstractDto
     */

    public void saveAutho(BaseAbstractDto baseAbstractDto) {
        RoleDto dto = super.getExactlyDto(baseAbstractDto);
            roleService.saveAutho(dto);
            dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
    }
    /**
     * 给角色数据权限授权
     * @param baseAbstractDto
     */

    public void saveDataAutho(BaseAbstractDto baseAbstractDto) {
        RoleDto dto = super.getExactlyDto(baseAbstractDto);
        roleService.saveDataAutho(dto);
        //统计类的结果
        ProcedureDto procedureDto=new ProcedureDto();
        procedureDto.setProcedure(new Procedure());
        procedureDto.getProcedure().setFlag("all");
        procedureService.check(procedureDto);
        procedureDto.getProcedure().setType("1");
        procedureService.executeAll(procedureDto);
        dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
    }

    public void saveRoleButtonAutho(BaseAbstractDto baseAbstractDto) {
        RoleDto dto = super.getExactlyDto(baseAbstractDto);
            roleService.saveRoleButtonAutho(dto);
            dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
    }



    public void listTreeTable(BaseAbstractDto baseAbstractDto) {
        RoleDto dto = super.getExactlyDto(baseAbstractDto);
        roleService.listTreeTableNew(dto);

    }

    /**
     * 保存对象，若无ID则新建对象，若有ID则更新对象
     *
     * @param basedto
     */
    public String save(BaseAbstractDto basedto) {
        RoleDto dto = super.getExactlyDto(basedto);
        //roleName 必传
        if (dto.getRole().getRoleName() == null || dto.getRole().getRoleName() == "") {
            return "false";
        }//当传入的roleName为null时
        else {
            //拿到新增的roleName数据库查询，如果存在则新增失败，当使用count
            int count = roleService.isExist(dto);
            if (count > 0) {
                return "exist";
            } else {
                //调用对应的service保存方法
                roleService.save(dto.getRole());
                dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
                return "success";
            }
        }
    }


    /**
     * 删除
     *
     * @param basedto
     */
    public void delete(BaseAbstractDto basedto) {
        RoleDto dto = super.getExactlyDto(basedto);
        String id = dto.getRole().getId();
        String excuteQuery = dto.getRole().getRoleCode();
        //为0说明删除的是子节点
        if ("0".equals(excuteQuery)) {
            //删除一个角色，则删除这个角色所绑定的用户上角色的权限（菜单，按钮，数据）
            //roleService.deleteUserAuthority(id);
            //删除role_code为要删除的角色用户的所有的权限
            //roleService.deleteUserAuthorityByRoleId(id);
            //调用对应的service删除方法
             roleService.delete(id);
            //删除t_role_authority表
            roleService.deleteRoleAuthority(id);
            dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
        } else {
             String[] arrays= getDeleteIds(dto);
             //批量更新多个角色的所包含的用户的权限信息
            /*int length=arrays.length;
            for (int index=0;index<length;index++) {
                String roleId= arrays[index];
               // roleService.deleteUserAuthority(roleId);
                //删除role_code为要删除的角色用户的所有的权限
                roleService.deleteUserAuthorityByRoleId(roleId);
            }*/

           // 删除角色表信息
            roleService.deleteArray(arrays);
            //删除角色权限表的信息
            roleService.deleteRoleAuthorityArrays(arrays);
            dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
        }

    }

    private String[] getDeleteIds(RoleDto dto){
       List<Role> roleList=( List<Role>)roleService.listTreeTable(dto).getPageData();
       List<String> stringList=new ArrayList<>();
       for (Role role:roleList){
           stringList.add(role.getId());
       }
        //System.out.println("list的值为");
       // System.out.println(JSON.toJSONString(roleList));
       String[] ids=new String[roleList.size()];
        stringList.toArray(ids);
        return ids;
    }

    /**
     * 根据ID查询实体的方法
     *
     * @param: basedto
     */
    public void findById(BaseAbstractDto basedto) {
        RoleDto dto = super.getExactlyDto(basedto);

        //调用对应的service查询某个实体的方法
        Role entity = roleService.findOne(dto.getRole().getId());
        //不要删除这行代码，调用set是为以后 service层要加缓存
        dto.setRole(entity);
    }

}
