package mgs.training.springboot.belajarjdbc.dto.http;

import java.util.List;

import javax.validation.constraints.NotNull;

import mgs.training.springboot.belajarjdbc.constant.ErrorCode;

public class HttpPagedModel<T> {

	private boolean success;
	private int code; // value of ErrorCode.java
	private String message;
	private List<T> data;
	private int size;
	private long totalElement;

	public HttpPagedModel() {
	}

	public HttpPagedModel(@NotNull List<T> data) {
		this(true, ErrorCode.SUCCESS, "Success", data, data.size(), data.size());
	}

	public HttpPagedModel(boolean success, int code, String message, List<T> data, int size, long totalElement) {
		this.success = success;
		this.code = code;
		this.message = message;
		this.data = data;
		this.size = size;
		this.totalElement = totalElement;
	}

	public static <T> HttpPagedModel<T> ok(List<T> data) {
		return new HttpPagedModel<>(data);
	}

	public static <T> HttpPagedModel<T> ok(List<T> data, int size, long totalElement) {
		return new HttpPagedModel<>(true, ErrorCode.SUCCESS, "Success", data, size, totalElement);
	}

	public static <T> HttpPagedModel<T> ok(List<T> data, String message, int size, long totalElement) {
		return new HttpPagedModel<>(true, ErrorCode.SUCCESS, message, data, size, totalElement);
	}

	public static <T> HttpPagedModel<T> error(String message) {
		return error(ErrorCode.FAILED, message, null);
	}

	public static <T> HttpPagedModel<T> error(int code, String message) {
		return error(code, message, null);
	}

	public static <T> HttpPagedModel<T> error(int code, String message, List<T> data) {
		return new HttpPagedModel<>(false, code, message, data, 0, 0);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getTotalElement() {
		return totalElement;
	}

	public void setTotalElement(long totalElement) {
		this.totalElement = totalElement;
	}

	@Override
	public String toString() {
		return "HttpPagedModel{" + "success=" + success + ", code=" + code + ", message='" + message + '\'' + ", data="
				+ data + ", size=" + size + ", totalElement=" + totalElement + '}';
	}

}
