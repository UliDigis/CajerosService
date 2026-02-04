package com.Banco.CajerosService.Configuration;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Configuración de DataSource para conexión a Oracle
 * DataSource Configuration for Oracle Connection
 */
@Configuration
public class DataSourcesConfig {
    
    /**
     * Bean de DataSource para Oracle Database
     * DataSource Bean for Oracle - jdbc:oracle:thin driver
     * 
     * @return DataSource configurado y listo para uso
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        
        // Driver de Oracle JDBC
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        
        // Configuración de conexión Oracle
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
        dataSource.setUsername("UBancoSep2025");
        dataSource.setPassword("password1");
        
        return dataSource;
    }
}
