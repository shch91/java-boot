package shch91.service.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EchoServer {


    private static final Logger logger = LoggerFactory.getLogger(EchoServer.class);


    public static void main(String[] args) throws Exception {
       EchoServer server= new EchoServer();
       server.run(9090);
    }

    public EchoServer() {

    }

    public void run(int port) throws Exception {
        //NioEventLoopGroup是一个处理I/O操作的多线程事件循环
        //bossGroup作为boss,接收传入连接
        //bossGroup只负责接收客户端的连接，不做复杂操作，为了减少资源占用，取值越小越好
        //Group：群组，Loop：循环，Event：事件，这几个东西联在一起，相比大家也大概明白它的用途了。
        //Netty内部都是通过线程在处理各种数据，EventLoopGroup就是用来管理调度他们的，注册Channel，管理他们的生命周期。
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //workerGroup作为worker，处理boss接收的连接的流量和将接收的连接注册进入这个worker
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //ServerBootstrap负责建立服务端
            //你可以直接使用Channel去建立服务端，但是大多数情况下你无需做这种乏味的事情
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    //指定使用NioServerSocketChannel产生一个Channel用来接收连接
                    .channel(NioServerSocketChannel.class)
                    //ChannelInitializer用于配置一个新的Channel
                    //用于向你的Channel当中添加ChannelInboundHandler的实现
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoServerHandler());
                        }

                        ;
                    })
                    //对Channel进行一些配置
                    //注意以下是socket的标准参数
                    //BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。
                    //Option是为了NioServerSocketChannel设置的，用来接收传入连接的
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //是否启用心跳保活机制。在双方TCP套接字建立连接后（即都进入ESTABLISHED状态）并且在两个小时左右上层没有任何数据传输的情况下，这套机制才会被激活。
                    //childOption是用来给父级ServerChannel之下的Channels设置参数的
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync();

            f.channel().closeFuture().sync();
        } finally {
            //资源优雅释放
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}

