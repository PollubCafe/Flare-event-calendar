package pl.pollub.cs.pentagoncafe.flare.component.security.bruteForceAttackGuard;

public interface BruteForceAttackGuard {
    void loginSucceeded(String key);

    void loginFailed(String key);

    boolean isBlocked(String key);
}
