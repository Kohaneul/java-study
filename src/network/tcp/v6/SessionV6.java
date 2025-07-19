package network.tcp.v6;

import network.tcp.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class SessionV6 implements Runnable{
    private final Socket socket;

    private final DataInputStream input;
    private final DataOutputStream output;
    private final SessionManagerV6 sessionManager;
    private boolean closed = false;


    public SessionV6(Socket socket, SessionManagerV6 sessionManager) throws IOException {
        this.socket = socket;
        this.input= new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;

        //세션 매니저에 등록
        this.sessionManager.add(this);

    }

    @Override
    public void run() {
        try{
            String read = input.readUTF();  //Thread-0
            String sendMsg = read + " world";
            output.writeUTF(sendMsg);

            while(true){
                String received = input.readUTF();
                log("client -> server: "+received);
                if(received.equals("exit")){
                    break;
                }
                String toSend = received+" World!";
                output.writeUTF(toSend);
                log("client <- server: "+toSend);
            }

        } catch (IOException e) {
            log(e);
        }
        finally {
            //세션매니저에서 나를 뺀다
            sessionManager.remove(this);
            //나의 자원을 스스로 정리
            close();
        }

    }

    //자원정리
    //세션 종료, 서버 종료 시 동시에 호출될 수 있다.
    public synchronized void close() {
        if(closed){
            return;
        }
        closeAll(socket,input,output);
        //flag
        closed = true;
        log("연결 종료 : "+socket + "isClosed : "+socket.isClosed());

    }
}
