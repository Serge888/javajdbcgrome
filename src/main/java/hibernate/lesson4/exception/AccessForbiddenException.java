package hibernate.lesson4.exception;

public class AccessForbiddenException extends BadRequestException {

    public AccessForbiddenException(String message) {
        super(message);
    }
}
