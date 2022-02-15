package io.omarben1.list.generation.beans;

import io.omarben1.list.generation.annotation.Ignore;

public class Book {
	
	@Ignore
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	private Integer price;

}
