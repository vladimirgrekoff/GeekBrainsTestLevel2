//Домашнее задание,уровень 2, урок 7: Владимир Греков
package lesson7.server.handlers;

import lesson7.server.MyServer;
import lesson7.server.services.AuthenticationService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;


public class ClientHandler {
    private static final String AUTH_CMD_PREFIX = "/auth"; // + login + password
    private static final String AUTH_OK_CMD_PREFIX = "/authOk"; // + username
    private static final String AUTH_ERR_CMD_PREFIX = "/authErr"; // + error message
    private static final String CLIENT_MSG_CMD_PREFIX = "/cMsg"; // + msg
    private static final String SERVER_ECHO_MSG_CMD_PREFIX = "/echo"; // + msg

    private static final String SERVER_MSG_CMD_PREFIX = "/sMsg"; // + msg
    private static final String PRIVATE_MSG_CMD_PREFIX = "/pm"; // + username + msg
    private static final String CONNECT_CMD_PREFIX = "/connect"; // + login + password
    private static final String STOP_SERVER_CMD_PREFIX = "/stop";
    private static final String END_CLIENT_CMD_PREFIX = "/end";
    private MyServer myServer;
    private Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private String username;

    public ClientHandler(MyServer myServer, Socket socket) {

        this.myServer = myServer;
        clientSocket = socket;
    }


    public void handle() throws IOException {
        in = new DataInputStream(clientSocket.getInputStream());
        out = new DataOutputStream(clientSocket.getOutputStream());
        sendServerToClientMessage("СВЯЗЬ УСТАНОВЛЕНА");// + "\n");//
//        connectedFirst("/Check");


        new Thread(() -> {
            try {
                authentication();
                readMessage();
            } catch (IOException e) {
                try {
                    throw new SocketException("соединение сброшено");
                } catch (SocketException ex) {
//                    throw new RuntimeException(ex);
                    System.out.println("Клиент сбросил соединение");
                }
                System.out.println("Данные не получены");
//                e.printStackTrace();
            }
        }).start();
    }

    private void authentication() throws IOException {
        while (true) {
            String message = in.readUTF();
            sendEchoMessage(message);
//            out.writeUTF(String.format("%s %s", SERVER_ECHO_MSG_CMD_PREFIX, message));
//            out.flush();

//            if (message.equals("/Ready")) {
//                break;
//            }
            if (message.equals(CONNECT_CMD_PREFIX)) {
                completedConnection(message);
                continue;
            }

            if (message.startsWith(AUTH_CMD_PREFIX)) {
                boolean isSuccessAuth = processAuthentication(message);
                if (isSuccessAuth) {
                    break;
                }
            } else {
                sendServerToClientMessage(AUTH_ERR_CMD_PREFIX + " Неверная команда аутентификации");// + "\n");//
//                out.writeUTF(AUTH_ERR_CMD_PREFIX + " Неверная команда аутентификации");
//                out.flush();
                System.out.println("Неверная команда аутентификации");
            }

        }
    }

    private boolean processAuthentication(String message) throws IOException {
        String[] parts = message.split("\\s+");
        if (parts.length != 3) {
            sendServerToClientMessage(AUTH_ERR_CMD_PREFIX + " Неверная команда аутентификации");// + "\n");
//            out.writeUTF(AUTH_ERR_CMD_PREFIX + " Неверная команда аутентификации");
//            out.flush();
            System.out.println("Неверная команда аутентификации");
            return false;
        }

        String login = parts[1];
        String password = parts[2];

        AuthenticationService auth = myServer.getAuthenticationService();

        username = auth.getUsernameByLoginAndPassword(login, password);

        if (username != null) {
            if (myServer.isUsernameBusy(username)) {
                sendServerToClientMessage(AUTH_ERR_CMD_PREFIX + " Логин уже используется");// + "\n");
//                out.writeUTF(AUTH_ERR_CMD_PREFIX + " Логин уже используется");
//                out.flush();
                return false;
            }

//            out.writeUTF(AUTH_OK_CMD_PREFIX + " " + username + "\n");
//            out.flush();
            sendServerToClientMessage(AUTH_OK_CMD_PREFIX + " " + username);// + "\n");
            myServer.subscribe(this);
            myServer.broadcastMessage(this, SERVER_MSG_CMD_PREFIX, "подключился к чату");// + "\n");////
            System.out.println("Пользователь " + username + " подключился к чату");
            return true;
        } else {
            sendServerToClientMessage(AUTH_ERR_CMD_PREFIX + " Неверная комбинация логина и пароля");// + "\n");
//            out.writeUTF(AUTH_ERR_CMD_PREFIX + " Неверная комбинация логина и пароля");
//            out.flush();
            return false;
        }
    }

    private void readMessage() throws IOException {
        try {
            while (true) {

                String message = in.readUTF();
                System.out.println("message | " + username + ": " + message);
                sendEchoMessage(message);

//            out.writeUTF(String.format("%s %s", SERVER_ECHO_MSG_CMD_PREFIX, message));
//            out.flush();

                String typeMessage = message.split("\\s+")[0];

                switch (typeMessage) {
                    case STOP_SERVER_CMD_PREFIX -> myServer.stop();
                    case END_CLIENT_CMD_PREFIX -> closeConnection();
                    case PRIVATE_MSG_CMD_PREFIX -> myServer.sendingPrivateMessage(this, message);

                    default -> myServer.broadcastMessage(this, CLIENT_MSG_CMD_PREFIX, message);
                }

            }

        } catch (IOException e) {
            System.out.println("Пользователь отключился");
//                throw new RuntimeException(e);
        }
    }

    private void completedConnection(String clientMessage) throws IOException {
//        String message = in.readUTF();
        if (clientMessage.startsWith(CONNECT_CMD_PREFIX)) {
            sendServerToClientMessage("СОЕДИНЕНИЕ ПРОШЛО УСПЕШНО");///
//            out.writeUTF("СОЕДИНЕНИЕ УСТАНОВЛЕНО УСПЕШНО");//pm + "\n");//
//            out.flush();
        }
    }
    private void closeConnection() throws IOException {
        String name = username;
        try {

            myServer.broadcastMessage(this, SERVER_MSG_CMD_PREFIX ,"покинул чат");// + "\n");
            myServer.unSubscribe(this);
            clientSocket.close();
        } catch (SocketException e) {
            System.out.println(name + " отключился");
            System.out.println("Сокет закрыт");
        }
    }

    public void sendServerToClientMessage(String serverMessage) throws IOException {
        out.writeUTF(serverMessage);
        out.flush();
    }
    public void sendMessage(String sender, String typeMessage, String message) throws IOException {
        out.writeUTF(String.format("%s %s %s", typeMessage, sender, message));
        out.flush();
    }
    public void sendPrivateMessage(String sender, String message) throws IOException {
        out.writeUTF(String.format("%s %s", sender, message));
        out.flush();
    }
    public void sendEchoMessage(/*String sender, */String message) throws IOException {
        out.writeUTF(String.format("%s %s", SERVER_ECHO_MSG_CMD_PREFIX, message));
        out.flush();
    }

    public String getUsername() {
        return username;
    }

}
