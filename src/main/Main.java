package main;

import components.MainScreen;
import components.ScrollPanel;
import sockets.PeerClient;
import sockets.ServerInfo;
import sockets.ServerListener;

public class Main {
    public static void main(String[] args) {

        ServerListener listener = new ServerListener();
        String address = listener.discoverServer();

        if(address != null) {
            String[] parts = address.split(":");
            System.out.println(listener);
            String ip = parts[0];
            System.out.println(ip);
            int port = Integer.parseInt(parts[1]);
            System.out.println(port);
            ServerInfo.set(ip, port);
            PeerClient client = new PeerClient(ip,port);
            client.start();
        }

        MainScreen mainScreen = new MainScreen();
    }
}