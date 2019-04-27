package au.com.tabcorp.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger LOGGER = Logger.getLogger(SpringSecurityConfig.class);
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		LOGGER.info("config auth");
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");

    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		LOGGER.info("config http");
		http
			.httpBasic()
			.and()
			.authorizeRequests()
			.antMatchers(HttpMethod.PUT, "/**").hasRole("USER")
			.antMatchers(HttpMethod.GET, "/stat/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/stat/**").hasRole("ADMIN")
			.and()
			.csrf().disable()
			.formLogin().disable();
	}
}
