package tcp;

import model.SinhVien;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient {
    Socket socket;
    InetAddress ip = null;
    int port = 12364;

    DataInputStream in;
    DataOutputStream out;
    ObjectOutputStream obout;
    ObjectInputStream obin;


    public TCPClient() {
        try {
            ip = InetAddress.getByName("192.168.1.129");
            socket = new Socket(ip, port);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            obout = new ObjectOutputStream(socket.getOutputStream());

            SinhVien sinhVien = new SinhVien("B19DCCN506", "Vu Kim Phuc", 9852, "rmi://192.168.1.129:9852/LTM");
            obout.writeObject(sinhVien);

            String response = in.readUTF();
            System.out.println(response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TCPClient client = new TCPClient();
    }
}
