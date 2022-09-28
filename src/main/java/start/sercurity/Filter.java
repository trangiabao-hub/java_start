package start.sercurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import start.constants.AuthenPath;
import start.exception.exceptions.NotAllowException;
import start.utils.ResponseHandler;

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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        boolean isAuthen = authenPath.isAuthen(uri);
        System.out.println(isAuthen);
        if(isAuthen){
            responseHandler.responseResolver(request,response, new NotAllowException("Not Allow!"));
        }else{
            filterChain.doFilter(request, response);
        }

    }
}
