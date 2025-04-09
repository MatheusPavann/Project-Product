package sockets;

import components.ScrollPanel;

import java.io.*;
import java.net.Socket;

public class PeerClient extends Thread {
    private String serverIp;
    private int serverPort;

    public PeerClient(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(serverIp, serverPort);
            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
            dataOut.writeUTF("listen");
            dataOut.flush();

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            while (true) {
                String message = (String) in.readObject();
                if ("refresh".equals(message)) {
                    ScrollPanel.refresh();
                }
            }
        } catch (Exception e) {
            System.out.println("Conexão com servidor perdida.");
            e.printStackTrace();
        }
    }
}
