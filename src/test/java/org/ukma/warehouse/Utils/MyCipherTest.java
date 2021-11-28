package org.ukma.warehouse.Utils;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class MyCipherTest {

    private static MyCipher cipher = new MyCipher();

    @Test
    public void checkEncoding() {
        String expected = "Hello, world!";
        String actual = cipher.encode(expected);

        Assertions.assertNotEquals(expected, actual);
    }

    @Test
    public void checkDecoding() {
        String starting = "Hello, world!";
        String expected = cipher.encode(starting);
        String actual = cipher.decode(expected);

        Assertions.assertNotEquals(expected, actual);
    }

    @Test
    public void checkEncodingAndDecoding() {
        String expected = "Hello, world!";
        String encoded = cipher.encode(expected);
        String actual = cipher.decode(encoded);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkBlankStringOperations() {
        String expected = "";
        String encoded = cipher.encode(expected);
        String actual = cipher.decode(encoded);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkStringWithOnlySpacesOperations() {
        String expected = "                                      ";
        String encoded = cipher.encode(expected);
        String actual = cipher.decode(encoded);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkDifferentStringsOperations() {
        String starting = "Hello, world!";
        String startingSecond = "Hello, world?";
        String encFst = cipher.encode(starting);
        String encSnd = cipher.encode(startingSecond);

        String expected = cipher.decode(encFst);
        String actual = cipher.decode(encSnd);

        Assertions.assertNotEquals(encFst, encSnd);
        Assertions.assertNotEquals(expected, actual);
    }

    @Test
    public void checkNullStringEncodingThrowsException() {
        String expected = null;
        Assertions.assertThrows(Exception.class, () -> {cipher.encode(expected);});
    }

    @Test
    public void checkNullStringDecodingThrowsException() {
        String expected = null;
        Assertions.assertThrows(Exception.class, () -> {cipher.decode(expected);});
    }

    @Test
    public void checkChangedEncodedStringThrowsException() {
        String starting = "Hello, world!";
        String encoded = cipher.encode(starting);
        char[] encodedAsArr = encoded.toCharArray();
        encodedAsArr[0] += 'A';
        String changedEncoded = String.valueOf(encodedAsArr);

        Assertions.assertThrows(Exception.class, () -> {cipher.decode(changedEncoded);});
    }
}
