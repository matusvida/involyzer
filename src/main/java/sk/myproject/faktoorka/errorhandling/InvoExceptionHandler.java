package sk.myproject.faktoorka.errorhandling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sk.myproject.faktoorka.api.BaseResponse;
import sk.myproject.faktoorka.api.response.ResponseStatus;

@ControllerAdvice
@Slf4j
public class InvoExceptionHandler {

    @ExceptionHandler({InvoException.class})
    public BaseResponse handleInvoException(InvoException e) {
        log.error(e.getMessage());
        return new BaseResponse(ResponseStatus.FAILED, e.getMessage());
    }
}
