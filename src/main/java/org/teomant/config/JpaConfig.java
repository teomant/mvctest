package org.teomant.config;

import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.teomant.Application;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ClassUtils;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource( "classpath:jpa.properties" )
@EnableTransactionManagement
@EnableJpaRepositories( basePackageClasses = Application.class )
public class JpaConfig{

    @Value( "${jndi.name}" ) private         String jndiName;
    @Value( "${db.dialect}" ) private        String dialect;
    @Value( "${db.default.schema}" ) private String schema;
    @Value( "${db.show_sql}" ) private       String showSql;

    @Bean
    public DataSource dataSource() throws NamingException{
        return ( DataSource ) new JndiTemplate().lookup( jndiName );
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory( DataSource dataSource ){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource( dataSource );

        String entities   = ClassUtils.getPackageName( Application.class );
        String converters = ClassUtils.getPackageName( Jsr310JpaConverters.class );
        entityManagerFactoryBean.setPackagesToScan( entities , converters );

        entityManagerFactoryBean.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );

        Properties jpaProperties = new Properties();
        jpaProperties.put( Environment.DIALECT , dialect );
        jpaProperties.put( Environment.SHOW_SQL , showSql );
        jpaProperties.put( Environment.DEFAULT_SCHEMA , schema );
        entityManagerFactoryBean.setJpaProperties( jpaProperties );

        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory ){
        return new JpaTransactionManager( entityManagerFactory );
    }

    //
    // If don`t want to store info about db on server
    //

//
//    @Value("db.name")
//    private String dbName;
//    @Value("db.auth")
//    private String dbAuth;
//    @Value("db.type")
//    private String dbType;
//    @Value("db.username")
//    private String dbUsername;
//    @Value("db.password")
//    private String dbPassword;
//    @Value("db.driverClassName")
//    private String dbDriverClassName;
//    @Value("db.url")
//    private String dbUrl;
//    @Value("db.validationQuery")
//    private String dbValidationQuery;
//    @Value("db.maxActive")
//    private String dbMaxActive;
//    @Value("db.maxIdle")
//    private String dbMaxIdle;
//
//
//    @Bean
//    public PlatformTransactionManager transactionManager(
//            EntityManagerFactory entityManagerFactory ){
//        return new JpaTransactionManager( entityManagerFactory );
//    }
//
//    @Bean
//    public DataSource dataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/opencodetest");
//        dataSource.setUsername( "postgres" );
//        dataSource.setPassword( "tempus" );
//        dataSource.setSchema( "public" );
//        return dataSource;
//    }
//
//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
//        return new PersistenceExceptionTranslationPostProcessor();
//    }
//
//    Properties additionalProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.hbm2ddl.auto", "update");
//        properties.setProperty(
//                "hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
//
//        return properties;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(  ){
//        LocalContainerEntityManagerFactoryBean em
//                = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource());
//        em.setPackagesToScan(new String[] { "org.teomant" });
//
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        em.setJpaProperties(additionalProperties());
//
//        return em;
//    }

}

