package mgs.training.springboot.belajarjdbc.dto;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = -6034172716230758878L;

	public CustomException(Integer code, String message, Throwable cause) {
		super(code.toString().concat("|").concat(message), cause);
	}
	
	public CustomException(Integer code, String message) {
		super(code.toString().concat("|").concat(message));
	}

	public CustomException(String message) {
		super(message);
	}

}
