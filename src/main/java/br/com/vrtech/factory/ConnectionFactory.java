package br.com.vrtech.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	public DataSource dataSource;
	
	public ConnectionFactory() {
		
		String servidor = "localhost";
		String porta = "3306";
		String banco = "transferencia_de_dados";
		String url = String.format("jdbc:mysql://%s:%s/%s?useTimezone=true&serverTimezone=UTC", servidor, porta, banco);
			
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl(url);
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("root");
		comboPooledDataSource.setMaxPoolSize(10);
		
		this.dataSource = comboPooledDataSource;
	}
	
	public Connection recuperarConexao() {
		try {
			return this.dataSource.getConnection();
		} catch(SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

}
