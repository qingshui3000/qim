package common.kit;

import common.entity.Client;
import common.handler.WriterHandler;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Arrays;
import java.util.Collection;

/**
 * 发送消息的工具类
 */
public class Writer {

    public static void write(ByteBuffer buffer, Collection<Client> clients) {
        clients.forEach(client -> {
            if(client != null && client.getAsc() != null){
                ByteBuffer copy = buffer.duplicate();
                AsynchronousSocketChannel asc = client.getAsc();
                asc.write(copy,copy,new WriterHandler(asc));
            }
        });
    }

    public static void write(ByteBuffer buffer, AsynchronousSocketChannel... clients) {
        Arrays.stream(clients)
                .forEach(asc -> {
                    if(asc != null){
                        ByteBuffer copy = buffer.duplicate();
                        asc.write(copy,copy,new WriterHandler(asc));
                    }
                });
    }
}
