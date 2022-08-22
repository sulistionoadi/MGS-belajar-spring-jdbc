package mgs.training.springboot.belajarjdbc.dto.http;

import java.util.Optional;

import mgs.training.springboot.belajarjdbc.constant.ErrorCode;

public class HttpRespModel<T> {

	private boolean success;
	private int code; // value of ErrorCode.java
	private String message;
	private T data;

	public HttpRespModel() {}

    public HttpRespModel(T data) {
        this(true, ErrorCode.SUCCESS, "Success", data);
    }

    public HttpRespModel(boolean success, int code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> HttpRespModel<T> ok(T data) {
        return new HttpRespModel<>(true, ErrorCode.SUCCESS, "Success", data);
    }

    public static <T> HttpRespModel<T> ok(T data, String message) {
        return new HttpRespModel<>(true, ErrorCode.SUCCESS, message, data);
    }

    public static <T> HttpRespModel<T> error(String message) {
        return error(ErrorCode.FAILED, message, null);
    }

    public static <T> HttpRespModel<T> error(int code, String message) {
        return error(code, message, null);
    }

    public static <T> HttpRespModel<T> error(int code, String message, T data) {
        return new HttpRespModel<>(false, code, message, data);
    }

    public Optional<T> toOptional() {
        return Optional.ofNullable(this.data);
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "HttpRespModel{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}
