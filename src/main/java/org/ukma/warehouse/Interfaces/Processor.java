package org.ukma.warehouse.Interfaces;

import org.ukma.warehouse.Models.Message;
import org.ukma.warehouse.Models.Packet;

public interface Processor {

    public Message process(Packet packet);
}
