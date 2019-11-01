package com.gnnt.mybatisgenerator.response;

/**
 * @author yb113008
 * @date 2019/02/20
 */
public class DataResponse<T> extends BaseResponse {

    private T data;

    public DataResponse() {

    }

    public DataResponse(String code, String message, boolean success, T data) {
        super(code, message, success);
        this.data = data;
    }

    public static <T> DataResponse<T> buildSuccess(T data) {
        return new DataResponse<>("200", "SUCCESS", true, data);
    }

    public T getData() {
        return data;
    }
}
