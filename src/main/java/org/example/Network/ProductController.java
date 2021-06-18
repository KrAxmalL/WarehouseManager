package org.example.Network;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.example.Databases.CrudCategoryRepository;
import org.example.Databases.CrudProductRepository;
import org.example.Models.Product;
import org.example.Services.ProductService;
import org.example.Utils.Config;
import org.example.Utils.JWTController;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class ProductController {

    public static final String PRODUCT_PATH = "/api/good";

    private static CrudProductRepository products = new CrudProductRepository();
    private static CrudCategoryRepository categories = new CrudCategoryRepository();

    public static void serve(HttpExchange exchange) {

        String requestMethod = exchange.getRequestMethod();
        String requestUri = exchange.getRequestURI().toString();
        String subPath = requestUri.substring(PRODUCT_PATH.length());

        int responseCode = 0;
        byte[] responseAsBytes = null;

        Headers requestHeaders = exchange.getRequestHeaders();
        List<String> authValues = requestHeaders.get("Authorization");
        boolean isValid = false;
        if((authValues == null) || (authValues.size() == 0)) {
            isValid = false;
        }
        else {
            String token = authValues.get(authValues.size()-1);
            isValid = JWTController.validateToken(token);
        }

        if(isValid) {
            if (subPath.equals("") && requestMethod.equals("PUT")) {
                servePut(exchange);
                return;
            } else {
                try {
                    int productId = Integer.parseInt(subPath.replaceFirst("/", ""));

                    if (requestMethod.equals("GET")) {
                        serveGet(exchange, productId);
                        return;
                    } else if (requestMethod.equals("POST")) {
                        servePost(exchange, productId);
                        return;
                    } else if (requestMethod.equals("DELETE")) {
                        serveDelete(exchange, productId);
                        return;
                    } else {
                        responseCode = 405;
                        String errorMessage = "Only GET, PUT and POST allowed for this request.";
                        responseAsBytes = errorMessage.getBytes();
                    }
                } catch (NumberFormatException ex) {
                    responseCode = 400;
                    String errorMessage = "Invalid id parameter. Can't parse to integer.";
                    responseAsBytes = errorMessage.getBytes();
                }
            }
        }
        else {
            responseCode = 403;
            String errorMessage = "Invalid JWT token.";
            responseAsBytes = errorMessage.getBytes();
        }

        try {
            OutputStream out = exchange.getResponseBody();
            exchange.sendResponseHeaders(responseCode, responseAsBytes.length);
            if(responseAsBytes.length > 0) {
                out.write(responseAsBytes);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void serveGet(HttpExchange exchange, int productId) {
        Headers responseHeaders = exchange.getResponseHeaders();
        int responseCode = 0;
        byte[] responseAsBytes = null;

        Product product = products.getProduct(productId);
        if(product == null) {
            responseCode = 404;
            String errorMessage = "Such product doesn't exist";
            responseAsBytes = errorMessage.getBytes();
        }
        else {
            responseCode = 200;
            String productJson = product.toJson();
            responseAsBytes = productJson.getBytes();
        }

        try {
            responseHeaders.add("Content-Type", "application/json");
            OutputStream out = exchange.getResponseBody();
            exchange.sendResponseHeaders(responseCode, responseAsBytes.length);
            if(responseAsBytes.length > 0) {
                out.write(responseAsBytes);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void servePut(HttpExchange exchange) {
        Headers responseHeaders = exchange.getResponseHeaders();
        int responseCode = 0;
        byte[] responseAsBytes = null;

        InputStream in = exchange.getRequestBody();
        byte[] input = new byte[Config.BUFFER_SIZE];
        try {
            in.read(input);
            String productToAddAsJson = new String(input);
            Product product = ProductService.parseProductFromJson(productToAddAsJson);
            boolean isValid = ProductService.validateProduct(product);

            if(isValid) {
                products.addProduct(product);
                responseCode = 201;
                String responseMessage = "{\"id\":" + products.getProductByName(product.getName()).getId() + "}";
                responseHeaders.add("Content-Type", "application/json");
                responseAsBytes = responseMessage.getBytes();
            }
            else {
                responseCode = 409;
                String errorMessage = "Wrong data for product or such product already exists. Check your input and try again";
                responseAsBytes = errorMessage.getBytes();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            responseCode = 400;
            String errorMessage = "Bad product json.";
            responseAsBytes = errorMessage.getBytes();
        }

        try {
            OutputStream out = exchange.getResponseBody();
            exchange.sendResponseHeaders(responseCode, responseAsBytes.length);
            if(responseAsBytes.length > 0) {
                out.write(responseAsBytes);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void servePost(HttpExchange exchange, int productId) {
        Headers responseHeaders = exchange.getResponseHeaders();
        int responseCode = 0;
        byte[] responseAsBytes = null;

        InputStream in = exchange.getRequestBody();
        byte[] input = new byte[Config.BUFFER_SIZE];
        try {
            Product productFromDb = products.getProduct(productId);
            if(productFromDb == null) {
                responseCode = 404;
                String errorMessage = "Such product doesn't exist";
                responseAsBytes = errorMessage.getBytes();
            }
            else {
                in.read(input);
                String productToAddAsJson = new String(input);

                Product product = ProductService.parseProductFromJson(productToAddAsJson);
                product.setId(productId);

                boolean isValid = ProductService.validateProduct(product);
                if (isValid) {
                    products.updateProduct(product);
                    responseCode = 204;
                    responseAsBytes = new byte[0];
                } else {
                    responseCode = 409;
                    String errorMessage = "Wrong data for product. Check your input and try again";
                    responseAsBytes = errorMessage.getBytes();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            responseCode = 400;
            String errorMessage = "Bad product json.";
            responseAsBytes = errorMessage.getBytes();
        }

        try {
            OutputStream out = exchange.getResponseBody();
            exchange.sendResponseHeaders(responseCode, responseAsBytes.length);
            if(responseAsBytes.length > 0) {
                out.write(responseAsBytes);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void serveDelete(HttpExchange exchange, int productId) {
        Headers responseHeaders = exchange.getResponseHeaders();
        int responseCode = 0;
        byte[] responseAsBytes = null;

        boolean deleted = products.deleteProduct(productId);
        if(deleted) {
            responseCode = 204;
            responseAsBytes = new byte[0];
        }
        else {
            responseCode = 404;
            String errorMessage = "Products with given id doesn't exist.";
            responseAsBytes = errorMessage.getBytes();
        }

        try {
            OutputStream out = exchange.getResponseBody();
            exchange.sendResponseHeaders(responseCode, responseAsBytes.length);
            if(responseAsBytes.length > 0) {
                out.write(responseAsBytes);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
