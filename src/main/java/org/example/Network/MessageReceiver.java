package org.example.Network;

import org.example.Interfaces.Receiver;

import java.util.concurrent.BlockingQueue;

public class MessageReceiver implements Receiver {

    private BlockingQueue<byte[]> receivedMessages;

    public MessageReceiver(BlockingQueue<byte[]> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    @Override
    public byte[] receivePacket() {
        byte[] currPacket = new byte[0];
        try {
            currPacket = receivedMessages.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return currPacket;
    }
}
