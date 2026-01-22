package com.Banco.CajerosService.Configuration;

import com.Banco.CajerosService.DTO.Result;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourcesConfig {
    
    @Bean
    public DataSource dataSource(){
        Result resultDataSource = new Result();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        
        try{
           
            dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
            dataSource.setUsername("UBancoSep2025");
            dataSource.setPassword("password1");
            resultDataSource.correct = true;
            resultDataSource.message = "Conexion Realizada correctamente";
            
        }catch(Exception ex){
            resultDataSource.message = ex.getMessage();
        }
        
        return dataSource;
    }
    
}
