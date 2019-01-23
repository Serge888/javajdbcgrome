package lesson4.hw.exception;

public class AccessForbiddenException extends BadRequestException {

    public AccessForbiddenException(String message) {
        super(message);
    }
}
