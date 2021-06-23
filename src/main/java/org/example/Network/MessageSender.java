package org.example.Network;

import org.example.Interfaces.Sender;

import java.util.concurrent.BlockingQueue;

public class MessageSender implements Sender {

    private BlockingQueue<byte[]> sendedMessages;

    public MessageSender(BlockingQueue<byte[]> sendedMessages) {
        this.sendedMessages = sendedMessages;
    }

    @Override
    public void sendMessage(byte[] packet) {
        try {
            sendedMessages.put(packet);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
