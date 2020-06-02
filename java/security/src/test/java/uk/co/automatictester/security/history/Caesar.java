package uk.co.automatictester.security.history;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Caesar {

    private static final int SHIFT = 3;
    private static final int LOWER_BOUND = 65;
    private static final int UPPER_BOUND = 90;

    @Test
    public void testEncrypt() {
        String input = "ABCDEFXYZ";
        String output = encrypt(input);
        String expected = "DEFGHIABC";
        assertThat(expected, equalTo(output));
    }

    @Test
    public void testDecrypt() {
        String input = "ABCDEFXYZ";
        String output = decrypt(input);
        String expected = "XYZABCUVW";
        assertThat(expected, equalTo(output));
    }

    @DataProvider(name = "getInvalidData")
    private Object[][] getInvalidData() {
        return new Object[][]{
                {"@"},
                {"["}
        };
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "getInvalidData")
    public void testInvalidInput(String input) {
        encrypt(input);
    }

    private String encrypt(String s) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            validate(c);
            char next = encryptChar(c);
            if (next > UPPER_BOUND) {
                int offset = next - UPPER_BOUND;
                next = (char) (LOWER_BOUND + offset - 1);
            }
            output.append(next);
        }
        return output.toString();
    }

    private String decrypt(String s) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            validate(c);
            char next = decryptChar(c);
            if (next < LOWER_BOUND) {
                int offset = LOWER_BOUND - next;
                next = (char) (UPPER_BOUND - offset + 1);
            }
            output.append(next);
        }
        return output.toString();
    }

    private char encryptChar(char c) {
        return (char) (c + SHIFT);
    }

    private char decryptChar(char c) {
        return (char) (c - SHIFT);
    }

    private void validate(char c) {
        if (c < LOWER_BOUND || c > UPPER_BOUND) throw new IllegalArgumentException();
    }
}
