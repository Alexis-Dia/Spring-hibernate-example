package edu.spring.java.lesson.DAO.DAOClases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.spring.java.lesson.DAO.DAOInterface.DAOOrdersInterface;
import edu.spring.java.lesson.DAO.db.ConnectionPool;
import edu.spring.java.lesson.DAO.exceptions.DaoException;
import edu.spring.java.lesson.entity.Orders;

public class OrdersDAO implements DAOOrdersInterface<Orders> {

	public void insert(Orders ob) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		try {
			connection = ConnectionPool.getPool().getConnection();
			statement = connection.prepareStatement("INSERT INTO ORDERS (users_id, payment, delete_status, totalcost) VALUES (?, ?, ?, ?)");
			statement.setInt(1, ob.getUsers_id());
			statement.setString(2, ob.getPayment());
			statement.setInt(3, ob.getDelete_status());
			statement.setInt(4, ob.getTotal_cost());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			ConnectionPool.getPool().closeDbResources(connection, statement, set);
		}
	}
	
    public int getLastInsertId() { 
    	Integer id = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		try {
			connection = ConnectionPool.getPool().getConnection();
			statement = connection.prepareStatement("SELECT  LAST_INSERT_ID()  FROM internetshop.orders");
			set = statement.executeQuery();
			while (set.next()) {
				id = set.getInt(1);	
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			ConnectionPool.getPool().closeDbResources(connection, statement, set);
		}
    	return id;
    } 
    
}
