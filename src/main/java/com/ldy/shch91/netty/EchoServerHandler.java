package com.ldy.shch91.netty;
 
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {


	private static final Logger logger = LoggerFactory.getLogger(EchoServerHandler.class);

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		//ChannelHandlerContext提供各种不同的操作用于触发不同的I/O时间和操作
		//调用write方法来逐字返回接收到的信息
		//这里我们不需要在DISCARD例子当中那样调用释放，因为Netty会在写的时候自动释放
		//只调用write是不会释放的，它会缓存，直到调用flush

		logger.info("Server Receive"+msg);
		ctx.write(msg);
		ctx.flush();
		//你可以直接使用writeAndFlush(msg)
		//ctx.writeAndFlush(msg);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}

