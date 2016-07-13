package edu.spring.java.lesson.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.spring.java.lesson.DAO.DAOFactory;
import edu.spring.java.lesson.DAO.DAOMaker;
import edu.spring.java.lesson.DAO.DAOInterface.DAOGoodsInOrdersInterface;
import edu.spring.java.lesson.DAO.DAOInterface.DAOOrdersInterface;
import edu.spring.java.lesson.DAO.DAOInterface.DAOUserInterface;
import edu.spring.java.lesson.entity.Goods_in_orders;
import edu.spring.java.lesson.entity.Orders;
import edu.spring.java.lesson.entity.Users;
import edu.spring.java.lesson.service.GoodsService;
import edu.spring.java.lesson.utils.HttpUtils;

@Controller
@Scope("session")
public class MainController {
	 
	@Inject
	@Qualifier("goodsService")
	private GoodsService goodsService;
 
	@RequestMapping(value = "/tv", method = RequestMethod.GET)
	public ModelAndView allList0(HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = goodsService.getAll(request, modelAndView);
		modelAndView = goodsService.setQuantitiAndSumTv(request, session, modelAndView);
		return modelAndView;
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ModelAndView allList(HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "a");
		modelAndView = goodsService.setQuantitiAndSumHello(request, session, modelAndView);
		modelAndView.setViewName("hello");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView allList3() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/getRegistration", method = RequestMethod.GET)
	public ModelAndView allList4(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("getRegistration");
		if (HttpUtils.StringOrEmpty(request, "username") && HttpUtils.StringOrEmpty(request, "password")){
		DAOFactory factory = new DAOMaker();
		 DAOUserInterface<Users> dao = factory.getDAOUser();
		 dao.insert(new Users(request.getParameter("username"), request.getParameter("password"), "user"));
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView allList4(HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = goodsService.deleteFromSessionGoodsInOrders(request, session, modelAndView);
		modelAndView = goodsService.setToSessionGoodsInOrders(request, session, modelAndView);
		modelAndView = goodsService.setQuantitiAndSum(request, session, modelAndView);
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addPurchase", method = RequestMethod.GET)
	public ModelAndView allList6(HttpServletRequest request, HttpSession session) {
		List<Goods_in_orders> list = null;
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addPurchase");
		if (HttpUtils.CheckPrincipal() && HttpUtils.IntegerOrEmpty(session)){
		DAOFactory factory = new DAOMaker(); 
		 DAOOrdersInterface<Orders> daoOrders = factory.getDAOOrders();
		 DAOGoodsInOrdersInterface<Goods_in_orders> daoGoodsInOrders = factory.getDAOGoodsInOrders();
		 daoOrders.insert(new Orders(HttpUtils.StringSplitter(HttpUtils.UsersId()), "processing", 0, (int)session.getAttribute("totalCost")));
		 if ((List<Goods_in_orders>) session.getAttribute("GoodsInCart") != null) {
				list = (List<Goods_in_orders>) session.getAttribute("GoodsInCart");
				 	for (Goods_in_orders goods_in_orders : list) {
					daoGoodsInOrders.insert(new Goods_in_orders(daoOrders.getLastInsertId(), goods_in_orders.getGoods_id(), goods_in_orders.getCount()));
				}
			}
		 modelAndView = goodsService.cleanAllFromSessionGoodsInOrders(session, modelAndView);
		}
		modelAndView = goodsService.setQuantitiAndSumPurchase(request, session, modelAndView);
		return modelAndView;
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView allList5(HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = goodsService.getAll(request, modelAndView);
		modelAndView = goodsService.setQuantitiAndSumContact(request, session, modelAndView);
		return modelAndView;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView allList7(HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = goodsService.setToSessionGoodsInOrders(request, session, modelAndView);
		modelAndView = goodsService.setQuantitiAndSumIndex(request, session, modelAndView);
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView allList8(HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin");
		return modelAndView;
	}
	
}
