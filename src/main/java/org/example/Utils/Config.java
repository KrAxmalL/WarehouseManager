package org.example.Utils;

import java.awt.*;

//used instead of .properties file
public class Config {

    public static final String dbDriverName = "org.h2.Driver";
    public static final String dbUrl = "jdbc:h2:mem:";

    public static final int SERVER_PORT = 9000;
    public static final String LOCALHOST = "localhost";

    public static final int THREAD_NUMBER = 4;

    public static final String TEST_LOGIN = "testLogin";
    public static final String TEST_PASSWORD = "testPass";

    public static final int MAIN_WIDTH = 800;
    public static final int MAIN_HEIGHT = 600;

    public static final int BUFFER_SIZE = 2048;
}
