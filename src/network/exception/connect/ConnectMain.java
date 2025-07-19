package network.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectMain {
    public static void main(String[] args) throws IOException {
        unknownHostEx1();
        unknownHostEx2();
        connectionRefused();
    }


    /**
     * 이런 IP는 존재하지 않는다.
     * @throws IOException
     */
    private static void unknownHostEx1() throws IOException {
        try {
            Socket socket = new Socket("999.999.999.999", 80);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * 도메인 이름이 잘못되었을때
     * @throws IOException
     */
    private static void unknownHostEx2() throws IOException {
        try {
            Socket socket = new Socket("google.gogo", 80);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * 메시지 연결이 거절 -> 서버 컴퓨터는 접속했으나, 서버컴퓨터에 해당 포트를 사용하지 않아서 TCP 연결 거절
     * @throws IOException
     */
    private static void connectionRefused() throws IOException {
        try{
            Socket socket = new Socket("localhost", 45678);
        }
        catch(ConnectException e){
            e.printStackTrace();

        }
    }


}
