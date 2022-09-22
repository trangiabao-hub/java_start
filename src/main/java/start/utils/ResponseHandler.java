package start.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import start.dto.response.ResponseDTO;

@Component
public class ResponseHandler<T> {

    public ResponseEntity<ResponseDTO<T>> response(int statusCode, String message, T data){
        return ResponseEntity.ok(new ResponseDTO<T>(statusCode, message, data));
    }

}
