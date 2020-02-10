package client;

import common.Address;
import common.Console;
import common.kit.Writer;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

public class ClientMain {
    public static void main(String[] args) throws Exception {
        //1.获取连接
        AsynchronousSocketChannel asc = getAsc();
        //获取输入并发送给server
        while (true){
            String content = Console.reader.readLine();
            if("stop".equals(content)){
                break;
            }
            ByteBuffer buffer = ByteBuffer.wrap(content.getBytes());
            asc = write(asc,buffer);
        }

    }

    private static AsynchronousSocketChannel write(AsynchronousSocketChannel asc, ByteBuffer buffer) throws Exception {
        if(!asc.isOpen()){
            asc = getAsc();
            System.out.println("重新连接");
        }
        Writer.write(buffer,asc);
        return asc;
    }

    private static AsynchronousSocketChannel getAsc() throws Exception {
        AsynchronousSocketChannel asc = AsynchronousSocketChannel.open();
        asc.connect(Address.loc,asc,new ClientHandler());
        Thread.sleep(1000);
        return asc;
    }
}
