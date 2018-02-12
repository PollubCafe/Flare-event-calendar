package pl.pollub.cs.pentagoncafe.flare.component.security.authFailure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import pl.pollub.cs.pentagoncafe.flare.component.security.bruteForceAttackGuard.BruteForceAttackGuard;

@Component
public class AuthFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private final BruteForceAttackGuard bruteForceAttackGuard;

    @Autowired
    AuthFailureListener(final BruteForceAttackGuard bruteForceAttackGuard) {
        this.bruteForceAttackGuard = bruteForceAttackGuard;
    }

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        WebAuthenticationDetails auth = (WebAuthenticationDetails)
                e.getAuthentication().getDetails();

        bruteForceAttackGuard.loginFailed(auth.getRemoteAddress());
    }
}
