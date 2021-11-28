package org.ukma.warehouse.Network.Utils;

import org.ukma.warehouse.Interfaces.Decryptor;
import org.ukma.warehouse.Models.Packet;

public class MessageDecryptor implements Decryptor {

    public MessageDecryptor() {}

    @Override
    public Packet decrypt(byte[] packet) {
        Packet currPacket = null;
        try {
            currPacket = new Packet(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currPacket;
    }

}
