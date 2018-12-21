package eu.vrtime.vrm.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "eu.vrtime.vrm.web")
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/webjars/**", "/img/**", "/css/**").addResourceLocations(
				"classpath:/META-INF/resources/webjars/", "classpath:/static/img/", "classpath:/static/css/");

	}

	// @Override
	// public void configureMessageConverters(List<HttpMessageConverter<?>>
	// converters) {
	// Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.xml();
	// builder.indentOutput(true);
	//
	// converters.add(new MappingJackson2XmlHttpMessageConverter(builder.build()));
	// }

}
