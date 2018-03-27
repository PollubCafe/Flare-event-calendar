package pl.pollub.cs.pentagoncafe.flare.service.security.randomPassword;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;

public class RandomPasswordGenerator {

    public static String generate() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

    private static final int length = 10;

    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String lower = upper.toLowerCase(Locale.ROOT);

    private static final String digits = "0123456789";

    private static final String alphanum = upper + lower + digits;

    private static final Random random = new SecureRandom();

    private static final char[] symbols = alphanum.toCharArray();

    private static final char[] buf = new char[length];

}
