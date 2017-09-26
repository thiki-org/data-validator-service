package org.thiki.datavalidator.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * DataSource is defined in class DynamicDataSource
 */
@Configuration
@MapperScan("org.thiki.datavalidator")
public class DatabaseConfig {

    @Bean
    public SqlSessionFactory templateSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(templateDataSource());

        org.apache.ibatis.session.Configuration mybatisConfig= new org.apache.ibatis.session.Configuration();

        mybatisConfig.setMapUnderscoreToCamelCase(true);

        registerTypeHandlers(mybatisConfig.getTypeHandlerRegistry());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        sqlSessionFactoryBuilder.build(mybatisConfig);

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:org/thiki/datavalidator/repo/mybatis/**/*.xml"));
        sqlSessionFactoryBean.setSqlSessionFactoryBuilder(sqlSessionFactoryBuilder);
        sqlSessionFactoryBean.setConfiguration(mybatisConfig);
        return sqlSessionFactoryBean.getObject();
    }

    private void registerTypeHandlers(TypeHandlerRegistry registry) {
    }

    @Bean
    public PlatformTransactionManager templateTransactionManager() {
        return new DataSourceTransactionManager(templateDataSource());
    }

    @Bean
    @Primary
    @ConfigurationProperties("template.datasource")
    public DataSource templateDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("server.datasource")
    public DataSource serverDataSource() {
        return DataSourceBuilder.create().build();
    }
}
