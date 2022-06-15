package org.white.amber.network;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description
 * @Date 2022/6/9
 * @Author yuze
 */
public class ServerClientDemo {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9999);

        Socket socket = serverSocket.accept();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String readLine = null;

        StringBuffer sb = new StringBuffer();
        do {
            readLine = bufferedReader.readLine();
            sb.append(readLine);
        } while (StringUtils.isNotBlank(readLine));

        System.out.println(sb.toString());


    }

}
