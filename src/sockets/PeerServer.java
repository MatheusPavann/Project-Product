package sockets;

import sockets.ProductMessage;

import java.io.*;
import java.net.*;

public class PeerServer extends Thread {
    private int port;

    public PeerServer(int port) {
        this.port = port;
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor escutando na porta " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> handleConnection(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleConnection(Socket socket) {
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            ProductMessage message = (ProductMessage) in.readObject();
            System.out.println("Recebido: " + message.getAction() + " - " + message.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
