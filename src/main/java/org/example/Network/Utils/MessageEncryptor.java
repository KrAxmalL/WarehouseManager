package org.example.Network.Utils;

import com.google.common.primitives.UnsignedLong;
import org.example.Interfaces.Encryptor;
import org.example.Models.Message;
import org.example.Models.Packet;

public class MessageEncryptor implements Encryptor {

    private static volatile long packetNumber = 0;

    public MessageEncryptor() {}

    @Override
    public byte[] encrypt(Message message, byte clientNumber) {
        Packet packet = new Packet(clientNumber, UnsignedLong.valueOf(packetNumber++), message);
        return packet.toByteRepresentation();
    }
}
