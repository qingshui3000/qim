package common.manager;

import common.entity.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupManager {
    //默认群组ID
    public static final String DEFAULT_GROUP_ID = "ABC";

    //群组集合
    private static final Map<String,List<Client>> groups = new HashMap<>();

    /**
     * 为群组添加新成员
     * @param key
     * @param value
     */
    public static void put(String key, Client value) {
        List<Client> clients = groups.get(key);
        if(clients == null){
            clients = new ArrayList<>();
            groups.put(key,clients);
        }
        clients.add(value);
    }

    public static List<Client> get(String key) {
        List<Client> clients = groups.get(key);
        if(clients != null){
            return clients;
        }
        return new ArrayList<>();
    }
}
