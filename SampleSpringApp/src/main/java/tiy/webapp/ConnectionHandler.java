package tiy.webapp;

import jdk.nashorn.internal.parser.JSONParser;
import jodd.json.JsonParser;

import java.io.*;
import java.net.Socket;

/**
 * Created by RdDvls on 9/12/16.
 */
public class ConnectionHandler implements Runnable {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Socket getConnection() {
        return connection;
    }

    public void setConnection(Socket connection) {
        this.connection = connection;
    }

    private String message = null;
    Socket connection;

    public void run() {
        try {
            handleIncomingConnection(connection);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public ConnectionHandler(Socket connection) {
        this.connection = connection;
    }


    private void handleIncomingConnection(Socket clientSocket) throws IOException {
        System.out.println("Connection established");
        System.out.println("clientSocket = " + clientSocket);
        System.out.println("Incoming connection from: " + clientSocket.getInetAddress().getHostAddress());
        BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter outputToClient = new PrintWriter(clientSocket.getOutputStream(), true);
        String inputLine;
//        System.out.println(inputFromClient.readLine());
//        outputToClient.println("anything");
        while ((inputLine = inputFromClient.readLine()) != null) {
            System.out.println(inputLine);
            outputToClient.println(inputLine);
        }
    }

}
