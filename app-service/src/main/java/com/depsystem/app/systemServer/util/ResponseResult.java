/**
 * @author JOJO
 * @class ResponseResult
 * @date 2023/3/11
 * @apiNote
 */

package com.depsystem.app.systemServer.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

/**
 * <b><code>ResponseResult</code></b>
 * <p>
 * Description响应信息主体
 * </p>
 * <b>Creation Time:</b> 2019/11/26 17:55.
 *
 * @author chensen
 * @since compile-boiler-code 0.1.0
 */
@Component
public class ResponseResult<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String msg;

    @Getter
    @Setter
    private T data;

    @Getter
    @Setter
    private String token;

    public static <T> ResponseResult<T> ok(int code, String msg) {
        return restResult(null, HttpEnum.OK_200.code(), HttpEnum.OK_200.desc(),null);
    }

    public static <T> ResponseResult<T> ok(T data) {
        return restResult(data, HttpEnum.OK_200.code(), HttpEnum.OK_200.desc(),null);
    }

    public static <T> ResponseResult<T> ok(T data, String msg) {
        return restResult(data, HttpEnum.OK_200.code(), msg,null);
    }

    public static <T> ResponseResult<T> ok(int code,String msg,T data,String token){
        return restResult(data,HttpEnum.OK_200.code(),msg,token);
    }

    /**
     * 系统内部错误
     * @return // 返回
     * @param <T> // 泛型
     */
    public static <T> ResponseResult<T> failed() {
        return restResult(null, HttpEnum.ERROR_500.code(),
                HttpEnum.ERROR_500.desc(),null);
    }

    public static <T> ResponseResult<T> failed(String msg) {
        return restResult(null, HttpEnum.ERROR_500.code(), msg,null);
    }

    public static <T> ResponseResult<T> failed(int code, String msg) {
        return restResult(null, code, msg,null);
    }

    public static <T> ResponseResult<T> failed(T data) {
        return restResult(data, HttpEnum.ERROR_500.code(),
                HttpEnum.ERROR_500.desc(),null);
    }

    public static <T> ResponseResult<T> failed(T data, String msg) {
        return restResult(data, HttpEnum.ERROR_500.code(), msg,null);
    }

    /**
     * 支付错误
     * @param data // 支付对象
     * @return     // 返回值
     * @param <T>  // 泛型
     */
    public static <T> ResponseResult<T> notplay(T data){
        return restResult(data,HttpEnum.ERROR_700.code(),HttpEnum.ERROR_700.desc(),null);
    }

    public static <T> ResponseResult<T> notplay(T data, String msg){
        return restResult(data,HttpEnum.ERROR_700.code(), msg,null);
    }

    public static <T> ResponseResult<T> notplay(int code,String msg){
        return restResult(null,code,msg,null);
    }

    public static <T> ResponseResult<T> notplay(String msg){
        return restResult(null,HttpEnum.ERROR_700.code(), msg,null);
    }

    public static <T> ResponseResult<T> notplay(){
        return restResult(null, HttpEnum.ERROR_700.code(), HttpEnum.ERROR_700.desc(),null);
    }

    /**
     * 构造器
     * @param data // 数据对象
     * @param code // 数据状态
     * @param msg  // 数据提示内容
     * @return     // 返回
     * @param <T>  // 泛型
     */
    private static <T> ResponseResult<T> restResult(T data, int code,
                                                    String msg,String token) {
        ResponseResult<T> apiResult = new ResponseResult<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        apiResult.setToken(token);
        return apiResult;
    }
}
