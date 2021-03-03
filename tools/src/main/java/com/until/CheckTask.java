package com.until;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.impl.CheckImpl;
import com.type.BaseData;

@Configuration
@EnableScheduling
public class CheckTask {

	@Autowired
	BaseData baseData;

	/** 秒 分 时 日 月 周 */
	/** 每一小时执行一次 */
	@Scheduled(cron = "0 0 0/1 * * *")
	public void check() {
		CheckImpl.Log.info("文件比对定时器启动");
		baseData.init();
		CheckImpl check = new CheckImpl();
		check.run(baseData, null);
		CheckImpl.Log.info("文件比对定时器完成");
	}

	/** 每周一凌晨执行一次 */
	@Scheduled(cron = "0 10 0 ? * 1")
	public void file() {
		baseData.init();
		CheckImpl.Log.info("生成检测编号定时器启动");
		FileList.run(baseData, null);
		CheckImpl.Log.info("生成检测编号定时器完成");

	}
}