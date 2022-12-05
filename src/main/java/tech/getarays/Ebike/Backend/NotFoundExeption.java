package tech.getarays.Ebike.Backend;

public class NotFoundExeption extends RuntimeException {
    public NotFoundExeption(String message) {
        super(message);
    }
}
