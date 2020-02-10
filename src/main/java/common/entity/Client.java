package common.entity;

import java.nio.channels.AsynchronousSocketChannel;
import java.util.UUID;

public class Client {
    private String id = UUID.randomUUID().toString();
    private String name = id;
    AsynchronousSocketChannel asc;

    public Client(AsynchronousSocketChannel asc) {
        this.asc = asc;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", asc=" + asc +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AsynchronousSocketChannel getAsc() {
        return asc;
    }

    public void setAsc(AsynchronousSocketChannel asc) {
        this.asc = asc;
    }
}
