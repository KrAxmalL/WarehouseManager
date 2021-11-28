package org.ukma.warehouse.Network.Utils;

import org.ukma.warehouse.Interfaces.Processor;
import org.ukma.warehouse.Models.Message;
import org.ukma.warehouse.Models.Packet;


public class MessageProcessor implements Processor {

    //random values(prekols)
    private static final int RESPONSE_COMMAND = 404;
    private static final int RESPONSE_USER_ID = 1337;

    public MessageProcessor() {}

    @Override
    public Message process(Packet packet) {
        Message response = new Message(RESPONSE_COMMAND, RESPONSE_USER_ID, "Ok");
        return response;
    }

}
