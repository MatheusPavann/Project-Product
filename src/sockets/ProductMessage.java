package sockets;

import java.io.Serializable;

public class ProductMessage implements Serializable {
    public enum ActionType { CREATE, UPDATE, DELETE }

    private ActionType action;
    private String code;
    private String name;

    public ProductMessage(ActionType create, Long code, String name) {}

    public ProductMessage(ActionType action, String code, String name) {
        this.action = action;
        this.code = code;
        this.name = name;
    }

    public ActionType getAction() {
        return action;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
