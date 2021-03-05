package library.base;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author yeyuting
 * @create 2021/2/26
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 1430633339880116031L;

    private boolean success = true ;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer status ;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code ;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg ;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data ;

    public Result(){

    }
    public Result(boolean success){
        this.success = success ;
    }

    public Result(boolean success, Integer status) {
        this.success = success;
        this.status = status;
    }

    public Result(boolean success, String code, String msg) {
        this(success);
        this.code = code;
        this.msg = msg;
    }

    public Result(boolean success, Integer status, String code, String msg) {
        this.success = success;
        this.status = status;
        this.code = code;
        this.msg = msg;
    }

    public Result(boolean success, String code, String msg, Object data) {
        this(success);
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
