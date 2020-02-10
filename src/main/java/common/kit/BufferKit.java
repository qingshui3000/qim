package common.kit;

import java.nio.ByteBuffer;

/**
 *  缓冲区工具类
 */
public class BufferKit {

    /**
     * 获取默认大小的缓冲区
     * @return
     */
    public static ByteBuffer getDef() {
        return ByteBuffer.allocate(1024);
    }
}
