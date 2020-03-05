package com.kedacom.rmiclient;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kedacom.rmiinterface.IIdGen;
import com.kedacom.rmiinterface.config.RmiConfigConsts;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class RmiclientApplication implements ApplicationRunner {

	@Value("${rmi.server.hostname}")
	private String rmiSvrHostname;

	public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
		SpringApplication.run(RmiclientApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		// 获取注册中心
		Registry registry = LocateRegistry.getRegistry(rmiSvrHostname, RmiConfigConsts.RMI_REGISTRY_PORT);
		log.info("get registry");

		// 在注册中心中查找远程对象
		IIdGen idgen = (IIdGen) registry.lookup(RmiConfigConsts.IDGEN_RMI_OBJECT_NAME);
		log.info("get idgen:{}", idgen);

		// 循环调用该接口
		for (int i = 0; i < 10; i++) {
			log.info("{} remote idgen:{}", i, idgen.getNextId());
		}
	}
}
