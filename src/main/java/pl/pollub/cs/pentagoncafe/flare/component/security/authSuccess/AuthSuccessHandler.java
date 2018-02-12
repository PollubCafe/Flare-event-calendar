package pl.pollub.cs.pentagoncafe.flare.component.security.authSuccess;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.UserResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.security.UserDetailsImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final ObjectMapper mapper;

    AuthSuccessHandler(@Qualifier("mappingJackson2HttpMessageConverter") MappingJackson2HttpMessageConverter messageConverter) {
        this.mapper = messageConverter.getObjectMapper();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        HttpSession session = request.getSession();

        session.setAttribute("username", userDetails.getUsername());
        session.setAttribute("password", userDetails.getPassword());

        UserResponseDTO user = userDetails.getUserResponseDTO();

        PrintWriter writer = response.getWriter();
        mapper.writeValue(writer, user);
        response.setStatus(HttpServletResponse.SC_OK);

        writer.flush();
    }
}
