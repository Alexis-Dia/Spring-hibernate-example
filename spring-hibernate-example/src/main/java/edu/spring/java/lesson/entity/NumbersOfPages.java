package edu.spring.java.lesson.entity;

public class NumbersOfPages {

	int numberOfPageId;

	public NumbersOfPages() {
	}
	
	public NumbersOfPages(int numberOfPageId) {
		this.numberOfPageId = numberOfPageId;
	}
	
	public int getNumberOfPageId() {
		return numberOfPageId;
	} 

	public void setNumberOfPageId(int numberOfPageId) {
		this.numberOfPageId = numberOfPageId;
	}

	@Override
	public String toString() {
		return "NumbersOfPages [numberOfPageId=" + numberOfPageId + "]";
	}
		
}
