package network.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectTimeoutMain2 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        try {
            //객체만 생성하면 아직 연결 X
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("192.168.1.25", 45678),3000);

        } catch (ConnectException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("end = "+(end-start));
    }
}
