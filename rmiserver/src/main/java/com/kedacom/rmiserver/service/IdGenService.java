/**  
* Title: IdGenService.java
* Description:  
* Copyright: Copyright (c) 2020 
* Company: www.kedacom.com 
* @author zhang.kai  
* @date 2020年2月15日 上午11:08:21  
* @version 1.0  
*/  
package com.kedacom.rmiserver.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.atomic.AtomicLong;

import com.kedacom.rmiinterface.IIdGen;

/**
 * @author zhang.kai
 *
 */

public class IdGenService extends UnicastRemoteObject implements IIdGen{
	
	
	private static final long serialVersionUID = -3742466067930952734L;
	
	private final AtomicLong idSeed ;
	
	/**
	 * @param port
	 * @throws RemoteException
	 */
	public IdGenService() throws RemoteException {
		super();
		idSeed = new AtomicLong(getInitValue()); 
	}


	@Override
	public long getNextId() throws RemoteException {
		
		return idSeed.getAndIncrement();
	}
	
	/**
	 * 获取ID的初始值
	 * @return
	 */
	private long getInitValue() {
		
		return System.currentTimeMillis();
	}
	
}
