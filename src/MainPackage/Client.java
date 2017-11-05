package MainPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	Socket clientSocket;
	PrintWriter out;
	BufferedReader in;
	static String consoleInput; /***ASK WHY THIS IS STATIC - "Cannot make a static reference to the non-static field consoleInput" error at do while in main method***/
	String serverInput;
	
	public Client(){
		
		try {
			clientSocket = new Socket("localhost", 4000);
		} catch (UnknownHostException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Client socket has been created and connected!");
		
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void incomingMessage(){
		
		try {
			serverInput = in.readLine(); //Waits for servers message then assigns it to a String 'serverInput'
		} catch (IOException e) {
			System.out.println("IO error occured while waiting for servers message..");
			e.printStackTrace();
		}
		System.out.println("\nServer Said: " + serverInput + "\n"); //Print out Server Said: + "servers message..." in the console
		
	}
	
	
	public void reply(){
		
		Scanner userInput = new Scanner(System.in);
		System.out.println("Type client message..\r");
		consoleInput = userInput.nextLine();
		
		out.println(consoleInput);
		
	}
	
	
	public void closeSocket(){
		
		try {
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		Client driver = new Client();
		
		do{
		
		driver.incomingMessage();
		driver.reply();
		}while(!consoleInput.equals("end"));
		
		
		driver.closeSocket();
		
	}

}
