package database;

import java.io.Serial;

public class DbException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 4379342274719071891L;

    public DbException(String message) {
        super(message);
    }
}
