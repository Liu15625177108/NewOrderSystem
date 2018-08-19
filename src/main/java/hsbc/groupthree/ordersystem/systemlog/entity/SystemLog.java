package hsbc.groupthree.ordersystem.systemlog.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @ClassName SystemLog
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.systemlog
 * @Date 2018/8/17 10:45
 */
@Entity
public class SystemLog {
    @Id
    private String id;
    /**
     * operate name
     */
    private String name;
    /**
     * operate time
     */
    private String time;
    /**
     * operate what
     */
    private String operation;

    public SystemLog() {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "SystemLog{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }
}
