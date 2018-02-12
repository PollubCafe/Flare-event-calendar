package pl.pollub.cs.pentagoncafe.flare.component.security.bruteForceAttackGuard;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class BruteForceAttackGuardImpl implements BruteForceAttackGuard {

    private final int MAX_ATTEMPT = 5;
    int attempts = 0;
    private LoadingCache<String, Integer> attemptsCache;


    public BruteForceAttackGuardImpl() {
        super();
        attemptsCache = CacheBuilder.newBuilder().
                expireAfterWrite(15, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() {
            @Override
            public Integer load(String key) {
                return 0;
            }
        });
    }

    @Override
    public void loginSucceeded(String key) {
        attemptsCache.invalidate(key);
    }

    public void loginFailed(String key) {

        try {
            attempts = attemptsCache.get(key);
        } catch (ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(key, attempts);
    }

    @Override
    public boolean isBlocked(String key) {
        try {
            return attemptsCache.get(key) >= MAX_ATTEMPT;
        } catch (ExecutionException e) {
            return false;
        }
    }
}
