package network.tcp.v6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV6 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        SessionManagerV6 sessionManager = new SessionManagerV6();
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소캣 시작 - 리스닝 포트 : "+PORT);

        //ShutdownHook 등록
        ShutdownHook shutdownHook = new ShutdownHook(serverSocket,sessionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook,"shutdown"));
        Socket socket = serverSocket.accept();

        try{
            while(true){
                log("소캣 연결 : "+socket);
                Thread thread = new Thread(new SessionV6(socket,sessionManager));
                thread.start();
            }
        }
        catch(IOException e){
            log("서버 소캣 종료 : "+e);
        }

    }

    /**
     * 자바가 종료될때 자동호출
     */
    static class ShutdownHook implements Runnable{
        private final ServerSocket serverSocket;
        private final SessionManagerV6 sessionManager;

        public ShutdownHook(ServerSocket serverSocket, SessionManagerV6 sessionManager) {
            this.serverSocket = serverSocket;
            this.sessionManager = sessionManager;
        }

        @Override
        public void run() {
            log("ShutdownHook 실행" );
            try {
                sessionManager.closeAll();
                serverSocket.close();

                Thread.sleep(1000); //자원 정리 대기
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("e = "+e);
            }
        }
    }
}
