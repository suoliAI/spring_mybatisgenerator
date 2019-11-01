package com.gnnt.mybatisgenerator.response;

import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.List;

/**
 * @author yb113008
 * @date 2019/02/20
 */
public class BaseResponse implements Serializable {

    private String code;
    private String message;
    private boolean success;
    private List<FieldError> fieldErrorList;

    public BaseResponse() {

    }

    BaseResponse(String code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    BaseResponse(String code, String message, boolean success, List<FieldError> fieldErrorList) {
        this(code, message, success);
        this.fieldErrorList = fieldErrorList;
    }

    public static BaseResponse buildSuccess() {
        return new BaseResponse("200", "SUCCESS", true);
    }

    public static BaseResponse buildFailed(String code, String message) {
        return new BaseResponse(code, message, false);
    }

    public static BaseResponse buildViladtorFailed(String code, String message, List<FieldError> fieldErrorList) {
        return new BaseResponse(code, message, false, fieldErrorList);
    }

    public static <T extends BaseResponse> T buildFailed(Class<T> clazz, String code, String message,
                                                         List<FieldError> fieldErrorList) {
        try {
            T t = clazz.newInstance();
            t.setCode(code);
            t.setMessage(message);
            t.setFieldErrorList(fieldErrorList);
            return t;
        } catch (Exception e) {
            throw new RuntimeException(
                "Build failed base response error. " + clazz.getSimpleName() + " " + code + " " + message);
        }
    }

    public static <T extends BaseResponse> T buildFailed(Class<T> clazz, String code, String message) {
        return buildFailed(clazz, code, message, null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<FieldError> getFieldErrorList() {
        return fieldErrorList;
    }

    public void setFieldErrorList(List<FieldError> fieldErrorList) {
        this.fieldErrorList = fieldErrorList;
    }
}
