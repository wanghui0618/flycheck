package com.dhcc.piccbid.service.dictdiag.impl;

import com.alibaba.fastjson.JSON;
import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.dictdiag.DictdiagDao;
import com.dhcc.piccbid.dao.dictdiag.DictdiagJdbcDao;
import com.dhcc.piccbid.dto.dictdiag.DictdiagDto;
import com.dhcc.piccbid.entity.dictdiag.Dictdiag;
import com.dhcc.piccbid.entity.dictdiag.TreeNode;
import com.dhcc.piccbid.service.dictdiag.DictdiagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zjx
 * @date 2019-04-30 10:13:57
 * @version V1.0
 */
@Service("DictdiagService")
public class DictdiagServiceImpl extends AbstractBaseService<Dictdiag, String> implements DictdiagService {

	@Resource
	private DictdiagDao DictdiagDao;
	
	@Resource
	private DictdiagJdbcDao dictdiagJdbcDao;
	
	@Resource
	private CommonService commonService;

	@Autowired
	private JdbcTemplate jdbcTemplate;


	public DictdiagServiceImpl(DictdiagDao DictdiagDao) {
		super(DictdiagDao);
		this.DictdiagDao = DictdiagDao;
	}

	@Override
	public PageModel list(DictdiagDto dto) {
		
		dictdiagJdbcDao.list(dto);
		PageModel pageModel=dto.getPageModel();
		commonService.fillSqlPageModelData(pageModel,Dictdiag.class,"id");
		return dto.getPageModel();
	}

	@Override
	public String ztreeDiag(DictdiagDto dictdiagDto) {
		//根据id去获取以这个id为父节点下的所有的子节点信息
		String id = dictdiagDto.getDictdiag().getId();
		List<Dictdiag> dictdiags=dictdiagList(dictdiagDto);
       /* System.out.println(dictdiags);
        return bulids(dictdiags, id);*/
		return  bulid(parseComplex(dictdiags),id);
	}
	@Override
	//返回数据库查询出来的list
	public List<Dictdiag> dictdiagList(DictdiagDto dictdiagDto){
		dictdiagJdbcDao.ztreeDiag(dictdiagDto);
		PageModel pageModel = dictdiagDto.getPageModel();
		pageModel.setPageSize(1000);
		commonService.fillSqlPageModelData(pageModel, Dictdiag.class, "id");
		List<Dictdiag> dictdiags = (List<Dictdiag>) dictdiagDto.getPageModel().getPageData();
		return dictdiags;
	}
	@Override
	public PageModel listSecondVo(DictdiagDto dto) {
		
		dictdiagJdbcDao.listSecondVo(dto);
		PageModel pageModel=dto.getPageModel();
		commonService.fillSqlPageModelData(pageModel,Dictdiag.class,"id");
		return dto.getPageModel();
	}

	/**
	 * 两层循环实现建树
	 *
	 * @param treeNodes 传入的树节点列表
	 * @return
	 */
	public String bulid(List<TreeNode> treeNodes, String id) {

		List<TreeNode> trees = new ArrayList<TreeNode>();

		for (TreeNode treeNode : treeNodes) {
			if (id.equals(treeNode.getId())) {
				trees.add(treeNode);
			}
			for (TreeNode it : treeNodes) {
				if (treeNode.getId().equals(it.getParentId())) {
					if (treeNode.getChildren() == null) {
						treeNode.setChildren(new ArrayList<TreeNode>());
					}
					treeNode.getChildren().add(it);
				}
			}
		}
		return JSON.toJSONString(trees);
	}
	//组装标准ztree树
	public List<TreeNode> parseComplex(List<Dictdiag> dictdiags) {
		if (dictdiags != null && dictdiags.size() > 0) {
			List<TreeNode> treeNodesList = new ArrayList<>();
			//将Dictdiag的数据放到TreeNode里面
			for (Dictdiag dictdiag : dictdiags) {
				TreeNode treeNode = new TreeNode();
				treeNode.setId(dictdiag.getId());
				treeNode.setParentId(dictdiag.getParentId());
				treeNode.setParentLeaf(dictdiag.getParentLeaf());
				treeNode.setDiagCode(dictdiag.getDiagCode());
				treeNode.setDiagDesc(dictdiag.getDiagDesc());
				treeNode.setDiagName(dictdiag.getDiagName());
				treeNode.setParentIndex(dictdiag.getParentIndex());
				// treeNode.set
				String treeNodename;
				if (dictdiag.getDiagCode() == null) {
					treeNodename = new StringBuffer(dictdiag.getDiagName()).toString();
				} else {
					treeNodename = new StringBuffer(dictdiag.getDiagCode()).append(" ").append(dictdiag.getDiagName()).toString();
				}
				treeNode.setName(treeNodename);
				treeNodesList.add(treeNode);
			}
			return  treeNodesList;
		} else {
			return new ArrayList<>();
		}
	}
	@Override
	public void updateDiag(DictdiagDto dictdiagDto) {
		String id = dictdiagDto.getDictdiag().getId();
		String newName = dictdiagDto.getDictdiag().getDiagName();
		String diagCode=dictdiagDto.getDictdiag().getDiagCode();
		String diagDesc=dictdiagDto.getDictdiag().getDiagDesc();
		//System.out.println("更新的json"+JSON.toJSON( dictdiagDto.getDictdiag()));
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("update  t_piccbid_dict_diag");
		stringBuffer.append(" set  DIAG_NAME='" + newName + "'");
		stringBuffer.append(",DIAG_CODE='"+diagCode+"'");
		stringBuffer.append(",DIAG_DESC='"+diagDesc+"'");
		stringBuffer.append("where id='" + id + "'");
		System.out.println(stringBuffer.toString());
		jdbcTemplate.update(stringBuffer.toString());

	}

}
