package org.thiki.datavalidator.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @see https://github.com/alibaba/fastjson/wiki/SerializeFilter
 */
@Configuration
public class WebSpringConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter  = new FastJsonHttpMessageConverter();

        converter.setSupportedMediaTypes(new ArrayList<MediaType>() {
            {
                this.add(MediaType.APPLICATION_JSON);
                this.add(MediaType.APPLICATION_JSON_UTF8);
            }
        });
        converters.add(converter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
//        registry.addInterceptor(new MappingsInterceptor()).addPathPatterns("/mappings");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

}
