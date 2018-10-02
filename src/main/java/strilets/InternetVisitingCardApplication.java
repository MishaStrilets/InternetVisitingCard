/**
 * Application class for launch an application.
 * 
 * @author Misha Strilets
 * @version 1.0
 */
package strilets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class InternetVisitingCardApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(InternetVisitingCardApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(InternetVisitingCardApplication.class, args);

	}

}