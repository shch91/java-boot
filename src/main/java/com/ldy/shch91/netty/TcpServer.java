package com.ldy.shch91.netty;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 */
public class TcpServer
{
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(9999);//建立服务端的socket服务
            Socket s = ss.accept();//获取客户端对象
            String ip = s.getInetAddress().getHostAddress();
            int port = s.getPort();
            System.out.println(ip + " : " + port + " connected");

            // 可以通过获取到的socket对象中的socket流和具体的客户端进行通讯。
            InputStream ins = s.getInputStream();//读取客户端的数据，使用客户端对象的socket读取流
            byte[] bytes = new byte[1024];
            int len = ins.read(bytes);
            String text = new String(bytes, 0, len);
            System.out.println(text);
            //关闭资源
            s.close();
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}