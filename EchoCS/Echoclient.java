import java.net.*;


import java.io.*;

public class Echoclient {
    public static void main(String[] args) throws IOException {
        if (args.length != 2 ) {
            System.err.println("Usage: java Echoclient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        
        try {
            Socket echoSocket = new Socket(hostName, portNumber);
            String userInput;
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            while((userInput = stdIn.readLine()) != null){
                out.println(userInput);                  //written into server socket
                System.out.println("echo : "+ in.readLine());   //received from server socket
            }
            
        } catch (UnknownHostException e) {
            System.err.println("Host "+ hostName+ " could not be found!");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Could not get I/O for connection to "+hostName);
            System.exit(1);
        }
    }

}