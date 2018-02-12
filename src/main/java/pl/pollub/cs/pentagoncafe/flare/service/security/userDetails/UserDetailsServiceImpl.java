package pl.pollub.cs.pentagoncafe.flare.service.security.userDetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.pollub.cs.pentagoncafe.flare.DTO.security.UserDetailsImpl;
import pl.pollub.cs.pentagoncafe.flare.component.message.Messages;
import pl.pollub.cs.pentagoncafe.flare.component.security.bruteForceAttackGuard.BruteForceAttackGuard;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.exception.auth.TooManyLoginAttempts;
import pl.pollub.cs.pentagoncafe.flare.repository.user.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    private final BruteForceAttackGuard bruteForceAttackGuard;

    private final HttpServletRequest request;

    private final Messages messages;

    public UserDetailsServiceImpl(UserRepository userRepository, BruteForceAttackGuard bruteForceAttackGuard, HttpServletRequest request, Messages messages) {
        this.userRepository = userRepository;
        this.bruteForceAttackGuard = bruteForceAttackGuard;
        this.request = request;
        this.messages = messages;
    }

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
        if (bruteForceAttackGuard.isBlocked(ip)) {
            throw new TooManyLoginAttempts();
        }

        User user;
        if(usernameOrEmail.contains("@")){
            user = userRepository.findByEmail(usernameOrEmail)
                    .orElseThrow(() -> new UsernameNotFoundException(messages.get("login.userAccount.notFound.ByEmail")));
        }
        else{
            user = userRepository.findByNick(usernameOrEmail)
                    .orElseThrow(() -> new UsernameNotFoundException(messages.get("login.userAccount.notFound.ByUsername")));
        }

        boolean enabled = user.isEnabled();
        boolean accountNonLocked = !user.isBanned();

        return new UserDetailsImpl(user.getNick(), user.getPassword(), user.getEmail(), enabled, accountNonLocked, user.getRole());
    }
}
