package client;

import common.handler.ReadHandler;
import common.kit.BufferKit;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

public class ClientHandler implements java.nio.channels.CompletionHandler<Void,java.nio.channels.AsynchronousSocketChannel> {
    @Override
    public void completed(Void result, AsynchronousSocketChannel asc) {
        //read
        ByteBuffer def = BufferKit.getDef();
        asc.read(def,def,new ReadHandler(asc));
    }

    @Override
    public void failed(Throwable exc, AsynchronousSocketChannel asc) {
        System.out.println("连接出错");
    }
}
