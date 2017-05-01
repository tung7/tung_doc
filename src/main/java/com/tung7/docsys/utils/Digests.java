package com.tung7.docsys.utils;

import org.apache.tomcat.util.codec.DecoderException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/4/30.
 * @update
 */

public class Digests {

    private static SecureRandom random = new SecureRandom();
    private static int DEFAULT_SALT_LENGTH = 16;

    public static String encodeHex(byte[] bytes) {
        return  Hex.encodeHexString(bytes);
    }

    public static byte[] decodeHex(String string) {
        return  Hex.decodeHex(string);
    }

    public static byte[] generateSalt() {
        return generateSalt(DEFAULT_SALT_LENGTH);
    }

    public static byte[] generateSalt(int numBytes) {
        if (numBytes < 0) {
            throw  new IllegalArgumentException("numBytes argument must be a positive integer (1 or larger)");
        };
        byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);
        return bytes;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String salt = Hex.encodeHexString(Digests.generateSalt(DEFAULT_SALT_LENGTH));
            System.out.println( salt);
        }
    }

    public static byte[] sha1(byte[] input) {
        return digest(input, "SHA-1", (byte[])null, 1);
    }

    /**
     * 根据salt做sha1运算
     * @param input
     * @param salt
     * @return
     */
    public static byte[] sha1(byte[] input, byte[] salt) {
        return digest(input, "SHA-1", salt, 1);
    }

    public static byte[] sha1(InputStream input) throws IOException {
        return digest(input, "SHA-1");
    }

    /**
     * 根据salt做多次迭代 sha1运算
     * @param input
     * @param salt
     * @param iterations 迭代次数
     * @return
     */
    public static byte[] sha1(byte[] input, byte[] salt, int iterations) {
        return digest(input, "SHA-1", salt, iterations);
    }
    public static byte[] md5(InputStream input) throws IOException {
        return digest(input, "MD5");
    }

    /**
     *
     * @param input
     * @param algorithm
     * @param salt
     * @param iterations 迭代次数
     * @return
     */
    public static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            if(salt != null) {
                digest.update(salt);
            }

            byte[] result = digest.digest(input);

            for(int i = 1; i < iterations; ++i) {
                digest.reset();
                result = digest.digest(result);
            }

            return result;
        } catch (GeneralSecurityException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static byte[] digest(InputStream input, String algorithm) throws IOException {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            short bufferLength = 8192;
            byte[] buffer = new byte[bufferLength];

            for(int read = input.read(buffer, 0, bufferLength); read > -1; read = input.read(buffer, 0, bufferLength)) {
                digest.update(buffer, 0, read);
            }
            return digest.digest();
        } catch (GeneralSecurityException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static class Hex {
        public static final Charset DEFAULT_CHARSET;
        public static final String DEFAULT_CHARSET_NAME = "UTF-8";
        private static final char[] DIGITS_LOWER;
        private static final char[] DIGITS_UPPER;
        static {
            DEFAULT_CHARSET = Charset.forName(DEFAULT_CHARSET_NAME);
            DIGITS_LOWER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            DIGITS_UPPER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        }
        public static char[] encodeHex(byte[] data) {
            return encodeHex(data, true);
        }
        public static char[] encodeHex(byte[] data, boolean toLowerCase) {
            return encodeHex(data, toLowerCase?DIGITS_LOWER:DIGITS_UPPER);
        }

        public static byte[] decodeHex(String input) {
            try {
                return Hex.decodeHex(input.toCharArray());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        protected static String encodeHexString(byte[] data) {
            return new String(encodeHex(data));
        }

        protected static char[] encodeHex(byte[] data, char[] toDigits) {
            int l = data.length;
            char[] out = new char[l << 1];
            int i = 0;

            for(int j = 0; i < l; ++i) {
                out[j++] = toDigits[(240 & data[i]) >>> 4];
                out[j++] = toDigits[15 & data[i]];
            }
            return out;
        }

        protected static byte[] decodeHex(char[] data) throws Exception {
            int len = data.length;
            if((len & 1) != 0) {
                throw new Exception("Odd number of characters.");
            } else {
                byte[] out = new byte[len >> 1];
                int i = 0;

                for(int j = 0; j < len; ++i) {
                    int f = toDigit(data[j], j) << 4;
                    ++j;
                    f |= toDigit(data[j], j);
                    ++j;
                    out[i] = (byte)(f & 255);
                }

                return out;
            }
        }

        protected static int toDigit(char ch, int index) throws Exception {
            int digit = Character.digit(ch, 16);
            if(digit == -1) {
                throw new Exception("Illegal hexadecimal character " + ch + " at index " + index);
            } else {
                return digit;
            }
        }

    }


}
