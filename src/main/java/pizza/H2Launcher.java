package pizza;

import org.h2.tools.Server;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

@Component
public class H2Launcher implements Runnable {

    @PostConstruct
    @Override
    public void run() {
        System.out.println("Launching H2 TCP Server");
        try {
            Server.createTcpServer("-tcp", "-ifNotExists", "-tcpPort", "9092").start();
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to launch H2 TCP server", e);
        }
    }
}
