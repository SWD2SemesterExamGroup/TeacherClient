package dk.kea.teacher.artifacts.Helpers;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

/**
 * Java generate "random" string
 * https://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
 */
public class RandomString
{
    // Static Final Fields
    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lower = upper.toLowerCase(Locale.ROOT);
    private static final String special = "!#%&/()=?-:.;,><+-*";
    private static final String digits = "0123456789";
    private static final String alphanum = upper + lower + digits + special;

    // Fields
    private final Random random;
    private final char[] symbols;
    private final char[] buf;

    /**
     * Generate a random string.
     * @return String
     */
    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

    /**
     * Actual Random String generation
     * @param length
     * @param random
     * @param symbols
     */
    public RandomString(int length, Random random, String symbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    /**
     Create an alphanumeric string generator.
     @param length
     @param random
     */
    public RandomString(int length, Random random) {
        this(length, random, alphanum);
    }

    /**
     Create an alphanumeric strings from a secure generator.
     @param length
     */
    public RandomString(int length) {
        this(length, new SecureRandom());
    }

    /**
     * Create session identifiers.
     */
    public RandomString() {
        this(21);
    }
}
