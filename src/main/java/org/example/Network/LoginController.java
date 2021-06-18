package org.example.Network;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.example.Databases.CrudUserRepository;
import org.example.Models.User;
import org.example.Services.UserService;
import org.example.Utils.Config;
import org.example.Utils.JWTController;
import org.example.Utils.MyCipher;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Base64;

public class LoginController {

    public static final String LOGIN_PATH = "/login";

    public static void serve(HttpExchange exchange) {

        String requestMethod = exchange.getRequestMethod();
        String requestUri = exchange.getRequestURI().toString();
        String subPath = requestUri.substring(LOGIN_PATH.length());

        int responseCode = 0;
        byte[] responseAsBytes = null;

        if(!subPath.equals("")) {
            responseCode = 404;
            String errorMessage = "Not found";
            responseAsBytes = errorMessage.getBytes();
        }
        else {
            if (requestMethod.equals("POST")) {
                InputStream in = exchange.getRequestBody();
                String input = "";
                byte[] inputBytes = new byte[Config.BUFFER_SIZE];

                try {
                    in.read(inputBytes);
                    input = new String(inputBytes);
                    User user = UserService.parseUserFromJson(input);

                    boolean isValid = UserService.validateUser(user);

                    if (isValid) {
                        Headers responseHeaders = exchange.getResponseHeaders();
                        responseHeaders.add("Content-Type", "application/json");
                        String jwt = JWTController.createJWT((user.getLogin()), JWTController.ISSUER,
                                (user.getPassword()));
                        responseCode = 200;
                        String responseJson = "{ \"token\": " + "\"" + jwt + "\"}";
                        responseAsBytes = responseJson.getBytes();
                    } else {
                        responseCode = 401;
                        String errorMessage = "Invalid login or password";
                        responseAsBytes = errorMessage.getBytes();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    responseCode = 400;
                    String errorMessage = "Invalid input for login or password";
                    responseAsBytes = errorMessage.getBytes();
                }
            } else {
                responseCode = 405;
                String errorMessage = "Invalid method. Only post is allowed for login";
                responseAsBytes = errorMessage.getBytes();
            }
        }

        try {
            exchange.sendResponseHeaders(responseCode, responseAsBytes.length);
            OutputStream out = exchange.getResponseBody();
            if(responseAsBytes.length > 0) {
                out.write(responseAsBytes);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
