package com.java.tutorial.clean_structure.model;
import lombok.Data;


/**
 * @Data: 这是 Lombok 提供的神奇注解。
 * 它会自动帮你生成 Getter, Setter, toString 等方法。
 * 这样你就不用手写那些无聊的代码了。
 */
@Data
public class Greeting {
    private String message;
    private String status;
    private int code;

    public Greeting(String message, String status, int code){
        this.message = message;
        this.status = status;
        this.code = code;
    }
}
