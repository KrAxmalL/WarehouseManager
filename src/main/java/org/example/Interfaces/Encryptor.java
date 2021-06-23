package org.example.Interfaces;

import org.example.Models.Message;

public interface Encryptor {

    public byte[] encrypt(Message message);
}
