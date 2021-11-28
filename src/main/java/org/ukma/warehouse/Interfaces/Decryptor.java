package org.ukma.warehouse.Interfaces;

import org.ukma.warehouse.Models.Packet;

public interface Decryptor {

    public Packet decrypt(byte[] packet);
}
