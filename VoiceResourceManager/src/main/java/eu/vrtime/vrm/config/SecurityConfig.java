package eu.vrtime.vrm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@SuppressWarnings("deprecation")
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("user")
				.password("lgs123").roles("USER").and().withUser("dev").password("lgs123").roles("USER", "ADMIN");
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().antMatchers("/api/rest/**").hasRole("USER").antMatchers("/**")
				.hasRole("ADMIN").and().csrf().disable().headers().frameOptions().disable();
	}
}
