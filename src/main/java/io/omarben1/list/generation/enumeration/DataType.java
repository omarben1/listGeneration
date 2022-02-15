package io.omarben1.list.generation.enumeration;

public enum DataType {
	
	EMAIL("^[A-Z0-9._%+-]{5,6}@[A-Z0-9.-]{3,4}\\.[A-Z]{2,6}$"),
	SIMPLE_NAME("^[A-Z][a-z]{4,7}$"),
	TEXT("(^[A-Z][a-z]{1,6} ){7,20}");
	
	private String regex;
	
	DataType(String regex){
		this.regex = regex;
	}
	
	public String getRegex() {
		return this.regex;
	}
	
}
