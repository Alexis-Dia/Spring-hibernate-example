package edu.spring.java.lesson.DAO;

import edu.spring.java.lesson.DAO.DAOClases.GoodsInOrdersDAO;
import edu.spring.java.lesson.DAO.DAOClases.OrdersDAO;
import edu.spring.java.lesson.DAO.DAOClases.UserDAO;
import edu.spring.java.lesson.DAO.DAOInterface.DAOGoodsInOrdersInterface;
import edu.spring.java.lesson.DAO.DAOInterface.DAOOrdersInterface;
import edu.spring.java.lesson.DAO.DAOInterface.DAOUserInterface;
import edu.spring.java.lesson.entity.Goods_in_orders;
import edu.spring.java.lesson.entity.Orders;
import edu.spring.java.lesson.entity.Users;

public class DAOMaker implements DAOFactory {

	public DAOUserInterface<Users> getDAOUser() {
		return new UserDAO();
	}

	public DAOOrdersInterface<Orders> getDAOOrders() {
		return new OrdersDAO();
	}

	public DAOGoodsInOrdersInterface<Goods_in_orders> getDAOGoodsInOrders() {
		return new GoodsInOrdersDAO();
	}
	
}
