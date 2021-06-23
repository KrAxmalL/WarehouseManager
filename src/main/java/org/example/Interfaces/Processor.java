package org.example.Interfaces;

import org.example.Models.Message;
import org.example.Models.Packet;

public interface Processor {

    public Message process(Packet packet);
}
