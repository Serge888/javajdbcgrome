package hibernate.lesson4.exception;

import org.hibernate.HibernateException;

public class InternalServerException extends HibernateException {
    public InternalServerException(String message) {
        super(message);
    }
}
