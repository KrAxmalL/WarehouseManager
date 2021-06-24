package org.example.Models;

import com.google.common.primitives.UnsignedLong;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.nio.ByteBuffer;

public class PacketTest {

    @Test
    public void checkBMagicDecoding() {
        String message = "Hello, world!";
        Message msg = new Message(10, 20, message);
        Packet pk = new Packet((byte)30, UnsignedLong.fromLongBits(40), msg);
        ByteBuffer buff = ByteBuffer.wrap(pk.toByteRepresentation());

        byte expected = pk.bMagic;
        byte actual = buff.get();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkBSrcDecoding() {
        String message = "Hello, world!";
        Message msg = new Message(10, 20, message);
        Packet pk = new Packet((byte)30, UnsignedLong.fromLongBits(40), msg);
        ByteBuffer buff = ByteBuffer.wrap(pk.toByteRepresentation());

        byte expected = pk.getBSrc();
        byte actual = buff.get(1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkBPktIdDecoding() {
        String message = "Hello, world!";
        Message msg = new Message(10, 20, message);
        Packet pk = new Packet((byte)30, UnsignedLong.fromLongBits(40), msg);
        ByteBuffer buff = ByteBuffer.wrap(pk.toByteRepresentation());

        UnsignedLong expected = pk.getBPktId();
        UnsignedLong actual = UnsignedLong.fromLongBits(buff.getLong(2));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkWLenDecoding() {
        String message = "Hello, world!";
        Message msg = new Message(10, 20, message);
        Packet pk = new Packet((byte)30, UnsignedLong.fromLongBits(40), msg);
        ByteBuffer buff = ByteBuffer.wrap(pk.toByteRepresentation());

        int expected = pk.getWLen();
        int actual = buff.getInt(10);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkWCrc16MetaDecoding() {
        String message = "Hello, world!";
        Message msg = new Message(10, 20, message);
        Packet pk = new Packet((byte)30, UnsignedLong.fromLongBits(40), msg);
        ByteBuffer buff = ByteBuffer.wrap(pk.toByteRepresentation());

        short expected = pk.getWCrc16Meta();
        short actual = buff.getShort(14);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkMessageCTypeDecoding() {
        String message = "Hello, world!";
        Message msg = new Message(10, 20, message);
        Packet pk = new Packet((byte)30, UnsignedLong.fromLongBits(40), msg);
        ByteBuffer buff = ByteBuffer.wrap(pk.toByteRepresentation());

        int expected = pk.getMsg().getCType();
        int actual = buff.getInt(16);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkMessageBUserIdDecoding() {
        String message = "Hello, world!";
        Message msg = new Message(10, 20, message);
        Packet pk = new Packet((byte)30, UnsignedLong.fromLongBits(40), msg);
        ByteBuffer buff = ByteBuffer.wrap(pk.toByteRepresentation());

        int expected = pk.getMsg().getBUserId();
        int actual = buff.getInt(20);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkMessageStringDecoding() {
        String message = "Hello, world!";
        Message msg = new Message(10, 20, message);
        Packet pk = new Packet((byte)30, UnsignedLong.fromLongBits(40), msg);
        ByteBuffer buff = ByteBuffer.wrap(pk.toByteRepresentation());

        String expected = pk.getMsg().getMessage();
        byte[] messageAsBytes = new byte[pk.getWLen() - Message.TYPE_AND_ID_LENGTH];
        buff.get(24, messageAsBytes);
        String actual = new String(messageAsBytes);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkWCrc16MessageDecoding() {
        String message = "Hello, world!";
        Message msg = new Message(10, 20, message);
        Packet pk = new Packet((byte)30, UnsignedLong.fromLongBits(40), msg);
        ByteBuffer buff = ByteBuffer.wrap(pk.toByteRepresentation());

        short expected = pk.getWCrc16Message();
        short actual = buff.getShort(16 + pk.getWLen());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkFullPacketDecoding() {
        String message = "Hello, world!";
        Message msg = new Message(10, 20, message);
        Packet expected = new Packet((byte)30, UnsignedLong.fromLongBits(40), msg);

        Packet actual = null;
        try {
            actual = new Packet(expected.toByteRepresentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
        expected.getMsg().decode();

        boolean equal = expected.canEqual(actual);
        Assertions.assertTrue(equal);
    }

    @Test
    public void checkBMagicChangeThrowsException() {
        String message = "Hello, world!";
        Message msg = new Message(10, 20, message);
        Packet expected = new Packet((byte)30, UnsignedLong.fromLongBits(40), msg);

        byte[] expectedAsBytes = expected.toByteRepresentation();
        expectedAsBytes[0] = 0x6; //manually injuring packet
        Assert.assertThrows(Exception.class, () -> {new Packet(expectedAsBytes);});
    }

    @Test
    public void checkBSrcChangeThrowsException() {
        String message = "Hello, world!";
        Message msg = new Message(10, 20, message);
        Packet expected = new Packet((byte)30, UnsignedLong.fromLongBits(40), msg);

        byte[] expectedAsBytes = expected.toByteRepresentation();
        expectedAsBytes[1] = 12; //manually injuring packet
        Assert.assertThrows(Exception.class, () -> {new Packet(expectedAsBytes);});
    }

    @Test
    public void checkBPktIdChangeThrowsException() {
        String message = "Hello, world!";
        Message msg = new Message(10, 20, message);
        Packet expected = new Packet((byte)30, UnsignedLong.fromLongBits(40), msg);

        byte[] expectedAsBytes = expected.toByteRepresentation();
        expectedAsBytes[5] = 12; //manually injuring packet
        Assert.assertThrows(Exception.class, () -> {new Packet(expectedAsBytes);});
    }

    @Test
    public void checkWLenChangeThrowsException() {
        String message = "Hello, world!";
        Message msg = new Message(10, 20, message);
        Packet expected = new Packet((byte)30, UnsignedLong.fromLongBits(40), msg);

        byte[] expectedAsBytes = expected.toByteRepresentation();
        expectedAsBytes[11] = 3; //manually injuring packet
        Assert.assertThrows(Exception.class, () -> {new Packet(expectedAsBytes);});
    }

    @Test
    public void checkWCrc16MetaChangeThrowsException() {
        String message = "Hello, world!";
        Message msg = new Message(10, 20, message);
        Packet expected = new Packet((byte)30, UnsignedLong.fromLongBits(40), msg);

        byte[] expectedAsBytes = expected.toByteRepresentation();
        expectedAsBytes[14] = 34; //manually injuring packet
        Assert.assertThrows(Exception.class, () -> {new Packet(expectedAsBytes);});
    }

    @Test
    public void checkCTypeChangeThrowsException() {
        String message = "Hello, world!";
        Message msg = new Message(10, 20, message);
        Packet expected = new Packet((byte)30, UnsignedLong.fromLongBits(40), msg);

        byte[] expectedAsBytes = expected.toByteRepresentation();
        expectedAsBytes[18] = 89; //manually injuring packet
        Assert.assertThrows(Exception.class, () -> {new Packet(expectedAsBytes);});
    }

    @Test
    public void checkBUserIdChangeThrowsException() {
        String message = "Hello, world!";
        Message msg = new Message(10, 20, message);
        Packet expected = new Packet((byte)30, UnsignedLong.fromLongBits(40), msg);

        byte[] expectedAsBytes = expected.toByteRepresentation();
        expectedAsBytes[21] = 37; //manually injuring packet
        Assert.assertThrows(Exception.class, () -> {new Packet(expectedAsBytes);});
    }

    @Test
    public void checkStringChangeThrowsException() {
        String message = "Hello, world!";
        Message msg = new Message(10, 20, message);
        Packet expected = new Packet((byte)30, UnsignedLong.fromLongBits(40), msg);

        byte[] expectedAsBytes = expected.toByteRepresentation();
        expectedAsBytes[25] = 94; //manually injuring packet
        Assert.assertThrows(Exception.class, () -> {new Packet(expectedAsBytes);});
    }

    @Test
    public void checkWCrc16MessageChangeThrowsException() {
        String message = "Hello, world!";
        Message msg = new Message(10, 20, message);
        Packet expected = new Packet((byte)30, UnsignedLong.fromLongBits(40), msg);

        byte[] expectedAsBytes = expected.toByteRepresentation();
        expectedAsBytes[Packet.META_PART_LENGTH + Short.BYTES + expected.getWLen() + 1] = 29; //manually injuring packet
        Assert.assertThrows(Exception.class, () -> {new Packet(expectedAsBytes);});
    }
}
