package com.genericinventory.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.genericinventory.application.shared.ConstantDateTimeService;
import com.genericinventory.application.shared.CurrentTimeDateTimeService;
import com.genericinventory.application.shared.DateTimeService;

@Configuration
@ComponentScan("com.genericinventory")
@Import(PersistenceContext.class)
public class ApplicationContext {

	@Profile(ApplicationProfiles.APPLICATION)
	@Configuration
	@PropertySource("classpath:application.properties")
	static class ApplicationProperties {
	}

	@Profile(ApplicationProfiles.APPLICATION)
	@Bean
	DateTimeService currentTimeDateTimeService() {
		return new CurrentTimeDateTimeService();
	}

	@Profile(ApplicationProfiles.TEST)
	@Configuration
	@PropertySource("classpath:application-test.properties")
	static class ApplicationTestProperties {
	}

	@Profile(ApplicationProfiles.TEST)
	@Bean
	DateTimeService constantDateTimeService() {
		return new ConstantDateTimeService();
	}

	@Profile(ApplicationProfiles.H2)
	@Configuration
	@PropertySource("classpath:application-testH2.properties")
	static class ApplicationTestH2Properties {
	}

	@Profile(ApplicationProfiles.H2)
	@Bean
	DateTimeService ConstantDateTimeService() {
		return new ConstantDateTimeService();
	}

	@Bean
	static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
