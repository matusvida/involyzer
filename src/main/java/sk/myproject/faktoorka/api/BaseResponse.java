package sk.myproject.faktoorka.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import sk.myproject.faktoorka.api.response.ResponseStatus;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    private Object data;
    private Integer status;
    private String errorMessage;

    public BaseResponse(ResponseStatus status, Object data) {
        this.status = status.getValue();
        this.data = data;
    }

    public BaseResponse(ResponseStatus status, String errorMessage) {
        this.status = status.getValue();
        this.errorMessage = errorMessage;
    }

    public BaseResponse(ResponseStatus status) {
        this.status = status.getValue();
    }
}
