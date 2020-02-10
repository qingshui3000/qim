package server;

import common.entity.Client;
import common.handler.ReadHandler;
import common.kit.BufferKit;
import common.manager.ClientManager;
import common.manager.GroupManager;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;

public class ServerHandler implements java.nio.channels.CompletionHandler<java.nio.channels.AsynchronousSocketChannel,java.nio.channels.AsynchronousServerSocketChannel> {
    /**
     * 客户端连接时将触发该方法
     * @param asc
     * @param server
     */
    @Override
    public void completed(AsynchronousSocketChannel asc, AsynchronousServerSocketChannel server) {
        //继续接收（实现异步）
        server.accept(server,new ServerHandler());

        //保存客户端
        Client client = ClientManager.put(asc);

        //加入默认群组
        GroupManager.put(GroupManager.DEFAULT_GROUP_ID,client);
        System.out.println("新连接 from："+client);
        //read
        ByteBuffer def = BufferKit.getDef();
        //异步read
        asc.read(def,def,new ReadHandler(asc));
    }

    @Override
    public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {
        System.out.println("连接出错");
    }
}
