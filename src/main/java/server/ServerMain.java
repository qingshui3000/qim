package server;

import common.Address;
import common.entity.Client;
import common.Console;
import common.manager.GroupManager;
import common.kit.Writer;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.List;

import static common.manager.GroupManager.DEFAULT_GROUP_ID;

public class ServerMain {
    public static void main(String[] args) throws Exception {
        //1.获取连接
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
        //2.bind
        server.bind(Address.loc);
        //3.接收(异步)
        server.accept(server,new ServerHandler());

        //回复
        while (true){
            String content = Console.reader.readLine();
            if("stop".equals(content)){
                break;
            }
            ByteBuffer buffer = ByteBuffer.wrap(content.getBytes());
            List<Client> clients = GroupManager.get(DEFAULT_GROUP_ID);
            Writer.write(buffer,clients);
        }
    }
}
