package org.example.Models;

import lombok.Data;
import lombok.ToString;
import org.example.Utils.MyCipher;

import java.nio.ByteBuffer;

@Data
public class Message {

    @ToString.Exclude
    public static final int TYPE_AND_ID_LENGTH = Integer.BYTES + Integer.BYTES;

    @ToString.Exclude
    private final MyCipher CIPHER = new MyCipher();

    private Integer cType;
    private Integer bUserId;
    private String message;

    public Message() {}

    public Message(Integer cType, Integer bUserId, String message) {
        this.cType = cType;
        this.bUserId = bUserId;
        this.message = message;
    }

    public Message(byte[] encodedMessage) {
        ByteBuffer buff = ByteBuffer.wrap(encodedMessage);

        this.cType = buff.getInt();
        this.bUserId = buff.getInt();

        byte[] messageBytes = new byte[encodedMessage.length - TYPE_AND_ID_LENGTH];
        buff.get(messageBytes);
        this.message = new String(messageBytes);
    }

    public byte[] toByteRepresentation() {
        return ByteBuffer.allocate(getFullLength()).putInt(cType)
                .putInt(bUserId).put(message.getBytes()).array();
    }

    public int getFullLength() {
        return TYPE_AND_ID_LENGTH + getMessageLength();
    }

    public int getMessageLength() {
        return message.length();
    }

    public void encode() {
        message = CIPHER.encode(message);
    }

    public void decode() {
        message = CIPHER.decode(message);
    }
}
