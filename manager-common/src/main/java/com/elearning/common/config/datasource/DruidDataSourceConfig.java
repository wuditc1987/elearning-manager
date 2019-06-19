package com.elearning.common.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.elearning.common.config.datasource.constants.DataSourceType;
import com.elearning.common.config.datasource.dynamic.DynamicDataSource;
import com.elearning.common.config.datasource.properties.DruidDataSourceProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置
 * @author wudi
 */
@Configuration
public class DruidDataSourceConfig {

    // ---- master datasource config

    @Bean("masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public DataSource masterDataSource(DruidDataSourceProperties properties){
        return properties.getDataSource(DruidDataSourceBuilder.create().build());
    }

    // ---- cluster datasource config

    @Bean("clusterDataSource")
    @ConfigurationProperties("spring.datasource.druid.cluster")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.cluster" , name = "enabled" , havingValue = "true")
    public DataSource clusterDataSource(DruidDataSourceProperties properties){
        return properties.getDataSource(DruidDataSourceBuilder.create().build());
    }

    @Primary
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dataSource(DataSource masterDataSource, DataSource clusterDataSource){
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>(2);
        targetDataSources.put(DataSourceType.MASTER,masterDataSource);
        targetDataSources.put(DataSourceType.CLUSTER,clusterDataSource);
        return new DynamicDataSource(masterDataSource,targetDataSources);
    }

    /*@Bean("masterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory() throws Exception{
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(masterDataSource());
        return factoryBean.getObject();
    }

    @Bean("masterSqlSessionTemplate")
    public SqlSessionTemplate masterSqlSessionTemplate() throws Exception{
        return new SqlSessionTemplate(masterSqlSessionFactory());
    }

    @Bean("clusterSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory() throws Exception{
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(clusterDataSource());
        return factoryBean.getObject();
    }

    @Bean("clusterSqlSessionTemplate")
    public SqlSessionTemplate clusterSqlSessionTemplate() throws Exception{
        return new SqlSessionTemplate(clusterSqlSessionFactory());
    }*/

}
