package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * ImplementA
 *
 * @author fengquanwei
 * @create 2018/7/24 10:49
 **/
public class ImplementA extends UnicastRemoteObject implements InterfaceA {
    protected ImplementA() throws RemoteException {
        super();
    }

    @Override
    public String methodA(String name) throws RemoteException {
        return "Hello, " + name;
    }
}
