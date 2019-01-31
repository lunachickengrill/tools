package eu.vrtime.springbootwicket;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringBootWicketApplication {

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder().sources(SpringBootWicketApplication.class).run(args);
	}

}
