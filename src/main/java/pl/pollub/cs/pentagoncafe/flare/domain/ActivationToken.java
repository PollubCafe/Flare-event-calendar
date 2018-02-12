package pl.pollub.cs.pentagoncafe.flare.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Data
@EqualsAndHashCode
public class ActivationToken {
    private static final int EXPIRATION = 60 * 24;

    private String token;
    private Instant expirationDate;

    public ActivationToken(String token){
        this.token = token;
        expirationDate = Instant.now().plus(EXPIRATION, ChronoUnit.MINUTES);
    }

    public void refreshDate() {
        this.expirationDate = Instant.now().plus(EXPIRATION, ChronoUnit.MINUTES);
    }
}
