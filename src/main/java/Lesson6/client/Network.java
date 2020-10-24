package Lesson6.client;

import Lesson6.client.controller.ControllerChatMain;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;



public class Network {
    public static final String SEVER_ADRESS = "localhost";
    private static final int SERVER_PORT=8189;
    private final String host;
    private final int port;
    private DataOutputStream outData;
    private DataInputStream inData;

    public Network(){
        this(SEVER_ADRESS,SERVER_PORT);
    }
    public Network(String host, int port) {
        this.host = host;
        this.port = port;
    }
    public boolean connect()  {
        try {
            Socket clientSocket=new Socket(SEVER_ADRESS,SERVER_PORT);
            outData = new DataOutputStream(clientSocket.getOutputStream());
            inData = new DataInputStream(clientSocket.getInputStream());
            return true;
        } catch (IOException e) {
            System.out.println("Соединение небыло установлено");
            e.printStackTrace();
            return false;
        }
    }

    public DataOutputStream getOutData() {
        return outData;
    }

    public DataInputStream getInData() {
        return inData;
    }

    public void waitMessage(ControllerChatMain controllerChatMain)  {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String message = inData.readUTF();
                        controllerChatMain.appendMessage("Server: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Соединение было потеряно");
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

    }
}
