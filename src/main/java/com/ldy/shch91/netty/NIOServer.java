package com.ldy.shch91.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

@Component
public class NIOServer {

    private static final Logger logger = LoggerFactory.getLogger(NIOServer.class);

    private int flag =0;

    //读写缓冲区大小
    private int BLOCK=4096;

    //发送缓冲
    private ByteBuffer sendBuffer = ByteBuffer.allocate(BLOCK);

    //接收缓冲
    private ByteBuffer receiveBuffer = ByteBuffer.allocate(BLOCK);

    private Selector selector;

    private int port = 8888;

    public NIOServer()throws IOException {
        ServerSocketChannel serverSocketChannel= ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        ServerSocket serverSocket=serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));

        selector=Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        logger.info("server start --- "+port);
    }

    /**
     * 监听
     * @throws IOException
     */
    private void listen() throws IOException{
        while(true){
            selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> itr=selectionKeys.iterator();
            while(itr.hasNext()){
                SelectionKey selectionKey=itr.next();
                itr.remove();
                handleKey(selectionKey);//处理 selectionKey
            }
        }
    }

    /**
     * 处理 selectionKey,读写
     * @param selectionKey
     * @throws IOException
     */
    private void handleKey(SelectionKey selectionKey)throws IOException{
        SocketChannel client=null;
        if(selectionKey.isAcceptable()){
            ServerSocketChannel server=(ServerSocketChannel)selectionKey.channel();

            client=server.accept();
            client.configureBlocking(false);
            client.register(selector,SelectionKey.OP_READ);
        }else if(selectionKey.isReadable()){
            client=(SocketChannel)selectionKey.channel();

            receiveBuffer.clear();
            int count=client.read(receiveBuffer);
            if(count>0){
                String receiveText=new String(receiveBuffer.array(),0,count);
                logger.info("服务端接收客户端数据 -- :"+receiveText);

                client.register(selector,SelectionKey.OP_WRITE);//注册写操作
            }
        }else if(selectionKey.isWritable()){
            client=(SocketChannel) selectionKey.channel();

            String sendText="message from server--"+flag++;
            sendBuffer.clear();
            sendBuffer.put(sendText.getBytes());
            sendBuffer.flip();
            client.write(sendBuffer);
            logger.info("服务端向客户端发送数据--: "+sendText);

            client.register(selector,SelectionKey.OP_READ); //注册读操作
        }
    }

    @PostConstruct
    public void start() throws IOException{
        NIOServer server=new NIOServer();
        server.listen();
    }
}