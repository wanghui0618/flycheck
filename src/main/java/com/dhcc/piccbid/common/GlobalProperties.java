package com.dhcc.piccbid.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component("GlobalProperties")
public class GlobalProperties {
	@Autowired
    private Environment environment;

	public Environment getEnv(){
	       return environment;
	}
}
