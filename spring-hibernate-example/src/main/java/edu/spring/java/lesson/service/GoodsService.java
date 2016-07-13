package edu.spring.java.lesson.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.spring.java.lesson.entity.Goods;
import edu.spring.java.lesson.entity.Goods_in_orders;
import edu.spring.java.lesson.entity.QuantityAndSum;

@Named("goodsService")
@Scope("session")
public class GoodsService {
	
	int i = 0;
	int numberPage = 0;
	private static final String NUMBER_OF_PAGE = "numberOfPage";
	private static final String NAME_ATRIBUTE = "goods";
	private static final String PRICE_LOWER = "priceLower";
	private static final String PRICE_HIGHTER = "priceHighter";
	String priceLower;
	String priceHighter;
	static List<Goods_in_orders> list;

	@Inject
	private SessionFactory sessionFactory;
	
	public GoodsService(){
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public ModelAndView getAll(HttpServletRequest request, ModelAndView modelAndView){
		Session session = sessionFactory.openSession();
		List <Goods> products = null;
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Goods.class);
			criteria.add(Restrictions.sqlRestriction("category_id > 0"));
			criteria.add(Restrictions.sqlRestriction("category_id < 2"));
			priceLower = GoodsService.getStringOrEmptyMinValue(priceLower, PRICE_LOWER, request);
			priceHighter = GoodsService.getStringOrEmptyMaxValue(priceHighter, PRICE_HIGHTER, request);
			criteria.add(Restrictions.sqlRestriction("price > " + priceLower));
			criteria.add(Restrictions.sqlRestriction("price < " + priceHighter));
			products = criteria.list();
			i = products.size();
			NavigationService du = new NavigationService();
			modelAndView.addObject(NUMBER_OF_PAGE,
					du.getNumberInResult((int) Math.ceil((double) i / 10)));
			modelAndView.setViewName("tv");
			
			if (request.getParameter("number") == null) {
				modelAndView.addObject(NAME_ATRIBUTE,
						du.getFilterPosts(products, 1));
				modelAndView.setViewName("tv");
			} else {
				modelAndView.addObject(NAME_ATRIBUTE, du.getFilterPosts(
						products,
						Integer.parseInt(request.getParameter("number"))));
				modelAndView.setViewName("tv");
			}
			session.getTransaction().commit(); 
		} catch (Exception e) {
			session.getTransaction().rollback();
		}
		finally {
			session.close();
		}
		return modelAndView;
	}
	
	public static String getStringOrEmptyMinValue (String priceLower, String constant, HttpServletRequest request) {
		if(request.getParameter(constant) == null){
			priceLower = "0";
			request.getSession().setAttribute(constant, "");
		} else {
			priceLower = request.getParameter(constant);
			request.getSession().setAttribute(constant, request.getParameter(constant));
		} if (priceLower.equals("")){
			priceLower = "0";
		}
		return priceLower;
	} 
	
	public static String getStringOrEmptyMaxValue (String priceLower, String constant, HttpServletRequest request) {
		if(request.getParameter(constant) == null){
			priceLower = "99999";
			request.getSession().setAttribute(constant, "");
		} else {
			priceLower = request.getParameter(constant);
			request.getSession().setAttribute(constant, request.getParameter(constant));
		} if (priceLower.equals("")){
			priceLower = "99999";
		}
		return priceLower;
	} 
	
	@SuppressWarnings("unchecked")
	public ModelAndView setToSessionGoodsInOrders(
			HttpServletRequest request, HttpSession session,
			ModelAndView modelAndView) {
		int count = 1;
		int repeatGoodsFlag = 0;
		String goods_id = request.getParameter("goods_id");
		String name = request.getParameter("name");
		if ((List<Goods_in_orders>) session.getAttribute("GoodsInCart") == null) {
			list = new ArrayList<Goods_in_orders>();
		} else {
			list = (List<Goods_in_orders>) session.getAttribute("GoodsInCart");
		}

		if (goods_id != null && name != null) {
			for (Goods_in_orders goods_in_orders : list) {
				if (goods_in_orders.getDescription().equals(
						request.getParameter("description"))) {
					goods_in_orders.setCount(goods_in_orders.getCount()
							+ count);
					repeatGoodsFlag = 1;
				}
			}
			if (repeatGoodsFlag != 1) {
				list.add(new Goods_in_orders(1, Integer.parseInt(request
						.getParameter("goods_id")), request
						.getParameter("name"), request
						.getParameter("description"), 1, Integer
						.parseInt(request.getParameter("price")), request
						.getParameter("image_path")));
			}
		}
		session.setAttribute("GoodsInCart", list);
		List<Goods_in_orders> goodsInOrders = (List<Goods_in_orders>) session.getAttribute("GoodsInCart");
		modelAndView.addObject("cart", goodsInOrders);
		modelAndView.setViewName("cart");
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView cleanAllFromSessionGoodsInOrders(HttpSession session, ModelAndView modelAndView) {
		if ((List<Goods_in_orders>) session.getAttribute("GoodsInCart") != null){
			list  = new ArrayList<Goods_in_orders>();
			session.setAttribute("GoodsInCart", list);
			List<Goods_in_orders> goodsInOrders = (List<Goods_in_orders>) session.getAttribute("GoodsInCart");
			modelAndView.addObject("cart", goodsInOrders);
		}
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping()
	public ModelAndView deleteFromSessionGoodsInOrders (HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
		if ((List<Goods_in_orders>) session.getAttribute("GoodsInCart") != null && request.getParameter("deleteByDescription")!=null){
			list = (List<Goods_in_orders>) session.getAttribute("GoodsInCart");
	        Iterator<Goods_in_orders> it = list.iterator();
	        while (it.hasNext()) {
	            if (it.next().getDescription().equals(request.getParameter("deleteByDescription"))) {
	                it.remove();
	            }
	        }
			session.setAttribute("GoodsInCart", list);
			List<Goods_in_orders> goodsInOrders = (List<Goods_in_orders>) session.getAttribute("GoodsInCart");
			modelAndView.addObject("cart", goodsInOrders);
		}
		modelAndView.setViewName("cart");
		return modelAndView;
	} 
	
	@SuppressWarnings("unchecked")
	@RequestMapping()
	public ModelAndView setQuantitiAndSum (HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
		int sum = 0;
		if ((List<Goods_in_orders>) session.getAttribute("GoodsInCart") != null){
			List<Goods_in_orders> goodsInOrders = (List<Goods_in_orders>) session.getAttribute("GoodsInCart");
			for (Goods_in_orders goods_in_orders : goodsInOrders) {
				sum = sum +goods_in_orders.getPrice()*goods_in_orders.getCount();
			}
			List<QuantityAndSum> listQuantityAndSum = new ArrayList<QuantityAndSum> ();
	        	listQuantityAndSum.add(new QuantityAndSum(list.size(), sum));
			session.setAttribute("quantitiAndSum", listQuantityAndSum);
			List<Goods_in_orders> quantitiAndSum = (List<Goods_in_orders>) session.getAttribute("quantitiAndSum");
			modelAndView.addObject("quantitiAndSum", quantitiAndSum);
			session.setAttribute("totalCost", sum);
		}
		modelAndView.setViewName("cart");
		return modelAndView;
	} 
	
	@SuppressWarnings("unchecked")
	@RequestMapping()
	public ModelAndView setQuantitiAndSumTv (HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
		int sum = 0;
		if ((List<Goods_in_orders>) session.getAttribute("GoodsInCart") != null){
			List<Goods_in_orders> goodsInOrders = (List<Goods_in_orders>) session.getAttribute("GoodsInCart");
			for (Goods_in_orders goods_in_orders : goodsInOrders) {
				sum = sum +goods_in_orders.getPrice()*goods_in_orders.getCount();
			}
			List<QuantityAndSum> listQuantityAndSum = new ArrayList<QuantityAndSum> ();
	        	listQuantityAndSum.add(new QuantityAndSum(list.size(), sum));
			session.setAttribute("quantitiAndSum", listQuantityAndSum);
			List<Goods_in_orders> quantitiAndSum = (List<Goods_in_orders>) session.getAttribute("quantitiAndSum");
			modelAndView.addObject("quantitiAndSum", quantitiAndSum);
		}
		modelAndView.setViewName("tv");
		return modelAndView;
	} 
	
	@SuppressWarnings("unchecked")
	@RequestMapping()
	public ModelAndView setQuantitiAndSumHello (HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
		int sum = 0;
		if ((List<Goods_in_orders>) session.getAttribute("GoodsInCart") != null){
			List<Goods_in_orders> goodsInOrders = (List<Goods_in_orders>) session.getAttribute("GoodsInCart");
			for (Goods_in_orders goods_in_orders : goodsInOrders) {
				sum = sum +goods_in_orders.getPrice()*goods_in_orders.getCount();
			}
			List<QuantityAndSum> listQuantityAndSum = new ArrayList<QuantityAndSum> ();
	        	listQuantityAndSum.add(new QuantityAndSum(list.size(), sum));
			session.setAttribute("quantitiAndSum", listQuantityAndSum);
			List<Goods_in_orders> quantitiAndSum = (List<Goods_in_orders>) session.getAttribute("quantitiAndSum");
			modelAndView.addObject("quantitiAndSum", quantitiAndSum);
		}
		modelAndView.setViewName("hello");
		return modelAndView;
	} 
	
	@SuppressWarnings("unchecked")
	@RequestMapping()
	public ModelAndView setQuantitiAndSumContact (HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
		int sum = 0;
		if ((List<Goods_in_orders>) session.getAttribute("GoodsInCart") != null){
			List<Goods_in_orders> goodsInOrders = (List<Goods_in_orders>) session.getAttribute("GoodsInCart");
			for (Goods_in_orders goods_in_orders : goodsInOrders) {
				sum = sum +goods_in_orders.getPrice()*goods_in_orders.getCount();
			}
			List<QuantityAndSum> listQuantityAndSum = new ArrayList<QuantityAndSum> ();
	        	listQuantityAndSum.add(new QuantityAndSum(list.size(), sum));
			session.setAttribute("quantitiAndSum", listQuantityAndSum);
			List<Goods_in_orders> quantitiAndSum = (List<Goods_in_orders>) session.getAttribute("quantitiAndSum");
			modelAndView.addObject("quantitiAndSum", quantitiAndSum);
		}
		modelAndView.setViewName("contact");
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping()
	public ModelAndView setQuantitiAndSumPurchase (HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
		int sum = 0;
		if ((List<Goods_in_orders>) session.getAttribute("GoodsInCart") != null){
			List<Goods_in_orders> goodsInOrders = (List<Goods_in_orders>) session.getAttribute("GoodsInCart");
			for (Goods_in_orders goods_in_orders : goodsInOrders) {
				sum = sum +goods_in_orders.getPrice()*goods_in_orders.getCount();
			}
			List<QuantityAndSum> listQuantityAndSum = new ArrayList<QuantityAndSum> ();
	        	listQuantityAndSum.add(new QuantityAndSum(list.size(), sum));
			session.setAttribute("quantitiAndSum", listQuantityAndSum);
			List<Goods_in_orders> quantitiAndSum = (List<Goods_in_orders>) session.getAttribute("quantitiAndSum");
			modelAndView.addObject("quantitiAndSum", quantitiAndSum);
		}
		modelAndView.setViewName("addPurchase");
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping()
	public ModelAndView setQuantitiAndSumIndex (HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
		int sum = 0;
		if ((List<Goods_in_orders>) session.getAttribute("GoodsInCart") != null){
			List<Goods_in_orders> goodsInOrders = (List<Goods_in_orders>) session.getAttribute("GoodsInCart");
			for (Goods_in_orders goods_in_orders : goodsInOrders) {
				sum = sum +goods_in_orders.getPrice()*goods_in_orders.getCount();
			}
			List<QuantityAndSum> listQuantityAndSum = new ArrayList<QuantityAndSum> ();
	        	listQuantityAndSum.add(new QuantityAndSum(list.size(), sum));
			session.setAttribute("quantitiAndSum", listQuantityAndSum);
			List<Goods_in_orders> quantitiAndSum = (List<Goods_in_orders>) session.getAttribute("quantitiAndSum");
			modelAndView.addObject("quantitiAndSum", quantitiAndSum);
		}
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
}
