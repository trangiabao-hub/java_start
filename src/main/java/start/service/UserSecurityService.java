package start.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import start.dto.UserSecurity;
import start.entity.User;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserSecurityService implements UserDetailsService {


//    @Autowired
//    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
        User user = new User("tesst","$2a$10$FjM8ye2giFVsYUW6.3XPz.xleDj7EL3JhFid0CIbQWWxAChrhG4u2");

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if(authorities.isEmpty()){
            authorities.add(new SimpleGrantedAuthority("no role"));
        }

        return new UserSecurity(user,authorities);
    }
}
