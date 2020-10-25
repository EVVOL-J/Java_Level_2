package Lesson6_Homework;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket = null;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner scanner;
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;


    Client() {
        try {
            createClient();
        } catch (IOException e) {
            System.out.println("Ошибка создания клиента");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client();

    }

    private void createClient() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        System.out.println("Успешное подключение к серверу");
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        scanner = new Scanner(System.in);
        startWrite();
        try {
            while (true) {
                String str = in.readUTF();
                if (str.equals("/end")) {
                    System.out.println("Сервер отключился");
                    System.out.println("Клиент отключен");
                    break;
                }
                System.out.println("Сервер: " + str);


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



