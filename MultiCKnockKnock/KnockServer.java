import java.net.*;
import java.io.*;

public class KnockServer {
    public static void main(String[] args) throws IOException{

        if(args.length != 1) {
            System.err.print("Usage: KnockServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        boolean listening = true;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {

            while(listening) {
                new MultiThreadServer(serverSocket.accept()).start();
            }
            
        } catch (IOException e) {
            System.err.println("Could not make IO communication with port " + portNumber);
            System.exit(-1);
        }
        
    }
}
