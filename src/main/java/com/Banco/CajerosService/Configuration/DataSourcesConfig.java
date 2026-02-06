package com.Banco.CajerosService.Configuration;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Configuración de DataSource para conexión a Oracle
 * DataSource Configuration for Oracle Connection
 */
@Configuration
public class DataSourcesConfig {

    @Value("${spring.datasource.driver-class-name:oracle.jdbc.OracleDriver}")
    private String driverClassName;

    @Value("${spring.datasource.url:jdbc:oracle:thin:@localhost:1521:orcl}")
    private String url;

    @Value("${spring.datasource.username:UBANCOSEP2025}")
    private String username;

    @Value("${spring.datasource.password:password1}")
    private String password;
    
    /**
     * Bean de DataSource para Oracle Database
     * DataSource Bean for Oracle - jdbc:oracle:thin driver
     * 
     * @return DataSource configurado y listo para uso
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        
        return dataSource;
    }
}
