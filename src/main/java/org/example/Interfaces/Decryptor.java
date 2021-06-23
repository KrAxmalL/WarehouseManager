package org.example.Interfaces;

import org.example.Models.Packet;

public interface Decryptor {

    public Packet decrypt(byte[] packet);
}
