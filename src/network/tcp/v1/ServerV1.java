package network.tcp.v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV1 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        //서버는 클라이언트가 어느 포트로 접근해야하는지 알아야하기 떄문에 특정 포트를 열어두어야 한다.
        //서버 소캣 : 클라이언트 - 서버의 TCP 연결 +접속정보를 OS backlog Queue에 담는것까지 지원
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소캣 시작 - 리스닝 포트 : "+PORT);
        //실제 클라이언트와 서버가 정보를 주고받으려면 Socket 필요
        Socket socket = serverSocket.accept();//포트에 클라이언트가 접속하면 클라이언트 통신 가능해짐

        log("소캣 연결: "+socket);
        DataInputStream input = new DataInputStream(socket.getInputStream());       //클라이언트 메시지를 받음
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());   //클라이언트에 보냄

        String received = input.readUTF();
        log("client -> server: "+received);

        String toSend = received+" World!";
        output.writeUTF(toSend);
        log("client <- server: "+toSend);

        //자원 정리
        log("연결 종료 : "+socket);
        input.close();
        output.close();
        socket.close();
        serverSocket.close();





    }
}
