package com.fengquanwei.hello.javaee.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * InterfaceA
 *
 * @author fengquanwei
 * @create 2018/7/24 10:48
 **/
public interface InterfaceA extends Remote {
    String methodA(String name) throws RemoteException;
}
