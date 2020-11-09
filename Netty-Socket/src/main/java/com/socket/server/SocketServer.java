package com.socket.server;

import java.nio.charset.Charset;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringEncoder;

public class SocketServer {
	private final int port;

	public SocketServer(int port) {
		this.port = port;
	}

	public void start() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup group = new NioEventLoopGroup();
		SocketServerHandler handler = new SocketServerHandler();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
			bootstrap.group(group, bossGroup) // 绑定线程池
					.channel(NioServerSocketChannel.class) // 指定使用的channel
					.localAddress(this.port)// 绑定监听端口
					.childHandler(new ChannelInitializer<SocketChannel>() { // 绑定客户端连接时候触发操作
						@Override
						protected void initChannel(SocketChannel channel) throws Exception {
							System.out.println("报告");
							System.out.println("信息：有一客户端链接到本服务端");
							System.out.println("IP:" + channel.localAddress().getHostName());
							System.out.println("Port:" + channel.localAddress().getPort());
							System.out.println("报告完毕");

							channel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
							channel.pipeline().addLast(handler); // 客户端触发操作
							channel.pipeline().addLast(new ByteArrayEncoder());
						}
					});
			ChannelFuture cf = bootstrap.bind().sync(); // 服务器异步创建绑定
			System.out.println(SocketServer.class + " 启动正在监听： " + cf.channel().localAddress());
			cf.channel().closeFuture().sync(); // 关闭服务器通道
		} finally {
			group.shutdownGracefully().sync(); // 释放线程池资源
			bossGroup.shutdownGracefully().sync();
		}
	}

	public static void main(String[] args) throws Exception {
		new SocketServer(8888).start(); // 启动
	}
}