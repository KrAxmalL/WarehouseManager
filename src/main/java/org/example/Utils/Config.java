package org.example.Utils;

//used instead of .properties file
public class Config {

    public static final String dbDriverName = "org.h2.Driver";
    public static final String dbUrl = "jdbc:h2:mem:";

    public static final int HTTP_SERVER_PORT = 9000;

    public static final String TEST_LOGIN = "testLogin";
    public static final String TEST_PASSWORD = "testPass";

    public static final int BUFFER_SIZE = 2048;
}
