package com.matthewoks.firstStep.Repositories;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class DatabaseConfig {
@Bean
public DataSource dataSource() throws SQLException {
    MysqlDataSource ds = new MysqlDataSource();
    ds.setServerName("localhost");
    ds.setPort(3306);
    ds.setUser("root");
    ds.setPassword("toor"); //eheh finchè si parla di noccioline la terrò in vista
    ds.setDatabaseName("allenamenti");
    ds.setUseSSL(false);
    ds.setAllowPublicKeyRetrieval(true);

    return ds;
}
@Bean
public Connection connection(DataSource dataSource)  throws SQLException  {
    return  dataSource.getConnection();

    }
}
