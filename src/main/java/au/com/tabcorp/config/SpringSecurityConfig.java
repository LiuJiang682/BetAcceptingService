package au.com.tabcorp.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import au.com.tabcorp.error.handler.TabcorpAccessDeniedHandler;
import au.com.tabcorp.security.AuthenticationSuccessHandler;
import au.com.tabcorp.security.RestAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("au.com.tabcorp.security")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger LOGGER = Logger.getLogger(SpringSecurityConfig.class);
	
	@Autowired
	private TabcorpAccessDeniedHandler tabcorpAccessDeniedHandler;
	
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	private SimpleUrlAuthenticationFailureHandler authenticationFailureHandler
		= new SimpleUrlAuthenticationFailureHandler();
	
	public SpringSecurityConfig() {
		super();
		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
	}
	
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
		/**
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
			*/
		http
			.csrf().disable()
			.authorizeRequests()
			.and()
			.exceptionHandling()
			.accessDeniedHandler(tabcorpAccessDeniedHandler)
			.authenticationEntryPoint(restAuthenticationEntryPoint)
			.and()
			.authorizeRequests()
			.antMatchers(HttpMethod.PUT, "/**").hasRole("USER")
			.antMatchers(HttpMethod.GET, "/stat/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/stat/**").hasRole("ADMIN")
			.and()
			.formLogin()
			.successHandler(authenticationSuccessHandler)
			.failureHandler(authenticationFailureHandler)
			.and()
			.httpBasic()
			.and()
			.logout();
	}
}
