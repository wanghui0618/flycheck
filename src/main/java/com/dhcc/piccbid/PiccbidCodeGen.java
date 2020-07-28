package com.dhcc.piccbid;

import com.dhcc.framework.common.codegen.CodeGenUtils;


/**
 * <p>标题: ScadaCodeGen.java</p>
 * <p>业务描述:数据采集系统</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2016</p>
 * @author 寇祥
 * @date 2018年9月10日
 * @version V1.0 
 */
public class PiccbidCodeGen {
	public static void main(String[] args) {		 				 
		CodeGenUtils.createCode("com.dhcc.piccbid", "dataComparison", "DataComparison", "xukeyong");
	}
}
