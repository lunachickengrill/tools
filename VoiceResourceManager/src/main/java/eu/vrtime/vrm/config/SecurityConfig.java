package eu.vrtime.vrm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;

	// @SuppressWarnings("deprecation")
	// public void configure(AuthenticationManagerBuilder builder) throws Exception
	// {
	// builder.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("dev")
	// .password("lgs123").roles("ADMIN");
	//
	// }
	//
	// protected void configure(HttpSecurity http) throws Exception {
	// http.httpBasic().and()
	// .authorizeRequests()
	// .antMatchers("/api/rest/**").hasRole("ADMIN").antMatchers("/**")
	// .hasRole("ADMIN")
	// .and().csrf()
	// .disable().headers()
	// .frameOptions().disable();
	//
	// }

	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("dev").password("{noop}lgs123").roles("ADMIN").build());
		manager.createUser(User.withUsername("user").password("{noop}user").roles("USER").build());

		return manager;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// http.authorizeRequests().antMatchers("/","/css/**", "/img/**",
		// "/webjars/**").permitAll()
		// .antMatchers("/user/**").hasRole("USER").anyRequest().authenticated().and().formLogin()
		// .loginPage("/login").permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true)
		// .logoutRequestMatcher(new
		// AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
		// .permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()
		// .authorizeRequests().antMatchers("/api/rest/**","/api/test/**").hasRole("ADMIN").and().csrf().disable().headers()
		// .frameOptions().disable();

//		http.httpBasic().and().authorizeRequests().antMatchers("/api/rest/**","/api/test/**").hasRole("ADMIN").antMatchers("/**")
//				.hasRole("ADMIN").and().csrf().disable().headers().frameOptions().disable().and().authorizeRequests()
//				.antMatchers("/", "/css/**", "/img/**", "/webjars/**").permitAll().antMatchers("/user/**")
//				.hasRole("USER").anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and()
//				.logout().invalidateHttpSession(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//				.logoutSuccessUrl("/login?logout").permitAll().and().exceptionHandling()
//				.accessDeniedHandler(accessDeniedHandler).and().csrf().disable().headers().frameOptions().disable();

		
		http.httpBasic().and().authorizeRequests().antMatchers("/api/rest/**","/api/test/**","/main").hasRole("ADMIN").antMatchers("/**")
		.hasRole("ADMIN").and().csrf().disable().headers().frameOptions().disable();
	}
}