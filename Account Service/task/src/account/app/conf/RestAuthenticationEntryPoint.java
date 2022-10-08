package account.app.conf;

import account.app.model.EventName;
import account.app.model.SecurityEvent;
import account.app.model.UserFailLoginCounter;
import account.app.service.SecurityEventService;
import account.app.service.UserFailLoginCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Enumeration;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    SecurityEventService securityEventService;

    @Autowired
    UserFailLoginCounterService userFailLoginCounterService;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        String creds = authorization.substring("Basic".length()).trim();
        byte[] decoded = Base64.getDecoder().decode(creds);
        String s = new String(decoded, StandardCharsets.UTF_8);
        String[] upArr = s.split(":");
        securityEventService.createSecurityEvent(
                new SecurityEvent(EventName.LOGIN_FAILED,upArr[0] ,request.getServletPath(), request.getServletPath()));

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }


}
