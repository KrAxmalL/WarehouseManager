package org.example.Network;

import com.google.common.primitives.UnsignedLong;
import org.example.Interfaces.Encryptor;
import org.example.Models.Message;
import org.example.Models.Packet;

public class MessageEncryptor implements Encryptor {

    //Random value
    private static final byte CLIENT_NUMBER = 127;

    private static volatile long packetNumber = 0;

    public MessageEncryptor() {}

    @Override
    public byte[] encrypt(Message message) {
        Packet packet = new Packet(CLIENT_NUMBER, UnsignedLong.valueOf(packetNumber++), message);
        return packet.toByteRepresentation();
    }
}
