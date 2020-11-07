import java.net.*;
import java.io.*;

public class KnockKnockClient {
    public static void main(String[] args) throws IOException{
        if(args.length != 2){
            System.err.println("Usage : KnockKnockClient <host name> <port number> ");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[1]);
        String hostName = args[0];
        String fromServer;
        String fromClient;

        try (
            Socket kkSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
        ) {

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            while((fromServer = in.readLine()) != null){
                System.out.println("Server : " + fromServer);
                if(fromServer.equalsIgnoreCase("Bye")){
                    break;
                }
                else {
                    fromClient = stdIn.readLine();

                    out.println(fromClient);
                    System.out.println("Client : " + fromClient);;
                }
            }
            
        } catch (UnknownHostException e) {
            System.err.println("Connection to host " + hostName + " on port " + portNumber + " not estabilished.\nHost not found.");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Unable to form I/O connection on port " + portNumber);
            System.err.println(e.getMessage());
        }

    }
}