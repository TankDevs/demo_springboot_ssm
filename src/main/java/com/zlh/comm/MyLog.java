package com.zlh.comm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class MyLog {
	// 单纯使用log4j
	// private static Logger logger=Logger.getLogger(Login.class);

	// 使用slf4j
	// 如果是slf4j+log4j，只要在log4j的基础上，添加slf4j-log4j12-1.7.25.jar
	// 如果是slf4j+logback，只要将logback.xml放在src目录下，并添加对应的logback的jar包
	private static Logger logger = LoggerFactory.getLogger(MyLog.class);

	public String logTest() {
		System.out.println("System.out.println-loginIndex");
		// logger.fatal("logger.fatal-loginIndex");//--log4j 特有
		logger.error("logger.error-loginIndex");
		logger.warn("logger.warn-loginIndex");
		logger.info("logger.info-loginIndex");
		logger.debug("logger.debug-loginIndex");
		logger.trace("logger.trace-loginIndex");
		return "loginIndex";
	}


}

