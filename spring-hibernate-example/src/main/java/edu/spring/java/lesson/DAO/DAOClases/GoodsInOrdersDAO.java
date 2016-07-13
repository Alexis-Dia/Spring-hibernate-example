package edu.spring.java.lesson.DAO.DAOClases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.spring.java.lesson.DAO.DAOInterface.DAOGoodsInOrdersInterface;
import edu.spring.java.lesson.DAO.db.ConnectionPool;
import edu.spring.java.lesson.DAO.exceptions.DaoException;
import edu.spring.java.lesson.entity.Goods_in_orders;

public class GoodsInOrdersDAO implements DAOGoodsInOrdersInterface<Goods_in_orders> {
	public void insert(Goods_in_orders ob) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		try {
			connection = ConnectionPool.getPool().getConnection();
			statement = connection.prepareStatement("INSERT INTO GOODS_IN_ORDERS (orders_id, goods_id, count) VALUES (?, ?, ?)");
			statement.setInt(1, ob.getOrders_id());
			statement.setInt(2, ob.getGoods_id());
			statement.setInt(3, ob.getCount());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			ConnectionPool.getPool().closeDbResources(connection, statement, set);
		}
	}
}
