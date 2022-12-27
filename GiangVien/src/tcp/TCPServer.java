package tcp;

import model.SinhVien;
import rmi.Service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class TCPServer {
    ServerSocket server = null;
    InetAddress ip = null;
    int port = 12364;

    DataInputStream in;
    DataOutputStream out;
    ObjectOutputStream obout;
    ObjectInputStream obin;

    public TCPServer() {
        try {
            server = new ServerSocket(port);
            while (true) {
                Socket socket = server.accept();

                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());
                obout = new ObjectOutputStream(socket.getOutputStream());
                obin = new ObjectInputStream(socket.getInputStream());

                SinhVien sinhVien = (SinhVien) obin.readObject();

                LocateRegistry.getRegistry(sinhVien.getPort());
                Service service = (Service) Naming.lookup(sinhVien.getUrl());

                System.out.println("UCLN cua 10 va 8 la: " + service.ucln(10, 8));
                System.out.println("BCNN cua 10 va 8 la: " + service.bcnn(10, 8));
                System.out.println("So lon nhat giua hai so 10 va 8 la: " + service.max(10, 8));
                System.out.println("Chuoi dao nguoc cua Hello Bro: " + service.reverseString("Hello Bro"));

                out.writeUTF("Server Giang Vien da nhan thong tin!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TCPServer server = new TCPServer();
    }
}
