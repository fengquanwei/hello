package rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Server
 *
 * @author fengquanwei
 * @create 2018/7/24 10:49
 **/
public class Server {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(8888);
        InterfaceA interfaceA = new ImplementA();
        registry.bind("rmi://127.0.0.1:8888/interfaceA", interfaceA);
    }
}
