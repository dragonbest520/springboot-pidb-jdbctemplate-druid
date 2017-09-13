package com.db520;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@SpringBootApplication
public class SpringbootPidbJdbcTemplateDruidApplication extends SpringBootServletInitializer {

    @Autowired
    private Environment env;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringbootPidbJdbcTemplateDruidApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPidbJdbcTemplateDruidApplication.class, args);
    }

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        /* dataSource.setInitialSize(2);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(60000);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(false);*/
        return dataSource;
    }
}
