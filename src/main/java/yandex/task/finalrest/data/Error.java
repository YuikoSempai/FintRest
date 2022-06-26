package yandex.task.finalrest.data;

public class Error {

    private final Integer code;
    private final String message;

    public Error(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Error(Integer code){
        this.code = code;
        this.message = null;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Error{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
