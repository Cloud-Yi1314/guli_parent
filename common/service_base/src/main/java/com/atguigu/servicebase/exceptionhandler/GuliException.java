package com.atguigu.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YiCloud
 * @date 2020/12/9 - 22:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException{

    private Integer code; //状态码

    private String msg; //异常信息

    @Override
    public String toString() {
        return "GuliException{" +
        "message=" + this.getMessage() +
        ", code=" + code +
        '}';
    }
}
