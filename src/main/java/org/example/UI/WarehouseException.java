package org.example.UI;

public class WarehouseException extends Exception {

    public WarehouseException() {}
    public WarehouseException(String msg) {super(msg);}

    // TODO: make all these exceptions create error windows
    public static class NotUniqueNameException extends WarehouseException {
        public NotUniqueNameException(String object) {
            super("Can't set new name for " + object + ". " + object.substring(0, 1).toUpperCase() + object.substring(1) + " with such name already exists!");
        }
    }

    public static class WriteOffQualityExceedingException extends WarehouseException {
        public WriteOffQualityExceedingException() {
            super("Write off quantity exceeds actual stock quantity!");
        }
    }
}
