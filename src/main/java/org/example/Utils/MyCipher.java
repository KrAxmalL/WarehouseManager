package org.example.Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

//class used to separate additional work with java cipher from Packet and Message
public class MyCipher {

    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String ENCRYPTION_ALGORITHM_FULL = "AES/CBC/PKCS5Padding";

    //hardcoded key
    private static final int KEY_SIZE = 128;
    private static final byte[] keyAsBytes = {-98, 2, -71, 120, -9, -29, 9, 91, -40, -38, 37, 91, -49, 7, -60, 66};
    private static final Key key = new SecretKeySpec(keyAsBytes, ENCRYPTION_ALGORITHM);

    //hardcoded iv vector
    private static final int IV_SIZE = 16;
    private static final byte[] ivASBytes = {37, -45, 32, -23, -35, -124, 11, -91, 24, 0, -49, 69, -8, 41, -32, -51};
    private static final IvParameterSpec iv = new IvParameterSpec(ivASBytes);

    private Cipher cipher;

    public MyCipher() {
        try {
            cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM_FULL);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public String encode(String toEncode) {
        //setting cipher to encryption mode
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        //converting given string to bytes and encoding these bytes
        byte[] encodedStringAsBytes = null;
        try {
            encodedStringAsBytes = cipher.doFinal(toEncode.getBytes());
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        //converting encoded bytes back to string(for better representation)
        return Base64.getEncoder()
                .encodeToString(encodedStringAsBytes);
    }

    public String decode(String toDecode) {
        //setting cipher to decryption mode
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        //converting string into bytes array and decoding it
        byte[] decodedStringAsBytes = null;
        try {
            decodedStringAsBytes = cipher.doFinal(Base64.getDecoder()
                                         .decode(toDecode));
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        //creating string from encoded bytes
        return new String(decodedStringAsBytes);
    }
}
