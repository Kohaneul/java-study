package network.tcp.v7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketUtil {

    public static void close(Socket socket, DataInputStream inputStream, DataOutputStream output){
        close(inputStream);

        close(output);

        close(socket);


    }

    private static void close(Socket socket) {
        if(socket != null){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void close(DataOutputStream output) {
        if(output != null){
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void close(DataInputStream input) {
        if(input != null){
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
