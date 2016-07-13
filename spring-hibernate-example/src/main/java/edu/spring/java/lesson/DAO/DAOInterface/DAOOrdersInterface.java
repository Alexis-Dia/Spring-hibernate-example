package edu.spring.java.lesson.DAO.DAOInterface;

public interface DAOOrdersInterface<T> {
	public void  insert(T ob);
	public int getLastInsertId();
}
