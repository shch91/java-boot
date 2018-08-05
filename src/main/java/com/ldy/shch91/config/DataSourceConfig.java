package com.ldy.shch91.config;

import com.ldy.shch91.context.DynamicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean(name = "sakila")
    @ConfigurationProperties(prefix = "spring.datasource.sakila") // application.properteis中对应属性的前缀
    public DataSource dataSourceSakila() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "employees")
    @ConfigurationProperties(prefix = "spring.datasource.employees") // application.properteis中对应属性的前缀
    public DataSource dataSourceEmployees() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDS")
    public DataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(dataSourceEmployees());

        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap(5);
        dsMap.put("sakila", dataSourceSakila());
        dsMap.put("employees", dataSourceEmployees());

        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }

}