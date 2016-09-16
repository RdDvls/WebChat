package tiy.webapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleSpringAppApplicationTests {
	String messageToSend = "Test message";
	String inputString;
	Socket clientsocket;
	@Test
	public void contextLoads() {
	}


	@Test
	public void testSendMessageToUser() throws IOException{
		ConnectionHandler connHandler = new ConnectionHandler(clientsocket);

		System.out.println(messageToSend);
		Socket clientSocket = new Socket("localhost", 8005);
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		out.println(messageToSend);
		out.flush();
		inputString = in.readLine();
		System.out.println(inputString);

		assertTrue(inputString.equalsIgnoreCase(messageToSend));

	}
}
