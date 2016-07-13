package edu.spring.java.lesson.DAO.DAOClases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.spring.java.lesson.DAO.DAOInterface.DAOUserInterface;
import edu.spring.java.lesson.DAO.db.ConnectionPool;
import edu.spring.java.lesson.DAO.exceptions.DaoException;
import edu.spring.java.lesson.entity.Users;

public class UserDAO implements DAOUserInterface<Users> {

	@Override
	public void insert(Users ob) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet set = null;
			try {
				connection = ConnectionPool.getPool().getConnection();
				statement = connection.prepareStatement("INSERT INTO USERS (username, password, role) VALUES (?, ?, ?)");
				statement.setString(1, ob.getUsername());
				statement.setString(2, ob.getPassword());
				statement.setString(3, ob.getRole());
				statement.executeUpdate();
			} catch (SQLException e) {
				throw new DaoException(e);
			} finally {
				ConnectionPool.getPool().closeDbResources(connection, statement, set);
			}
		}
	}
