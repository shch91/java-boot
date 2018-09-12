package com.ldy.shch91.netty;

import com.alibaba.fastjson.JSON;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class TimeClient {

    private static final Logger logger = LoggerFactory.getLogger(NIOServer.class);

    @PostConstruct
    public  void start() throws InterruptedException {
        new TimeClient().connect(8086,"127.0.0.1");
    }

    public void connect(int port,String host) throws InterruptedException {
        //配置客户端nio线程组
        EventLoopGroup group=new NioEventLoopGroup();
        try{
            Bootstrap b=new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("\n".getBytes())));
                            channel.pipeline().addLast(new StringDecoder());
                            channel.pipeline().addLast(new ChannelHandlerAdapter(){

                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    logger.info("enter");
                                    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                                    String line=br.readLine();
                                    logger.info("enter1:"+line);
                                    while(!"EOF".equals(line)){
                                        byte[] bytes=(line+System.getProperty("line.separator")).getBytes();
                                        ByteBuf buf=Unpooled.buffer(bytes.length);
                                        buf.writeBytes(bytes);
                                        ctx.writeAndFlush(buf);
                                        line=br.readLine();
                                        logger.info("enter1:"+line);
                                    }
//                                    byte[] bytes=("QUERY TIME ORDER"+System.getProperty("line.separator")).getBytes();
//                                    ByteBuf buf=Unpooled.buffer(bytes.length);
//                                    buf.writeBytes(bytes);
//                                    ctx.writeAndFlush(buf);
                                }


                                public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception {
                                    logger.info(JSON.toJSONString(msg));
                                }
                            });
                        }
                    });

            //发起异步连接操作
            ChannelFuture f=b.connect(host,port).sync();

            //等待客户端链路关闭
            f.channel().closeFuture().sync();
        }finally {
            //释放nio线程组
            group.shutdownGracefully();
        }
    }
}