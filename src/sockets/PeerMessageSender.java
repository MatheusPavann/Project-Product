package sockets;

import java.io.DataOutputStream;
import java.net.Socket;

public class PeerMessageSender {
    public static void sendRefresh(String serverIp, int serverPort) {
        try (Socket socket = new Socket(serverIp, serverPort);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
            out.writeUTF("refresh");
            out.flush();
        }
        catch (Exception e) {
            System.out.println("Erro ao enviar mensagem de refresh para o servidor.");
            e.printStackTrace();
        }
    }
}
