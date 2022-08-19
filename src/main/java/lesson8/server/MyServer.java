package lesson8.server;




import lesson8.server.handlers.ClientHandler;
import lesson8.server.services.AuthenticationService;
import lesson8.server.services.impl.SimpleAuthenticationServiceImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyServer {

    private final ServerSocket serverSocket;
    private final AuthenticationService authenticationService;
    private final ArrayList<ClientHandler> clients;

    public MyServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        authenticationService = new SimpleAuthenticationServiceImpl();
        clients = new ArrayList<>();
    }


    public void start() {
        System.out.println("СЕРВЕР ЗАПУЩЕН!");
        System.out.println("---------------");

        try {
            while (true) {
                waitAndProcessNewClientConnection();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void waitAndProcessNewClientConnection() throws IOException {
        System.out.println("Ожидание клиента...");
        Socket socket = serverSocket.accept();
        System.out.println("Клиент подключился!");
        processClientConnection(socket);
    }

    private void processClientConnection(Socket socket) throws IOException {
        ClientHandler handler = new ClientHandler(this, socket);
        handler.handle();
    }

    public synchronized void subscribe(ClientHandler handler) {
        clients.add(handler);
    }

    public synchronized void unSubscribe(ClientHandler handler) {
        clients.remove(handler);
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public boolean isUsernameBusy(String username) {
        for (ClientHandler client : clients) {
            if (client.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void stop() {
        System.out.println("------------------");
        System.out.println("------------------");
        System.out.println("ЗАВЕРШЕНИЕ РАБОТЫ");
        System.exit(0);
    }

    public void updateUserListInChat(String typeMessage, String clientAuth) throws IOException {
        String userList = typeMessage;
        for (ClientHandler client : clients) {
            userList = userList + " " + client.getUsername();
        }
        sendUserListInChat(userList);
    }
    public synchronized void sendUserListInChat(String message) throws IOException {
        for (ClientHandler client : clients) {
            client.sendServerToClientMessage(message);
        }

    }
    public synchronized void broadcastMessage(ClientHandler sender, String typeMessage, String message) throws IOException {
        for (ClientHandler client : clients) {
            if (client == sender) {
//                client.sendEchoMessage(sender.getUsername(), message);
                continue;
            } else {
                client.sendMessage(sender.getUsername(), typeMessage, message);
            }
        }
    }

    public synchronized void sendingPrivateMessage(ClientHandler sender, String message) throws IOException {
        String[] parts = message.split("\\s");
        String recipient = parts[1].trim();
        String privateMessage;

        parts[0] = "";
        parts[1] = "";
        privateMessage = String.join(" ", parts).trim();
        for (ClientHandler client : clients) {
            if (client.getUsername().equals(recipient)) {
                client.sendPrivateMessage(sender.getUsername(), privateMessage);
                return;
            }
        }
//            sender.sendEchoMessage(sender.getUsername(),message);
    }

}