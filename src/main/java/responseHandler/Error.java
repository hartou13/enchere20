package responseHandler;

import java.io.Serializable;

public class Error extends Response{
    Integer code;
    String message;
    public Error(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
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
    public Error() {
    }
}
