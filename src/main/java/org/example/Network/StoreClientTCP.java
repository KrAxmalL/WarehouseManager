package org.example.Network;

import lombok.SneakyThrows;
import org.example.Controllers.Main.MainController;
import org.example.Databases.DBConnection;
import org.example.Interfaces.Decryptor;
import org.example.Interfaces.Encryptor;
import org.example.Interfaces.Processor;
import org.example.Models.Message;
import org.example.Models.Packet;
import org.example.Network.MessageDecryptor;
import org.example.Network.MessageEncryptor;
import org.example.Network.MessageProcessor;
import org.example.Utils.Config;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class StoreClientTCP extends Thread {

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    private Decryptor decryptor;
    private Processor processor;
    private Encryptor encryptor;

    private static volatile int clientCounter = 0;
    private int id = clientCounter++;

    private MainController mainController;

    public StoreClientTCP(InetAddress address) {
        System.out.println("Making client " + id);
        try {
            socket = new Socket(address, Config.SERVER_PORT);
        }
        catch (IOException e) {
            System.err.println("Socket failed");
        }

        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

            this.decryptor = new MessageDecryptor();
            this.processor = new MessageProcessor();
            this.encryptor = new MessageEncryptor();

            start();
        }
        catch (IOException e) {
            try {
                socket.close();
            }
            catch (IOException e2) {
                System.err.println("Client socket not closed!");
            }
        }
    }

    public void sendRequest(int command, String message) throws IOException {
        Message msg = new Message(command, id, message);
        byte[] buffer = encryptor.encrypt(msg);
        output.writeInt(buffer.length);
        output.write(buffer);
        System.out.println("Sended request! " + msg);
    }

    public Message getResponse() throws IOException {
        int size = input.readInt();
        byte[] buffer = new byte[size];
        input.readFully(buffer);
        Packet packet = decryptor.decrypt(buffer);
        System.out.println("Client " + id + " got response from server: " + packet.getMsg().toString());
        return packet.getMsg();
    }

    @SneakyThrows
    public void run() {

    }
}
