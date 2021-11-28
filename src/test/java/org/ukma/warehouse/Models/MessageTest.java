package org.ukma.warehouse.Models;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.nio.ByteBuffer;

public class MessageTest {

    @Test
    public void checkFullMessageLength() {
        String message = "Hello, world!";
        Message msg = new Message(10, 20, message);

        int expected = Message.TYPE_AND_ID_LENGTH + message.length();
        int actual = msg.getFullLength();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkFullMessageLengthForBlankString() {
        String message = "";
        Message msg = new Message(10, 20, message);

        int expected = Message.TYPE_AND_ID_LENGTH + message.length();
        int actual = msg.getFullLength();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkCTypeDecoding() {
        String message = "Hello, world!";
        Message msg = new Message(10, 20, message);
        ByteBuffer buff = ByteBuffer.wrap(msg.toByteRepresentation());

        int expected = msg.getCType();
        int actual = buff.getInt();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkBUserIdDecoding() {
        String message = "Hello, world!";
        Message msg = new Message(10, 20, message);
        ByteBuffer buff = ByteBuffer.wrap(msg.toByteRepresentation());

        int expected = msg.getBUserId();
        int actual = buff.getInt(4);//skip first integer
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkGettingStringFromBytes() {
        String expected = "Hello, world!";
        Message msg = new Message(10, 20, expected);
        ByteBuffer buff = ByteBuffer.wrap(msg.toByteRepresentation());

        byte[] stringAsBytes = new byte[msg.getMessageLength()];
        buff.get(8, stringAsBytes); //skip integers
        String actual = new String(stringAsBytes);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkStringEncoding() {
        String expected = "Hello, world!";
        Message msg = new Message(10, 20, expected);
        msg.encode();

        String actual = msg.getMessage();
        Assertions.assertNotEquals(expected, actual);
    }

    @Test
    public void checkStringDecoding() {
        String starting = "Hello, world!";
        Message msg = new Message(10, 20, starting);

        msg.encode();
        String expected = msg.getMessage();
        msg.decode();
        String actual = msg.getMessage();
        Assertions.assertNotEquals(expected, actual);
    }

    @Test
    public void checkStringEncodingAndDecoding() {
        String expected = "Hello, world!";
        Message msg = new Message(10, 20, expected);

        msg.encode();
        msg.decode();
        String actual = msg.getMessage();
        Assertions.assertEquals(expected, actual);
    }
}
