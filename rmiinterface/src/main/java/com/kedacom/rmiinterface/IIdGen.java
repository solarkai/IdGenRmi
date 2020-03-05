/**  
* Title: IIdGen.java
* Description:  
* Copyright: Copyright (c) 2020 
* Company: www.kedacom.com 
* @author zhang.kai  
* @date 2020年2月15日 上午10:48:50  
* @version 1.0  
*/  
package com.kedacom.rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author zhang.kai
 *
 */
public interface IIdGen extends Remote{
	
	public long getNextId() throws RemoteException;

}
