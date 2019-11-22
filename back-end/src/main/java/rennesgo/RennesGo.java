package rennesgo;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "rennesgo.auth", "rennesgo.controller", "rennesgo.data"})
public class RennesGo {
	public static void main(final String[] args) {
		final SpringApplication app = new SpringApplication(RennesGo.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "4444"));
		app.run(args);
	}
}
