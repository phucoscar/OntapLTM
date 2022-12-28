package udp;

import model.SinhVien;
import rmi.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class UDPServer {
    DatagramSocket datagramSocket;

    ByteArrayInputStream bin;
    ByteArrayOutputStream bout;
    ObjectInputStream obin;
    ObjectOutputStream obout;

    private int port = 9853;

    public UDPServer() {
        try {
            datagramSocket = new DatagramSocket(port);

            // receive from client
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            datagramSocket.receive(receivePacket);

            SinhVien sinhVien = (SinhVien) toObject(receivePacket.getData());

            LocateRegistry.getRegistry(sinhVien.getPort());
            Service service = (Service) Naming.lookup(sinhVien.getUrl());
            System.out.println("UCLN cua 10 va 8 la: " + service.ucln(10, 8));
            System.out.println("BCNN cua 10 va 8 la: " + service.bcnn(10, 8));
            System.out.println("So lon nhat giua hai so 10 va 8 la: " + service.max(10, 8));
            System.out.println("Chuoi dao nguoc cua Hello Bro: " + service.reverseString("Hello Bro"));

            // send to client
            String response = "Server Giang Vien đã nhận!";
            byte[] sendData = response.getBytes("utf-8");
            InetAddress ip = receivePacket.getAddress();
            int port = receivePacket.getPort();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ip, port);
            datagramSocket.send(sendPacket);
            System.out.println("Server đã trả lời!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] toByteArray(Object o) {
        byte[] bytes = null;
        try {
            bout = new ByteArrayOutputStream();
            obout = new ObjectOutputStream(bout);

            obout.writeObject(o);
            obout.flush();
            bytes = bout.toByteArray();
            bout.close();
            obout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bytes;
    }

    public Object toObject(byte[] bytes) {
        Object obj = null;
        try {
            bin = new ByteArrayInputStream(bytes);
            obin = new ObjectInputStream(bin);
            obj = obin.readObject();
            bin.close();
            obin.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return obj;
    }
    public static void main(String[] args) {
        new UDPServer();
    }
}
