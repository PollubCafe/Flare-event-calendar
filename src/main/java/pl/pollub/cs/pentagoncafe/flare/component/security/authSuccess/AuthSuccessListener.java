package pl.pollub.cs.pentagoncafe.flare.component.security.authSuccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import pl.pollub.cs.pentagoncafe.flare.component.security.bruteForceAttackGuard.BruteForceAttackGuard;

@Component
public class AuthSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final BruteForceAttackGuard bruteForceAttackGuard;

    @Autowired
    AuthSuccessListener(final BruteForceAttackGuard bruteForceAttackGuard) {
        this.bruteForceAttackGuard = bruteForceAttackGuard;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent e) {
        WebAuthenticationDetails auth = (WebAuthenticationDetails) e.getAuthentication().getDetails();

        bruteForceAttackGuard.loginSucceeded(auth.getRemoteAddress());
    }
}
