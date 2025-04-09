package sockets;

public class ServerInfo {
    private static String ip;
    private static int port;

    public static void set(String ipAddress, int serverPort) {
        ip = ipAddress;
        port = serverPort;
    }

    public static String getIp() {
        return ip;
    }

    public static int getPort() {
        return port;
    }

    public static boolean isConfigured() {
        return ip != null && port != 0;
    }
}
