package rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Client
 *
 * @author fengquanwei
 * @create 2018/7/24 10:52
 **/
public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 8888);
        InterfaceA interfaceA = (InterfaceA) registry.lookup("rmi://127.0.0.1:8888/interfaceA");
        System.out.println(interfaceA.methodA("Lask"));
    }
}
