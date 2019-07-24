package eu.vrtime.bootwicketappthree;

import javax.servlet.Filter;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.protocol.http.WicketFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BootWicketApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(BootWicketApplication.class, args);
		checkForBean(ctx);
	}

	public static void checkForBean(ApplicationContext ctx) {

		System.out.println("BEAN EXISTS: " + ctx.containsBean("authService"));

	}

}
