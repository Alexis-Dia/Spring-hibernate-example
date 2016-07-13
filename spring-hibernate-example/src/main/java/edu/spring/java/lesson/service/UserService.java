package edu.spring.java.lesson.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Query; 
import org.hibernate.SessionFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.java.lesson.entity.Users;

@Named
public class UserService implements UserDetailsService {

	@Inject
	private SessionFactory sessionFactory;
	
	public UserService() {
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Query query = sessionFactory.getCurrentSession().createQuery("from Users u where u.username=:username");
		query.setParameter("username", username);
		Users result = (Users) query.uniqueResult();
		return  result;
	}

}
