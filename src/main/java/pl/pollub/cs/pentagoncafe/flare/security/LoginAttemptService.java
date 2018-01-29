package pl.pollub.cs.pentagoncafe.flare.security;

public interface LoginAttemptService {
    void loginSucceeded(String key);

    void loginFailed(String key);

    boolean isBlocked(String key);
}
