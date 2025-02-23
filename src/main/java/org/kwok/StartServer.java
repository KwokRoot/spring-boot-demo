package org.kwok;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class StartServer {

    public static void main(String[] args) {
        SpringApplication.run(StartServer.class, args);
    }

}
