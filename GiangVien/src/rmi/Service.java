package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
    public int ucln(int a, int b) throws RemoteException;
    public int bcnn(int a, int b) throws RemoteException;
    public int max(int a, int b) throws RemoteException;
    public String reverseString(String inp) throws RemoteException;
}
