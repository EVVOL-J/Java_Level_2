package Lesson6.server;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final int SERVER_PORT=8189;
    public static void main(String[] args) {
        try(ServerSocket serverSocket=new ServerSocket(SERVER_PORT)){

            //serverSocket.setSoTimeout(10_000);
            System.out.println("Waiting for new connection...");
            Socket clientSocket=serverSocket.accept();
            System.out.println("Client has been connected");

            DataInputStream inData=new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outData=new DataOutputStream(clientSocket.getOutputStream());

            while (true){
                String message=inData.readUTF();
                System.out.println("Client message: " + message);
                if(message.equals("/end"))
                {
                    break;
                }
                outData.writeUTF("Echo: "+ message);

            }

            System.out.println("Server has been closed");

        } catch (IOException e) {
            System.err.println("Server port is already opened");
            e.printStackTrace();
        }
    }
}
