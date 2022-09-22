package start.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class Test {


    @GetMapping("/test")
    @PreAuthorize("hasAuthority('aaaaaaaaaaa')")
    public void test(){
        System.out.println("test");
    }
}
