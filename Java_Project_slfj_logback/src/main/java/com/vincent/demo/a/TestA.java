package com.vincent.demo.a;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestA {
	private static Logger log = LoggerFactory.getLogger(TestA.class);

	public TestA() {
		log.debug("TestA-debug");
		log.info("TestA-info");
		log.warn("TestA-warn");
		log.error("TestA-error");
	}
}
