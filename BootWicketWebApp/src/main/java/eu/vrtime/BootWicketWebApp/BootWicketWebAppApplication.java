package eu.vrtime.BootWicketWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BootWicketWebAppApplication {

	public static void main(String[] args) {
//		SpringApplication.run(BootWicketWebAppApplication.class, args);
		new SpringApplicationBuilder().sources(BootWicketWebAppApplication.class);
	}

}

