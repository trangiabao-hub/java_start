package start.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import start.dto.request.LoginRequestDTO;

@RestController
public class Authen {

    public void login(@RequestBody LoginRequestDTO loginRequestDTO){
        
    }

}
