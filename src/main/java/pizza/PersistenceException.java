package pizza;

import java.sql.SQLException;

public class PersistenceException extends RuntimeException {

    public PersistenceException(SQLException e) {
        super(e.getMessage(), e);
    }

    public PersistenceException(String msg) {
        super(msg);
    }
}
