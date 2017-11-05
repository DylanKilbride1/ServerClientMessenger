package MainPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	
	ServerSocket serverSideSocket;
	Socket serverSocket;
	PrintWriter out;
	BufferedReader in;
	String clientInput, answer, reply;
	static String serverConsoleInput;
	String[] words = { "hello", "computer", "train", "screen", "book", "phone", "mouse", "speaker", "carpet", "wall", "bike", "printer",
			"animal", "router", "java", "python", "windows", "chair", "floor", "house", "college"};
	

	public Server(){
		
		try {
			serverSideSocket = new ServerSocket(4000); //Creates a server socket bound to port number: 4000
			System.out.println("Server is running correctly! Awaiting a clients connection..");
		} catch (IOException e) { // Catches IOException if an error occurs while opening the socket
			System.out.println("Failure opening the server side socket, IO error..");
			e.printStackTrace();
		}
	
		
		try {
			serverSocket = serverSideSocket.accept(); //Listening for a connection to be made to the socket
		} catch (IOException e) { //Throws an error if there are any problems while awaiting a connection
			System.out.println("IO error while awaiting connection..");
			e.printStackTrace();
		}
		System.out.println("Accepted a connection!");
		
		try {
			out = new PrintWriter(serverSocket.getOutputStream(), true); //Creates a new PrintWriter from the exisiting OutputStream. True - flushes the output buffer
		} catch (IOException e) { //Catches IO Error when creating OutputStream or if there is no socket connected
			
			System.out.println("IO Error. Problem with output stream or no connected socket..");
			e.printStackTrace();
		}
		
		try {
			in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream())); /***ASK ABOUT THIS LINE!!***/
		} catch (IOException e) {//Catches IO Error while creating inputStream.
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*try {
			clientInput = in.readLine(); //Waits for clients message then assigns it to a String 'clientInput'
		} catch (IOException e) {
			System.out.println("IO error occured while waiting for clients message..");
			e.printStackTrace();
		}
		System.out.println("Client Said: " + clientInput); //Print out Client Said: + "clients message..." in the console
		*/
		
		/*Scanner serverInput = new Scanner(System.in);
		System.out.println("\n\nServer reply message: ");
		serverConsoleInput = serverInput.nextLine();
		
		out.println(serverConsoleInput);
		*/
		/*
		try {
			String msg = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*
		try {
		serverSocket.close();
		} catch (IOException e){
			
		}*/
}
	
	public void incomingMessageFromClient(){
		
		try {
			clientInput = in.readLine(); //Waits for clients message then assigns it to a String 'clientInput'
		} catch (IOException e) {
			System.out.println("IO error occured while waiting for clients message..");
			e.printStackTrace();
		}
		System.out.println("\nClient Said: " + clientInput); //Print out Client Said: + "clients message..." in the console
		
	}
	
	public void serverReply(){
		
		Scanner serverInput = new Scanner(System.in);
		System.out.println("\nType server message:\r ");
		serverConsoleInput = serverInput.nextLine();
		
		out.println(serverConsoleInput);
		
	}
	
	public void closeSocket(){
		
		try {
			serverSocket.close();
			} catch (IOException e){
				
			}
		
	}
	
	public static void main(String[] args){
		Server driver = new Server();
		
		
		
		do{
		
		driver.serverReply();
		driver.incomingMessageFromClient();
		} while(!serverConsoleInput.equals("end"));
		
		driver.closeSocket();
		
	}
}
