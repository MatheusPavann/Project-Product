package main;

import components.MainScreen;
import entities.Product;
import sockets.PeerClient;
import sockets.PeerServer;
import sockets.ProductMessage;


public class Main {
    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();

        ProductMessage message = new ProductMessage(ProductMessage.ActionType.CREATE, "123", "Produto Teste");
        PeerClient.send("192.168.0.101", 12345, message);
    }
}
