import java.net.*;
import java.io.*;

public class MultiThreadServer extends Thread{

    Socket socket = null;
    String inputLine, outputLine;

    MultiThreadServer(Socket socket) {
        //super("MultiThreadServer");
        this.socket = socket;
    }

    public void run() {

        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            KnockKnockProtocol kkp = new KnockKnockProtocol();
            outputLine = kkp.processInput(null);
            out.println(outputLine);

            while((inputLine = in.readLine()) != null){
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);

                if(outputLine.equalsIgnoreCase("Bye")){
                    break;
                }
            }
            socket.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}