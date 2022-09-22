package start.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import start.dto.request.LoginRequestDTO;
import start.dto.request.SignUpRequestDTO;
import start.entity.User;

@Service
public class AuthenService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User login(LoginRequestDTO loginRequestDTO){
        Authentication authentication = null;
        System.out.println(authenticationManager);
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getUsername(),
                            loginRequestDTO.getPassword()
                    )
            );

        } catch (Exception e) {
//            throw new EntityNotFound("Username or password invalid");
            e.printStackTrace();
        }
        return null;
    }

    public User signUp(SignUpRequestDTO signUpRequestDTO){
        return new User(signUpRequestDTO.getUsername(), passwordEncoder.encode(signUpRequestDTO.getPassword()));
    }

}
