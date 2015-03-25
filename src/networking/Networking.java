package networking;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.ArrayList;
import java.util.List;

import networking.codec.login.LoginDecoder;
import engine.interfaces.Bundle;

/**
 * Created with eclipse 11/03/2015 10:30:29 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class Networking implements Bundle<Networking> {

	private final List<EventLoopGroup> cachedGroups = new ArrayList<>();
	private ChannelFuture mainNetworkingBootstrap;
	
	private final int port;
	
	public Networking(final int port) {
		this.port = port;
	}
	
	@Override
	public Networking onCreate() {
		final EventLoopGroup bossGroup = new NioEventLoopGroup();
		final EventLoopGroup workerGroup = new NioEventLoopGroup();
		cachedGroups.add(bossGroup);
		cachedGroups.add(workerGroup);
		try {
			final ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel channel) throws Exception {
					channel.pipeline().addLast("login", new LoginDecoder());
				}
			}).childOption(ChannelOption.SO_KEEPALIVE, true);
			mainNetworkingBootstrap = bootstrap.bind(port).sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Initialized the network on the port " + port + ".");
		return this;
	}

	@Override
	public Networking onStop() {
		try {
			mainNetworkingBootstrap.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			for (EventLoopGroup group : cachedGroups) {
				group.shutdownGracefully();
			}
		}
		System.out.println("Stopped the network of the port " + port + ".");
		return this;
	}

}
