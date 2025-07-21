package network.exception.close;

import java.io.*;
import java.net.Socket;

import static util.MyLogger.log;

public class NormalCloseClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        log("소캣 연결 :"+socket );

        InputStream input = socket.getInputStream();
        readByInputStream(input,socket);
//        readByBufferedReader(input,socket);
//        readByDataInputStream(input,socket);

        log("연결 종료 : "+socket.isClosed());
    }

    private static void readByDataInputStream(InputStream input, Socket socket) throws IOException {
        DataInputStream dis = new DataInputStream(input);
        //DataInputStream에서는 문제가 생기면 exception으로 받는다.
        try{
            dis.readUTF();
        }
        catch(EOFException e){
            log(e);
        }
        finally{
            dis.close();
            socket.close();
        }
    }

    private static void readByBufferedReader(InputStream input, Socket socket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String readString = br.readLine();
        log("readString = "+readString);

        if(readString == null){
            br.close();
            socket.close();
        }
    }

    private static void readByInputStream(InputStream input, Socket socket) throws IOException {
        //상대방이 연결을 끊을때 input.read()는 -1(End Of File)이 된다.
        int read = input.read();
        log("read = "+read);
        if(read==-1){
            input.close();
            socket.close();
        }
    }
}
