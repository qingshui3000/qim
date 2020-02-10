package common.handler;

import common.entity.Client;
import common.kit.BufferKit;
import common.manager.ClientManager;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

public class ReadHandler implements java.nio.channels.CompletionHandler<Integer,java.nio.ByteBuffer> {
    private final AsynchronousSocketChannel asc;
    public ReadHandler(AsynchronousSocketChannel asc) {
        this.asc = asc;
    }

    /**
     * 有数据读取时触发该方法
     * @param result
     * @param buffer
     */
    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        //继续读
        ByteBuffer def = BufferKit.getDef();
        asc.read(def,def,new ReadHandler(asc));
        //获取信息
        Client client = ClientManager.get(asc);
        String name = client == null ? "server" : client.getName();
        //print
        System.out.println(name + ":" + new String(buffer.array(),0,result));
    }

    @Override
    public void failed(Throwable exc, ByteBuffer buffer) {
        System.out.println("读取数据出错");
    }
}
