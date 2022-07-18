//Домашнее задание,уровень 2, урок 6: Владимир Греков
package lesson6;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class EchoServer {
    private static final int SERVER_PORT = 8189;
    private static DataInputStream  in;
    private static DataOutputStream  out;
    private static Socket socket;




    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                if (!reader.ready()) {
                    try {
                        String str = in.readUTF();
                        System.out.println("Клиент: " + str);
                        if (str.equalsIgnoreCase("/end")) {
                            break;
                        }
                        str = "Эхо: " + str;
                        out.writeUTF(str);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                if (reader.ready()) {
                    try {
                        String s = reader.readLine();
                        if (!s.isEmpty()) {
                            s = "Эхо-сервер: " + s;
                            out.writeUTF(s);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Соединение сброшено");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
