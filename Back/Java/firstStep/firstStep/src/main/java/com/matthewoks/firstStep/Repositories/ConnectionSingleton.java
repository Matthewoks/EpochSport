package com.matthewoks.firstStep.Repositories;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class ConnectionSingleton {

    private static ConnectionSingleton instance;
    public static ConnectionSingleton getInstance(){
        if(instance== null)
            instance = new ConnectionSingleton();


        return instance;
    }

    private ConnectionSingleton(){

    }

    public Connection getConnection() throws SQLException
    {
            MysqlDataSource ds = new MysqlDataSource();
            ds.setServerName("localhost");
            ds.setPort(3306);
            ds.setUser("root");
            ds.setPassword("toor"); //eheh finchè si parla di noccioline la terrò in vista
            ds.setDatabaseName("allenamenti");
            ds.setUseSSL(false);
            ds.setAllowPublicKeyRetrieval(true);

            return ds.getConnection();
    }
}
