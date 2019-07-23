package eu.vrtime.bootwicketappthree.config;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.spring.SpringWebApplicationFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import eu.vrtime.bootwicketappthree.WicketApplication;

@Configuration
@Import({PersistenceConfig.class})
public class SpringConfig {
	
//	@Bean
//	public Filter getWicketFilter() {
//		WicketApplication webApplication = new WicketApplication();
//		webApplication.setConfigurationType(RuntimeConfigurationType.DEVELOPMENT);
//		WicketFilter filter = new WicketFilter(webApplication);
//		filter.setFilterPath("/");
//		return filter;
//
//	}
	
	@Bean
	public FilterRegistrationBean<WicketFilter> wicketFilterRegistration(){
		WicketApplication webApplication = new WicketApplication();
		webApplication.setConfigurationType(RuntimeConfigurationType.DEVELOPMENT);
		
		WicketFilter filter = new WicketFilter(webApplication);
		filter.setFilterPath("/");
		
		FilterRegistrationBean<WicketFilter> registration = new FilterRegistrationBean<>();
		registration.setFilter(filter);
		registration.addInitParameter(WicketFilter.APP_FACT_PARAM, SpringWebApplicationFactory.class.getName());
		registration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD);
		
		return registration;
	}

}
