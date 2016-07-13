package edu.spring.java.lesson.DAO.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {

	private static class PoolHolder {
		private static final ConnectionPool POOL = new ConnectionPool();
	}

	public static ConnectionPool getPool() {
		return PoolHolder.POOL;
	}

	private ConnectionPool() {
	}

	public Connection getConnection() {
		try {
			Context initContext = new InitialContext();
			Context rootContext = (Context) initContext.lookup("java:comp/env");
			DataSource dataSource = (DataSource) rootContext.lookup("jdbc/TrainingDB");
	
			Connection c = dataSource.getConnection();
	
			return c;
	
		} catch(NamingException e) {
			throw new RuntimeException("Some errors occurred during retrieving connection from JNDI!", e);
		} catch(SQLException e) {
			throw new RuntimeException("Some errors occurred during DB access!", e);
		}
	}

	public void closeDbResources(Connection connection) {
		closeDbResources(connection, null);
	}

	public void closeDbResources(Connection connection, PreparedStatement statement) {
		closeDbResources(connection, statement, null);
	}

	public void closeDbResources(Connection connection, PreparedStatement statement, ResultSet resultSet) {
		closeResultSet(resultSet);
		closeStatement(statement);
		closeConnection(connection);
	}

	private void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
			}
		}
	}

	private void closeStatement(PreparedStatement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
			}
		}
	}

	private void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
			}
		}
	}
}
