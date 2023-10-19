package start.sercurity;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import start.constants.AuthenPath;
import start.entity.User;
import start.exception.exceptions.ExpiredToken;
import start.exception.exceptions.InValidToken;
import start.exception.exceptions.NotAllowException;
import start.repository.UserRepository;
import start.utils.ResponseHandler;
import start.utils.TokenHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class Filter extends OncePerRequestFilter {

    @Autowired
    AuthenPath authenPath;

    @Autowired
    ResponseHandler responseHandler;

    @Autowired
    TokenHandler tokenHandler;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        boolean isAuthen = authenPath.isAuthen(uri);
        if(isAuthen){
            String id;
            String token = getToken(request);
            if(token == null) {
                responseHandler.responseResolver(request,response, new NotAllowException("Empty Token!"));
                return;
            }
            try {
                id = tokenHandler.getInfoByToken(token);
            } catch (ExpiredJwtException expiredJwtException) {
                responseHandler.responseResolver(request,response, new NotAllowException("Expired Token!"));
                return;
            } catch (MalformedJwtException malformedJwtException) {
                responseHandler.responseResolver(request,response, new NotAllowException("Invalid Token!"));
                return;
            }
            if (id != null) {
                // token chuẩn
                // tạo 1 đối tượng mà spring security hiểu
                User user = userRepository.findUserByUsername(id);
                //token hop le
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                // chạy đc tới đây => set up cho thằng spring security ok hết r
                // truy cập vào chạy api
                // filter cho phép truy cập thì mới đc truy cập
                filterChain.doFilter(request, response);
            } else {
                // token tào lao
                responseHandler.responseResolver(request,response, new NotAllowException("Invalid Token!"));
            }
        }else{
            filterChain.doFilter(request, response);
        }
    }

    private String getToken(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");
        if (authorization == null)
            return null;

        String token = authorization.split(" ")[1];
        return token;
    }
}
