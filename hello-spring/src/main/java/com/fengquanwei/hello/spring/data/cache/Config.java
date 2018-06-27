package com.fengquanwei.hello.spring.data.cache;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Config
 *
 * @author fengquanwei
 * @create 2018/6/26 15:00
 **/
@Configuration
@ComponentScan
@EnableCaching // 启动注解驱动的缓存
public class Config {
    // 配置缓存管理器：使用 ConcurrentHashMap 缓存

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }

    // 配置缓存管理器：使用 EhCache 缓存

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("spring-data-ehcache.xml"));
        return ehCacheManagerFactoryBean;
    }

    @Bean
    public EhCacheCacheManager ehcacheManager(net.sf.ehcache.CacheManager cacheManager) {
        return new EhCacheCacheManager(cacheManager);
    }

    // 配置缓存管理器：使用多个缓存管理器

    @Bean
    @Primary
    public CacheManager compositeCacheManager(CacheManager cacheManager, CacheManager ehcacheManager) {
        CompositeCacheManager compositeCacheManager = new CompositeCacheManager();

        List<CacheManager> cacheManagerList = new ArrayList<>();
        cacheManagerList.add(cacheManager);
        cacheManagerList.add(ehcacheManager);

        compositeCacheManager.setCacheManagers(cacheManagerList);
        return compositeCacheManager;
    }

    // 配置 Hibernate

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setInitialSize(4);
        dataSource.setMaxActive(10);

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan("com.fengquanwei.hello.spring.data.cache");

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        sessionFactoryBean.setHibernateProperties(properties);

        return sessionFactoryBean;
    }

    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
