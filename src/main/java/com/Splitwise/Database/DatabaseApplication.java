package com.Splitwise.Database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application entry point.
 * <p>
 * This class boots the Spring application context and starts the embedded web
 * server. No application logic lives here — controllers, configuration, and
 * beans are defined in other packages under com.Splitwise.Database.
 */
@SpringBootApplication
public class DatabaseApplication {

	/**
	 * Application entry point. Delegates to Spring Boot which initializes
	 * the application context and starts the embedded server.
	 *
	 * @param args standard Java main args (unused)
	 */
	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
	}

}
