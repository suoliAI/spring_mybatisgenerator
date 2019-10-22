package com.gnnt.mybatisgenerator.response;



import java.io.Serializable;

public class BaseResponse implements Serializable {
    private static final long serialVersionUID = 4664393123328239186L;
    private Integer code;
    private String message;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BaseResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public BaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public static BaseResponse buildSuccess( Object data){
        return new BaseResponse(200,"success",data);
    }

    public static BaseResponse buildFail(Integer code, String message){
        return new BaseResponse(code,message);
    }
}
