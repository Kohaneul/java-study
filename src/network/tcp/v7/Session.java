package network.tcp.v7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Session implements Runnable{
    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final SessionManager sessionManager;

    private boolean isClosed = false;

    public Session(Socket socket,SessionManager sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager =sessionManager;
        sessionManager.add(this);
    }


    @Override
    public void run() {
        try {
            Scanner sc = new Scanner(System.in);
            while(true){
                String readMessage = input.readUTF();
                System.out.println("받은 메시지 : "+readMessage);
                System.out.print("메시지 입력 : ");
                String sendMessage = sc.nextLine();
                output.writeUTF(sendMessage);
                System.out.println("보낸 메시지 : "+sendMessage);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            sessionManager.remove(this);
            close();
        }
    }


    public void close(){

        if(isClosed){
            return;
        }

        isClosed = true;
        SocketUtil.close(socket, input,output);



    }

}


