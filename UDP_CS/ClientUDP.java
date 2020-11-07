import java.net.*;
import java.io.*;

public class ClientUDP {
    public static void main(String[] args) throws IOException{

        if(args.length !=1 ){
            System.err.println("Usage: java ClientUDP <host name>");
            System.exit(1);
        }

        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress address = InetAddress.getByName(args[0]);

        byte[] buf = new byte[64];
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
        clientSocket.send(packet);

        packet = new DatagramPacket(buf, buf.length);
        clientSocket.receive(packet);
        String received = new String(packet.getData(),0,packet.getLength());
        System.out.println("Recieved from server : " + received);

        clientSocket.close();
        
    }

}