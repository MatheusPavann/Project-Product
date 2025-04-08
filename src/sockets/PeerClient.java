package sockets;

import java.io.*;
import java.net.*;

public class PeerClient {
    public static void send(String peerIp, int peerPort) {
        try (Socket socket = new Socket(peerIp, peerPort);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
            out.writeObject("refresh");
        } catch (IOException e) {
            System.out.println("Erro ao enviar para " + peerIp);
        }
    }
}
