package exceptions;


import java.io.Serial;

public class DomainException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public DomainException(String msg) {
        super(msg);
    }
}
