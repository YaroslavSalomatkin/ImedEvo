package imedevo.util;


public class ErrorBody {
    private final Integer code = 400;
    private String message;

    public ErrorBody(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
