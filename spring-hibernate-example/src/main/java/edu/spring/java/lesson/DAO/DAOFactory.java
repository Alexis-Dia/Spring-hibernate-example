package edu.spring.java.lesson.DAO;

import edu.spring.java.lesson.DAO.DAOInterface.DAOGoodsInOrdersInterface;
import edu.spring.java.lesson.DAO.DAOInterface.DAOOrdersInterface;
import edu.spring.java.lesson.DAO.DAOInterface.DAOUserInterface;
import edu.spring.java.lesson.entity.Goods_in_orders;
import edu.spring.java.lesson.entity.Orders;
import edu.spring.java.lesson.entity.Users;

public interface DAOFactory {
	DAOUserInterface<Users> getDAOUser();
	DAOOrdersInterface<Orders> getDAOOrders();
	DAOGoodsInOrdersInterface<Goods_in_orders> getDAOGoodsInOrders();
}
