import java.net.*;
import java.io.*;

public class ServerUDP {
    public static void main(String[] args) throws IOException{

        DatagramSocket serverSocket = new DatagramSocket(4445);
        byte[] msg_bytes = new byte[64];
        String msg = "Hello, this is a datagram";
        msg_bytes = msg.getBytes();

        //packet to be recieved from client
        byte[] buf = new byte[64];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        serverSocket.receive(packet);

        InetAddress address = packet.getAddress();
        int port = packet.getPort();

        //packet now sent with dest add and port
        packet = new DatagramPacket(msg_bytes, msg_bytes.length, address, port);
        serverSocket.send(packet);
           
    }
}