package com.weitw.study.sbt.schedule;

import com.weitw.study.sbt.db.tables.pojos.TMenu;
import com.weitw.study.sbt.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class PushSfDataCenterTask {

	private final static Logger logger = LoggerFactory.getLogger(PushSfDataCenterTask.class);

	@Autowired
	private com.weitw.study.sbt.db.tables.daos.TMenuDao menuDao;

	@Scheduled(cron = "0/5 * * * * ?")
	public void test() {
		System.out.println("test测试-start," + DateUtils.normalDateFormat(new Date()));
		TMenu menu = menuDao.findById(1);
		System.out.println("test测试-end," + DateUtils.normalDateFormat(new Date()) + "," + menu);
	}

	@Scheduled(cron = "0/4 * * * * ?")
	public void test222() {
		System.out.println("test222测试-start," + DateUtils.normalDateFormat(new Date()));
		TMenu menu = menuDao.findById(2);
		System.out.println("test222测试-end," + DateUtils.normalDateFormat(new Date()) + "," + menu);
	}


}
