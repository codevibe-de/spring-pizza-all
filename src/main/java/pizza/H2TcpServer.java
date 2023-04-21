package pizza;

import org.h2.tools.Server;
import org.springframework.core.NestedExceptionUtils;

import java.net.BindException;
import java.sql.SQLException;

/**
 * Provides support for starting and stopping an H2 database server instance. This instance
 * can be reached by TCP connections, which makes connecting to it from your IDE (e.g.
 * Intellij Database Tools) easy.
 */
public class H2TcpServer {

    private Server server;

    public void start() {
        System.out.println("Starting H2 TCP Server");
        try {
            server = Server.createTcpServer("-tcp", "-ifNotExists", "-tcpPort", "9092").start();
        } catch (SQLException e) {
            var rootCause = NestedExceptionUtils.getRootCause(e);
            if (rootCause instanceof BindException) {
                System.out.println("Server seems to be running already (maybe in some other context?)");
            }
            else {
                throw new IllegalStateException("Failed to start H2 TCP server", e);
            }
        }
    }

    public void stop() {
        if (this.server != null) {
            System.out.println("Stopping H2 TCP Server");
            this.server.stop();
        }
    }
}
