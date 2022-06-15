package org.white.amber.network;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @Description
 * @Date 2022/6/9
 * @Author yuze
 */
public class ClientDemo {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 9999);

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        String str = "hello socket!";

        bufferedWriter.write(str);
        bufferedWriter.flush();






    }
}
