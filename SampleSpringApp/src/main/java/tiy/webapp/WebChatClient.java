package tiy.webapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import jodd.json.JsonSerializer;
import jodd.json.meta.JSON;


/**
 * Created by RdDvls on 9/12/16.
 */
public class WebChatClient {
    String inputString;

    public String sendMessageToUser(String messageToSend) {
        try {
            System.out.println("Running client...");
            Socket clientSocket = new Socket("localhost", 8005);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println(messageToSend);
            out.flush();
            inputString = in.readLine();
            System.out.println(inputString);
            clientSocket.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }return inputString;
    }
}



