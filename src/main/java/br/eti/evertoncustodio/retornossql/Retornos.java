package br.eti.evertoncustodio.retornossql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

class Retornos {

	public static void main(String[] args) {
		Retornos retornos = new Retornos();
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		try(Connection con = connectionFactory.acquireConnection()) {
			System.out.println("ResultSet");
			retornos.comoResultSet(con);
			System.out.println();
			
			System.out.println("Output parameter");
			retornos.comoOutput(con);
			System.out.println();
			
			System.out.println("Return");
			retornos.comoReturn(con);
			System.out.println();
			
			System.out.println("Todos");
			retornos.todosOsTipos(con);
			System.out.println();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void comoResultSet(Connection con) throws SQLException {
		String sql = "EXEC SP_RETORNO_RESULTSET";
		
		CallableStatement call = con.prepareCall(sql);
		ResultSet resultado = call.executeQuery();
		
		while(resultado.next()) {
			System.out.println(resultado.getString("NOME"));
		}
		
		resultado.close();
		call.close();
	}
	
	public void comoOutput(Connection con) throws SQLException {
		String sql = "EXEC SP_RETORNO_OUTPUT ?";
		
		CallableStatement call = con.prepareCall(sql);
		call.registerOutParameter("NOME", java.sql.Types.VARCHAR);
		call.execute();
		String resultado = call.getString("NOME");
		
		System.out.println(resultado);
		
		call.close();
	}
	
	public void comoReturn(Connection con)  throws SQLException {
		String sql = "? = EXEC SP_RETORNO_RETURN";
		
		CallableStatement call = con.prepareCall(sql);
		call.registerOutParameter(1, java.sql.Types.INTEGER);
		call.execute();
		int resultado = call.getInt(1);
		
		System.out.println(resultado);
		
		call.close();
	}
	
	public void todosOsTipos(Connection con)  throws SQLException {
		String sql = "? = EXEC SP_RETORNO_TODOS ?";
		
		CallableStatement call = con.prepareCall(sql);
		
		call.registerOutParameter(1, java.sql.Types.INTEGER);
		call.registerOutParameter("NOME", java.sql.Types.VARCHAR);
		ResultSet resultado = call.executeQuery();
		
		while(resultado.next()) {
			System.out.println(resultado.getString("NOME"));
		}
		
		String nome = call.getString("NOME");
		int codigoRetorno = call.getInt(1);
		
		System.out.println("Output: " + nome);
		System.out.println("Código de Retorno: " + codigoRetorno);
		
		resultado.close();
		call.close();
	}
}
