package com.moxos.uab.config;

import com.moxos.uab.config.propierties.PostgresPropierties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.moxos.uab.persistence.die", sqlSessionFactoryRef = "dieSqlSessionFactory")
public class MyBatisConfigDie {

    @Bean("dieConnectionString")
    @ConfigurationProperties(prefix = "die.datasource")
    public PostgresPropierties client() {
        return new PostgresPropierties();
    }

    @Bean(name = "dieDataSource")
    public DataSource dieDataSource(@Qualifier("dieConnectionString") PostgresPropierties cliente) {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url(cliente.getUrl());
        dataSourceBuilder.username(cliente.getUsername());
        dataSourceBuilder.password(cliente.getPassword());
        return dataSourceBuilder.build();
    }


    @Bean(name = "dieSqlSessionFactory")
    public SqlSessionFactory dieSqlSessionFactory(@Qualifier("dieDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/die/*.xml"));
        return sessionFactory.getObject();
    }
}
