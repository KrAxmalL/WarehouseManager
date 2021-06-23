package org.example.Network;

import org.example.Databases.CrudProductRepository;
import org.example.Databases.DBConnection;
import org.example.Interfaces.Decryptor;
import org.example.Interfaces.Encryptor;
import org.example.Interfaces.Processor;
import org.example.Models.Message;
import org.example.Models.Packet;
import org.example.Models.Product;
import org.example.Network.MessageDecryptor;
import org.example.Network.MessageEncryptor;
import org.example.Network.MessageProcessor;
import org.example.Services.CategoryService;
import org.example.Services.ProductService;
import org.example.Utils.CommandTypeEncoder;
import org.example.Utils.Config;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ServeTCPClient extends Thread {

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    private Decryptor decryptor;
    private Processor processor;
    private Encryptor encryptor;

    private ProductService productService;
    private CategoryService categoryService;

    private static volatile int serverThreadsCounter = 0;
    private int id;

    public ServeTCPClient(Socket socket) {
        this.socket = socket;
        try {
            input = new DataInputStream(this.socket.getInputStream());
            output = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.decryptor = new MessageDecryptor();
        this.processor = new MessageProcessor();
        this.encryptor = new MessageEncryptor();

        productService = new ProductService();

        id = serverThreadsCounter++;

        start();
    }

    private Packet getRequest() throws IOException {
        int size = input.readInt();
        byte[] buffer = new byte[size];
        input.readFully(buffer);
        Packet packet = decryptor.decrypt(buffer);
        System.out.println("Server thread number " + id + " got message: " + packet.getMsg().toString()
                           + " from client " + packet.getMsg().getBUserId());
        return packet;
    }

    private String processPacket(Packet packet) {
        Message message = packet.getMsg();
        int command = message.getCType();
        if(CommandTypeEncoder.isProduct(command)) {
            return productService.processRequest(command, message.getMessage());
        }
        else if(CommandTypeEncoder.isCategory(command)) {
            return categoryService.processRequest(command, message.getMessage());
        }
        else {
            return "error";
        }
    }

    private void sendResponse(String data) throws IOException {
        Message response = new Message(-1, -1, data);
        byte[] buffer = encryptor.encrypt(response);
        output.writeInt(buffer.length);
        output.write(buffer);
    }

    @Override
    public void run() {
        try {
            byte[] buffer = null;
            System.out.println(new CrudProductRepository().getAll());
            System.out.println(DBConnection.getInstance().getConnection());
            while(true) {
                Packet packet = getRequest();
                String result = processPacket(packet);
                System.out.println("Request result: " + result);
                sendResponse(result);
                System.out.println(new CrudProductRepository().getAll());
            }
        } catch (IOException | SQLException e) {
            System.out.println("IO Exception on server side\n");
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Server socket " + id + " wasn't closed!");
            }
        }
    }
}
