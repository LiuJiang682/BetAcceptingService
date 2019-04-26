package au.com.tabcorp;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import au.com.tabcorp.converter.BetMessageConverter;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    private static final Logger LOGGER = Logger.getLogger(AppConfig.class);

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
    public FilterRegistrationBean registration(HiddenHttpMethodFilter filter) {
        LOGGER.info("registration called");
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        LOGGER.info("configMessageConverters called");
        converters.add(new BetMessageConverter());
    }
}
