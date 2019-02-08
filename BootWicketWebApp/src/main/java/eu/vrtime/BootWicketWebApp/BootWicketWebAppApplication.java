package eu.vrtime.BootWicketWebApp;

import javax.servlet.Filter;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.protocol.http.WicketFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BootWicketWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootWicketWebAppApplication.class, args);
//		new SpringApplicationBuilder().sources(BootWicketWebAppApplication.class);
	}
	
	@Bean
	public Filter getWicketFilter() {
		WicketApplication webApplication=new WicketApplication();
		webApplication.setConfigurationType(RuntimeConfigurationType.DEPLOYMENT);
		WicketFilter filter = new WicketFilter(webApplication);
		filter.setFilterPath("/");
		return filter;
		
	}

}

