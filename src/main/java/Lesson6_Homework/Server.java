package Lesson6_Homework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private Socket socket = null;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner scanner;


    Server() {
        createServer();
    }

    public static void main(String[] args) {
        new Server();

    }

    private void createServer() {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            scanner = new Scanner(System.in);
            startWrite();
            while (true) {
                String str = in.readUTF();
                if (str.equals("/end")) {
                    System.out.println("Клиент отключился");
                    System.out.println("Сервер отключен");
                    break;
                }
                System.out.println("Клиент: " + str);


            }
        } catch (IOException e) {
            System.out.println("Вы отключились");
        }
    }

    private void startWrite() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    while (true) {
                        String message = scanner.nextLine();
                        out.writeUTF(message);
                        if(message.equalsIgnoreCase("/end")) break;
                        System.out.println("Я: " + message);

                    }
                } catch (IOException e) {

                    e.printStackTrace();
                    System.out.println("Ошибка отправки сообщения");
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}


