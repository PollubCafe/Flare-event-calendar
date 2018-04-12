package pl.pollub.cs.pentagoncafe.flare.component.security.authFailure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import pl.pollub.cs.pentagoncafe.flare.component.message.Messages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Map<String,String> messagesMap = new HashMap<>();

    @Autowired
    public AuthFailureHandler(Messages messages) {
        messagesMap.put("Bad credentials", messages.get("login.badCredentials"));
        messagesMap.put("User is disabled", messages.get("login.userAccount.disabled"));
        messagesMap.put("User account is locked", messages.get("login.userAccount.locked"));
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        String exceptionMessage = exception.getMessage();
        writer.write(messagesMap.getOrDefault(exceptionMessage, exceptionMessage));
        writer.flush();
    }
}
