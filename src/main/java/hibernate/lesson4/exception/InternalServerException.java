package hibernate.lesson4.exception;

import java.io.IOException;

public class InternalServerException extends IOException {
    public InternalServerException(String message) {
        super(message);
    }
}
