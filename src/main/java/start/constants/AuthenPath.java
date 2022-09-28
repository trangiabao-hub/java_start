package start.constants;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AuthenPath {
    public ArrayList<String> listPathAuthen;

    public AuthenPath(){
        listPathAuthen = new ArrayList<>();
        listPathAuthen.add("/authen");
    }

    public boolean isAuthen(String path){
        return listPathAuthen.contains(path);
    }

}
