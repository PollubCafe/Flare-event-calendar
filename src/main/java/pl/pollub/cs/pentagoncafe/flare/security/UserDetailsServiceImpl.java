package pl.pollub.cs.pentagoncafe.flare.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.exception.auth.TooManyLoginsAttempts;
import pl.pollub.cs.pentagoncafe.flare.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private LoginAttemptService loginAttemptService;


    @Autowired
    private HttpServletRequest request;


    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new TooManyLoginsAttempts();
        }

        User user;
        if(usernameOrEmail.contains("@")){
            user = userRepository.findByNick(usernameOrEmail).orElseThrow(() -> new UsernameNotFoundException("Bad username or password"));
        }
        else{
            user = userRepository.findByEmail(usernameOrEmail).orElseThrow(() -> new UsernameNotFoundException("Bad username or password"));
        }

        boolean enabled = user.isEnabled();
        boolean accountNonLocked = !user.isBanned();

        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().name()));


        return new UserDetailsImpl(user.getName(), user.getPassword(), user.getEmail(), enabled, accountNonLocked, user.getRole());
    }
}
