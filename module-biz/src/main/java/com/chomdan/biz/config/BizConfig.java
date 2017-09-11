package com.chomdan.biz.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.aspectj.AnnotationTransactionAspect;
import javax.sql.DataSource;
import java.util.Properties;

///**
// * Created by youngjo.jang on 2017-01-15.
// */
@Configuration
@PropertySource(value={
        "classpath:property/biz-config.properties"
})
@EnableTransactionManagement
@EnableJpaAuditing
@ComponentScan(basePackages = "com.chomdan.biz")
@EnableJpaRepositories(basePackages = { "com.chomdan.biz.repository" }, transactionManagerRef = "transactionManager")
public class BizConfig implements EnvironmentAware {

//    public static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
//    public static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
//    public static final String HIBERNATE_DIALECT = "hibernate.dialect";
//    public static final String HIBERNATE_HDM2DDL_AUTO = "hibernate.hbm2ddl.auto";
//    public static final String HIBERNATE_EJB_NAMING_STRATEGY = "hibernate.physical_naming_strategy";


//    spring.jpa.show-sql=true
//    spring.jpa.hibernate.ddl-auto=update
//    spring.jpa.hibernate.naming-strategy=org.hibernate.cfg.ImprovedNamingStrategy
//    spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQLDialect
//    spring.jpa.properties.hibernate.id.new_generator_mappings = false
//    spring.jpa.properties.hibernate.format_sql = true

    public static final String HIBERNATE_SHOW_SQL = "spring.jpa.show-sql";
    public static final String HIBERNATE_FORMAT_SQL = "spring.jpa.properties.hibernate.format_sql";
    public static final String HIBERNATE_DIALECT = "spring.jpa.properties.hibernate.dialect";
    public static final String HIBERNATE_HDM2DDL_AUTO = "spring.jpa.hibernate.ddl-auto";
//    public static final String HIBERNATE_EJB_NAMING_STRATEGY = "spring.jpa.hibernate.naming.physical-strategy";
//    public static final String HIBERNATE_EJB_NAMING_STRATEGY = "spring.jpa.hibernate.naming.physical-strategy";

    public static final String HIBERNATE_NAMING_PHYSICAL_STRATEGY = "spring.jpa.hibernate.naming.physical-strategy";
    public static final String HIBERNATE_NAMING_IMPLICIT_STRATEGY = "spring.jpa.hibernate.naming.implicit-strategy";

    public static final String HIBERNATE_CACHE_USE_QUERY_CACHE = "hibernate.cache.use_query_cache";
    public static final String HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = "hibernate.cache.use_second_level_cache";
    public static final String HIBERNATE_CACHE_REGION_FACTORY_CLASS = "hibernate.cache.region.factory_class";

    @Autowired
    Environment env;

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setFileEncoding("UTF-8");
        return propertySourcesPlaceholderConfigurer;
    }

    @Bean(name="dataSource", destroyMethod = "shutdown")
    public DataSource dataSource() {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(env.getProperty("database.url"));
        dataSource.setDriverClassName(env.getProperty("database.driverClassName"));
        dataSource.setUsername(env.getProperty("database.username"));
        dataSource.setPassword(env.getProperty("database.password"));

        int connectionCount = Integer.parseInt(env.getProperty("database.connectionCount"));
        dataSource.setMaximumPoolSize(connectionCount);
        dataSource.setMinimumIdle(connectionCount);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setPackagesToScan("com.chomdan.biz");
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter());
        entityManagerFactory.setJpaProperties(getHibernateProperties());
        return entityManagerFactory;
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setGenerateDdl(Boolean.TRUE);
        boolean showSql = Boolean.valueOf(env.getProperty("spring.jpa.show-sql"));
        hibernateJpaVendorAdapter.setShowSql(showSql);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource());
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        AnnotationTransactionAspect.aspectOf().setTransactionManager(transactionManager);
        AnnotationTransactionAspect.aspectOf().setTransactionManagerBeanName("transactionManager");
        return transactionManager;
    }

    protected Properties getHibernateProperties() throws Exception {
        final Properties properties = new Properties();

//         properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.hbm2ddl.auto",    "update");
        properties.setProperty("hibernate.dialect",         "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.show_sql",        "true");
        properties.setProperty("hibernate.format_sql",      "true");
//        properties.setProperty("hibernate.implicit_naming_strategy", "org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyHbmImpl");


//        properties.put(HIBERNATE_DIALECT,               env.getProperty(HIBERNATE_DIALECT));
//        properties.put(HIBERNATE_HDM2DDL_AUTO,          env.getProperty(HIBERNATE_HDM2DDL_AUTO));
//        properties.put(HIBERNATE_SHOW_SQL,              env.getProperty(HIBERNATE_SHOW_SQL));
//        properties.put(HIBERNATE_FORMAT_SQL,            env.getProperty(HIBERNATE_FORMAT_SQL));
////        properties.put(HIBERNATE_EJB_NAMING_STRATEGY,   env.getProperty(HIBERNATE_EJB_NAMING_STRATEGY));
//        properties.put(HIBERNATE_NAMING_PHYSICAL_STRATEGY,   env.getProperty(HIBERNATE_NAMING_PHYSICAL_STRATEGY));
//        properties.put(HIBERNATE_NAMING_IMPLICIT_STRATEGY,   env.getProperty(HIBERNATE_NAMING_IMPLICIT_STRATEGY));

//        EhCacheProvider.setCacheManager(ehCacheManagerFactoryBean().getObject());
//        properties.put(HIBERNATE_CACHE_REGION_FACTORY_CLASS, "com.kfms.base.utils.EhCacheProvider");
//        properties.put(HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE, true);
//        properties.put(HIBERNATE_CACHE_USE_QUERY_CACHE, true);
//        properties.put("hibernate.jdbc.batch_size", "50");

//        properties.put("hibernate.generate_statistics", true);
//        properties.put("hibernate.cache.use_structured_entries", true);
//        grid();
//        properties.put(HIBERNATE_CACHE_REGION_FACTORY_CLASS, "org.gridgain.grid.cache.hibernate.GridHibernateRegionFactory");
//        properties.put("org.gridgain.hibernate.grid_name", "hibernate-grid");
//        properties.put("org.gridgain.hibernate.default_access_type", "READ_ONLY");

        return properties;
    }




}