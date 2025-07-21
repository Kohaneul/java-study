package network.tcp.v7;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final int PORT = 12345;
    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost",PORT);
            DataInputStream input =new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ) {
            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.print("보내는 메시지 : ");
                String sendMessage = sc.nextLine();
                output.writeUTF(sendMessage);
                System.out.println(sendMessage);

                if(sendMessage.equals("exit")) {
                    break;
                }

                System.out.print("받은 메시지 : ");
                String readMessage = input.readUTF();
                System.out.println(readMessage);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
