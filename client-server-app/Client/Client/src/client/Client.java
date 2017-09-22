package client;

import java.net.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import java.awt.EventQueue;
import java.util.ArrayList;

public class Client {

    public static ArrayList<cPostInformation> alPosts;
    public static ArrayList<cPostInformation> prevPosts;
    public static short nPostsCount;

    private static int serverPort = 8080;

    private static Socket socket;
    private static BufferedReader in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException,
            InterruptedException {
        // TODO code application logic here

        alPosts = new ArrayList<cPostInformation>();
        
        prevPosts = new ArrayList<cPostInformation>();

        nPostsCount = 0;

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });

        InetAddress addr = InetAddress.getByName(null);

        try {
            socket = new Socket(addr, serverPort);
        } catch (IOException e) {
            System.err.println("Socket failed");
            // Если создание сокета провалилось,
            // ничего ненужно чистить.
        }
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Включаем автоматическое выталкивание:
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream())), true);

        } catch (IOException e) {
            // Сокет должен быть закрыт при любой
            // ошибке, кроме ошибки конструктора сокета:
            try {
                socket.close();
            } catch (IOException e2) {
                System.err.println("Socket not closed");
            }
        }

//			char cConnectionNumber = ' ';
//			
//			// client IP
//                        
//			FileInputStream fis = null;
//			try {
//				fis = new FileInputStream("/home/vm/Documents/DevelopmentWorkspaces/"
//                                        + "NetBeansWorkspace/Client/Client/src/Connections.txt");
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			
//			try {
//				cConnectionNumber = (char) fis.read();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			String address = "127.0.0." + cConnectionNumber;
//
//			// Here, the address of the order of the computer where it will be
//			// executed and the client.
//			try {
//				// create an object that reflects the above IP-address.
//				InetAddress ipAddress = InetAddress.getByName(address);
//				System.out
//						.println("Any of you heard of a socket with IP address "
//								+ address + " and port " + serverPort + "?");
//				// creates a socket using the IP-address and port of the server.
//				Socket socket = new Socket(ipAddress, serverPort);
//				System.out.println("Yes! I just got hold of the program.");
//
//				// input / otput threads
//				InputStream sin = socket.getInputStream();
//				OutputStream sout = socket.getOutputStream();
//
//				// conver threads to easily read messages
//				DataInputStream in = new DataInputStream(sin);
//				DataOutputStream out = new DataOutputStream(sout);
//
//				// Thread for reading data from keyboard
//				BufferedReader keyboard = new BufferedReader(
//						new InputStreamReader(System.in));
//				String line = null;
//				System.out
//						.println("Type in something and press enter. "
//								+ "Will send it to the server and tell ya what it thinks.");
//				System.out.println();
//
//				while (true) {
//					// wait for user tap something and press Enter
//					line = keyboard.readLine();
//					System.out.println("Sending this line to the server...");
//					out.writeUTF(line); // send string to the server
//					out.flush();
//					line = in.readUTF(); // wait for servers string
//					System.out
//							.println("The server was very polite. It sent me this : "
//									+ line);
//					System.out
//							.println("Looks like the server is pleased with us. "
//									+ "Go ahead and enter more lines.");
//					System.out.println();
//				}
//			} catch (Exception x) {
//				x.printStackTrace();
//			}
    }

    public static void sendMessage(String message) {
        out.write(message, 0, message.length());
        //out.print("");
        out.flush();
    }

    public static String getMessage() {

        char c = ' ';

        String str = "";

        try {

            do {
                c = (char) in.read();

                str += c;
            } while (c != '|');

            return str;
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Socket not closed");
            }

        }

        return null;
    }
    
    public static void flushOutStream(){out.flush();}
}