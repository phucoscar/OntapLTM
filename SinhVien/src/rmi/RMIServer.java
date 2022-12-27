package rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            Service service = new ServiceImpl();

            LocateRegistry.createRegistry(9852);
            Naming.bind("rmi://192.168.1.129:9852/LTM", service);
            System.out.println("Server RMI is running!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
