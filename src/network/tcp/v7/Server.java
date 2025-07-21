package network.tcp.v7;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 12345;
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket socket = serverSocket.accept();
            SessionManager sessionManager = new SessionManager();
            ShutDownHook shutDownHook = new ShutDownHook(serverSocket,sessionManager);
            Runtime.getRuntime().addShutdownHook(new Thread(shutDownHook));
            while(true){
                Thread thread = new Thread(new Session(socket,sessionManager));
                thread.start();
            }




        } catch (IOException e) {
            System.out.println("서버 소캣 종료");
        }
    }

    static class ShutDownHook implements Runnable{
        private final ServerSocket serverSocket;
        private final SessionManager sessionManager;

        ShutDownHook(ServerSocket serverSocket, SessionManager sessionManager) {
            this.serverSocket = serverSocket;
            this.sessionManager = sessionManager;
        }


        @Override
        public void run() {
            System.out.println("ShutDownHook 실행");
            try {
                sessionManager.clearAll();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("e = "+e);

            }
        }
    }



}
