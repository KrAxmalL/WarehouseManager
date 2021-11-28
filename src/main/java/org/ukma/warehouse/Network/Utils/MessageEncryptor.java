package org.ukma.warehouse.Network.Utils;

import com.google.common.primitives.UnsignedLong;
import org.ukma.warehouse.Interfaces.Encryptor;
import org.ukma.warehouse.Models.Message;
import org.ukma.warehouse.Models.Packet;

public class MessageEncryptor implements Encryptor {

    private static volatile long packetNumber = 0;

    public MessageEncryptor() {}

    @Override
    public byte[] encrypt(Message message, byte clientNumber) {
        Packet packet = new Packet(clientNumber, UnsignedLong.valueOf(packetNumber++), message);
        return packet.toByteRepresentation();
    }
}
