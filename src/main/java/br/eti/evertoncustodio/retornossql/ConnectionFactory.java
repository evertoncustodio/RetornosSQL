package br.eti.evertoncustodio.retornossql;

import java.sql.Connection;
import java.sql.DriverManager;

class ConnectionFactory {

	public Connection acquireConnection() {
		String url = "jdbc:jtds:sqlserver://localhost/RETORNOS_SQL" ;
		
		try {
			Connection con = DriverManager.getConnection(url, "sa", "root");
			return con;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
