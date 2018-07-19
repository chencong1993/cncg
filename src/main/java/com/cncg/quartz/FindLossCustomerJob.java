package com.cncg.quartz;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//import com.cncg.service.CustomerService;

/**
 * 定时任务
 * @author Administrator
 *
 */
@Component
public class FindLossCustomerJob {

	//@Resource
	//private CustomerService customerService; // 客户Service
	
	/**
	 * 每天凌晨2点定期执行
	 */
	@Scheduled(cron="0 0 2 * * ?")
	public void work(){
		System.out.println("定时任务");
		//customerService.checkCustomerLoss();
	}
}