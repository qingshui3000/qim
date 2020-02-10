package common.manager;

import common.entity.Client;

import java.nio.channels.AsynchronousSocketChannel;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ClientManager {

    //已连接客户端（实现单点登录）
    private static final Map<String,Client> clients = new HashMap<>();

    /**
     * 添加新的客户端
     * @param asc
     * @return
     */
    public static Client put(AsynchronousSocketChannel asc) {
        Client client = new Client(asc);
        clients.put(client.getName(),client);
        return client;
    }
    public static Client get(AsynchronousSocketChannel asc) {
        Collection<Client> values = clients.values();
        Iterator<Client> it = values.iterator();
        while (it.hasNext()){
            Client client = it.next();
            if(asc.equals(client.getAsc())){
                return client;
            }
        }
        return null;
    }
}
