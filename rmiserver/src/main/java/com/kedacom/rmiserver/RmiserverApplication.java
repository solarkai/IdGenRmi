package com.kedacom.rmiserver;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kedacom.rmiinterface.config.RmiConfigConsts;
import com.kedacom.rmiserver.service.IdGenService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class RmiserverApplication implements  ApplicationRunner{
	
	@Value("${rmi.server.hostname}")
	private String rmiSvrHostname;
	
	public static void main(String[] args) {
		SpringApplication.run(RmiserverApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		//设置rmi服务端的hostname,该值与远程调用对象绑定，用于客户端寻址
		System.setProperty("java.rmi.server.hostname",rmiSvrHostname);
		
		//创建注册中心
		Registry reg = LocateRegistry.createRegistry(RmiConfigConsts.RMI_REGISTRY_PORT);
		log.info("registry:"+reg);
		
		//注册远程对象
		IdGenService idgObj = new IdGenService();
		log.info("remote obj:{}",idgObj);
		Registry registry = LocateRegistry.getRegistry(RmiConfigConsts.RMI_REGISTRY_PORT);
		registry.bind(RmiConfigConsts.IDGEN_RMI_OBJECT_NAME, idgObj);
		
		
		log.info("idgen rmi obj ready!");
	}
	
	

}
