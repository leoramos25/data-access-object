package database;

import java.io.Serial;

public class DbIntegrityException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -6919808437571198298L;

    public DbIntegrityException(String message) {
        super(message);
    }
}
