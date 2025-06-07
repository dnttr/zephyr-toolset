package org.dnttr.zephyr.toolset.utils;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author dnttr
 */

public class StringUtils {

    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateRandomString(int length) {
        byte[] bytes = new byte[length];
        secureRandom.nextBytes(bytes);

        return encode(bytes, length);
    }

    public static String encode(byte[] input) {
        return Base64.getEncoder().encodeToString(input);
    }

    public static String encode(byte[] input, int length) {
        String encodedString = encode(input);
        encodedString = encodedString.substring(0, length);

        return encodedString;
    }
}
