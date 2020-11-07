import java.net.*;
import java.io.*;

public class KnockKnockServer {
    public static void main(String[] args) throws IOException{
        if (args.length != 1){
            System.err.println("Usage:java KnockKnockServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        String inputLine;

        try (
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket kkClient = serverSocket.accept();

            PrintWriter out = new PrintWriter(kkClient.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(kkClient.getInputStream()));
        ) {
            //Initialising protocol connection with client
            KnockKnockProtocol kkp = new KnockKnockProtocol();
            String outputLine = kkp.processInput(null);
            out.println(outputLine);
            
            while((inputLine = in.readLine()) != null) {
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);

                if(outputLine.equalsIgnoreCase("Bye")) {
                    break;
                }

            }
            
        } catch (IOException e) {
            System.out.println("Unable to listen on port " + portNumber);
            System.err.println(e.getMessage());
        }

    }
    
}