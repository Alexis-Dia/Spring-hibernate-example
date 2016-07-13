package edu.spring.java.lesson.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.spring.java.lesson.entity.Goods;
import edu.spring.java.lesson.entity.NumbersOfPages;

public class NavigationService {

	int id = 0;
	int idMax = 0;

	public NavigationService() {
	}

	public List<Goods> getFilterPosts(List<Goods> goodsInput, int i)
			throws ClassNotFoundException, SQLException {
		List<Goods> goods = goodsInput;
		List<Goods> goodsFiltered = new ArrayList<Goods>();
		for (int j = 0; j < goods.size(); j++) {
			if (j < i * 10 && j >= i * 10 - 10) { 
				goodsFiltered.add(new Goods(goods.get(j).getGoods_id(), goods
						.get(j).getCategoty_id(), goods.get(j).getName(), goods
						.get(j).getImage_path(), goods.get(j).getPrice(), goods
						.get(j).getOldprice(), goods.get(j).getDescription(),
						goods.get(j).getCharacteristic1(), goods.get(j)
								.getCharacteristic2(), goods.get(j)
								.getCharacteristic3(), goods.get(j)
								.getCharacteristic4(), goods.get(j)
								.getCharacteristic5(), goods.get(j)
								.getCharacteristic6(), goods.get(j)
								.getCharacteristic7(), goods.get(j)
								.getCharacteristic8(), goods.get(j)
								.getCharacteristic9(), goods.get(j)
								.getCharacteristic10(), goods.get(j)
								.getCharacteristic11(), goods.get(j)
								.getDelete_status(), goods.get(j)
								.getStock_status(), goods.get(j).getRating()));
			}
		}
		return goodsFiltered;
	}
	
	public List<NumbersOfPages> getNumberInResult(int number) throws ClassNotFoundException, SQLException {
		String[] array = new String[number];
		ArrayList<NumbersOfPages> numberOfPage = new ArrayList<NumbersOfPages>();
		for (int i = 0; i < array.length; i++) {
			id = id+1; 
			numberOfPage.add(new NumbersOfPages(id));
		}
		return numberOfPage;
	}
}
