package com.dhcc.piccbid.utils;

import org.springframework.stereotype.Component;

import com.dhcc.framework.common.SpringContextHolder;
import com.dhcc.piccbid.common.GlobalProperties;

@Component
public class HttpClientUtil {
	GlobalProperties globalProperties = (GlobalProperties)SpringContextHolder.getBean("GlobalProperties");
}
