package com.dhcc.piccbid.web.controller.knowledge;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author gzw
 * @date 2019-05-23 18:21:42
 * @version V1.0
 */
@Controller
public class KnowledgeController {
	
	@RequestMapping("/knowledge/knowledge")
	public String knowledge() {
		return "knowledge/knowledge";
	}

	@RequestMapping("/knowledge/knowledgeInfo")
	public String knowledgeInfo() {
		return "knowledge/knowledgeInfo";
	}
	
	@RequestMapping("/knowledge/knowledgeInfoEdit")
	public String knowledgeInfoEdit() {
		return "knowledge/knowledgeInfoEdit";
	}
}
