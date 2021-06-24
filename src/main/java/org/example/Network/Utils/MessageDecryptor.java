package org.example.Network.Utils;

import org.example.Interfaces.Decryptor;
import org.example.Models.Packet;

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
