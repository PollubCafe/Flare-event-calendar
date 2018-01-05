package pl.pollub.cs.pentagoncafe.flare.domain;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Data
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;

    private String token;
    private Long expirationDate;

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public void refreshDate() {
        this.expirationDate=calculateExpiryDate(EXPIRATION).getTime();
    }

    public VerificationToken(String token){
        this.token = token;
        expirationDate = calculateExpiryDate(EXPIRATION).getTime();
    }
}
