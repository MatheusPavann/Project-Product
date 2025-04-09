package sockets;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ServerListener {
    private final int multicastPort = 4446;
    private final String multicastAddress = "230.0.0.0";

    public String discoverServer() {
        try (MulticastSocket socket = new MulticastSocket(multicastPort)) {
            InetAddress group = InetAddress.getByName(multicastAddress);
            socket.joinGroup(group);

            byte[] buffer = new byte[256];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.setSoTimeout(7000);

            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength());

            System.out.println("Servidor encontrado: " + received);
            return received;

        } catch (Exception e) {
            System.out.println("Servidor não encontrado na rede.");
            return null;
        }
    }
}
