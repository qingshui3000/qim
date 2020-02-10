package common.handler;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class WriterHandler implements CompletionHandler<Integer,ByteBuffer> {
    private final AsynchronousSocketChannel asc;
    public WriterHandler(AsynchronousSocketChannel asc) {
        this.asc = asc;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        if(buffer.hasRemaining()){
            asc.write(buffer,buffer,this);
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer buffer) {
        System.out.println("写出错误");
    }
}
