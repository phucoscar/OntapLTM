package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceImpl extends UnicastRemoteObject implements Service {

    public ServiceImpl() throws RemoteException {

    }

    @Override
    public int ucln(int a, int b) throws RemoteException {
        if (b == 0)
            return a;
        return ucln(b, a % b);
    }

    @Override
    public int bcnn(int a, int b) throws RemoteException {
        return (a * b) / ucln(a, b);
    }

    @Override
    public int max(int a, int b) throws RemoteException {
        if (a > b) return a;
        else return b;
    }

    @Override
    public String reverseString(String inp) throws RemoteException {
        return new StringBuilder(inp).reverse().toString();
    }
}
