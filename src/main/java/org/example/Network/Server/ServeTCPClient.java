package org.example.Network.Server;

import org.example.Databases.CrudProductRepository;
import org.example.Databases.CrudUserRepository;
import org.example.Databases.DBConnection;
import org.example.Interfaces.Decryptor;
import org.example.Interfaces.Encryptor;
import org.example.Interfaces.Processor;
import org.example.Models.Message;
import org.example.Models.Packet;
import org.example.Network.Utils.MessageDecryptor;
import org.example.Network.Utils.MessageEncryptor;
import org.example.Network.Utils.MessageProcessor;
import org.example.Services.CategoryService;
import org.example.Services.ProductService;
import org.example.Services.UserService;
import org.example.Utils.CommandTypeEncoder;

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
    private UserService userService;

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
        categoryService = new CategoryService();
        userService = new UserService();

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
        else if(CommandTypeEncoder.isUser(command)) {
            return userService.processRequest(command, message.getMessage());
        }
        else {
            return "error";
        }
    }

    private void sendResponse(String data, Packet received) throws IOException {
        Message response = new Message(received.getMsg().getCType(), received.getMsg().getBUserId(), data);
        byte[] buffer = encryptor.encrypt(response, received.getBSrc());
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
                sendResponse(result, packet);
                System.out.println(new CrudUserRepository().getAll());
                //System.out.println(new CrudProductRepository().getAll());
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
