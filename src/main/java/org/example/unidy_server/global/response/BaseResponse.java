package org.example.unidy_server.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    private final String status;
    private final String message;
    private final List<T> data;

    public BaseResponse(
            String status,
            String message,
            List<T> data
    ) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResponse<T> of(
            String status,
            String message,
            List<T> data
    ) {
        return new BaseResponse<>(
                status,
                message,
                data);
    }
}