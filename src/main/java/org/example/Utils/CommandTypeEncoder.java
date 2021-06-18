package org.example.Utils;

public class CommandTypeEncoder {

    public static final int
            PRODUCT = 1,
            CATEGORY = 2;

    public static final int
            CREATE           = 4,
            READ             = 8,
            UPDATE           = 16,
            DELETE           = 32,
            LIST_BY_CRITERIA = 64;

    public static final int
            CRITERIA_NAME = 128,
            CRITERIA_PRODUCER = 256,
            CRITERIA_CATEGORY_ID = 512,
            CRITERIA_DESCRIPTION = 1024,
            CRITERIA_AMOUNT = 2048,
            CRITERIA_PRICE = 4096,
            CRITERIA_CATEGORY_NAME = 8192;

    public static final int
            ORDER_ASCEND = 16384,
            ORDER_DESCEND = 32768;

    public static int createCommand(int entity, int command, int criteria, int order) {
        return entity ^ command ^ criteria ^ order;
    }

    public static boolean isProduct(int incomingCommandType) {
        return ((incomingCommandType & PRODUCT) == PRODUCT);
    }

    public static boolean isCategory(int incomingCommandType) {
        return ((incomingCommandType & CATEGORY) == CATEGORY);
    }
}
