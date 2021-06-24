package org.example.Network.Utils;

import org.example.Interfaces.Processor;
import org.example.Models.Message;
import org.example.Models.Packet;


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
