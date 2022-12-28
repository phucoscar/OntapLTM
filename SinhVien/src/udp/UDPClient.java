package udp;

import model.SinhVien;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    DatagramSocket datagramSocket;

    ByteArrayInputStream bin;
    ByteArrayOutputStream bout;
    ObjectInputStream obin;
    ObjectOutputStream obout;

    private int port = 9853;

    public UDPClient() {
        try {
            datagramSocket = new DatagramSocket();

            // send to server
            SinhVien sinhVien = new SinhVien("B19DCCN506", "Vu Kim Phuc", 9852, "rmi://192.168.1.129:9852/LTM");
            byte[] sendData = toByteArray(sinhVien);
            InetAddress ip = InetAddress.getByName("192.168.1.129");
            int port = 9853;
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ip, port);
            datagramSocket.send(sendPacket);
            System.out.println("Đã gửi!");

            // receive from server
            byte[] receiveData = new byte[100];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            datagramSocket.receive(receivePacket);
            String response = new String(receivePacket.getData()).trim();
            System.out.println(response);
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
        new UDPClient();
    }
}
