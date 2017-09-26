package org.thiki.datavalidator.config;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.http.HttpStatus;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thiki.datavalidator.ex.AssertionException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by joeaniu on 10/17/16.
 */
@Configuration
@PropertySources(value = { @PropertySource("system.properties") })
public class ApplicationContextConfig {

    
    /**
     * add thread scope to support multi data sources in none-request-scope scenario.
     * @return
     */
    @Bean
    public CustomScopeConfigurer customScopeConfigurer(){
        CustomScopeConfigurer bean = new CustomScopeConfigurer();
        Map<String, Object> scopes = new HashMap<>();
        scopes.put("thread", new SimpleThreadScope());
        bean.setScopes(scopes);
        return bean;
    }
    
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.setSessionTimeout(50, TimeUnit.MINUTES);
        return factory;
    }

    @Bean
    public WebMvcConfigurer mvcConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization", "productId", "serverId", "adminUserId")
                        .allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS")
                        .allowCredentials(true)
                        .maxAge(3600)
                        ;
            }
        };
    }



    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18N/message");
        messageSource.setDefaultEncoding("utf-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator(){
        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
        factory.setValidationMessageSource(messageSource());
        return factory;
    }

    @Bean
    public EmbeddedServletContainerCustomizer customizer(){
        return  container -> {
            container.addErrorPages(new ErrorPage(AssertionException.class, "/error/businessException"));
            container.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/businessException"));
            container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/error/invalidParamsException"));
        };
    }


}
