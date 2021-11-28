package org.ukma.warehouse.Interfaces;

import org.ukma.warehouse.Models.Message;

public interface Encryptor {

    public byte[] encrypt(Message message, byte clientNumber);
}
