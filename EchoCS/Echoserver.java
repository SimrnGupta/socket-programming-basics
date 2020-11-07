import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Echoserver {
    public static void main(final String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        if (args.length != 1){
            System.err.println("Usage: java Echoserver <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputline;
            String input_cmmd;
            while ((inputline = in.readLine()) != null) {
                System.out.println("Sent msg : " + inputline);
                out.println(inputline);
                if(inputline.equalsIgnoreCase("close")){
                    clientSocket.close();
                    System.out.println("Client closed connection\nClose connection?");
                    input_cmmd = sc.nextLine();
                    if(input_cmmd.equalsIgnoreCase("yes")){
                        System.exit(1);
                    }
                    
                    
                }
            }
            serverSocket.close();


        } catch (IOException e) {
            System.out.println("Exception caught when listening to port " + portNumber);
            System.out.println(e.getMessage());
        }
    }

}