package io.omarben1.list.generation;

import java.time.LocalDate;
import java.util.List;

import io.omarben1.list.generation.annotation.Criterion;
import io.omarben1.list.generation.enumeration.DataType;

public class Student {
	
	@Criterion(data = DataType.SIMPLE_NAME)
	private String age;

	@Criterion(data = DataType.EMAIL)
	private String name;
	
	@Criterion(minDate = "2020-01-11")
	private LocalDate date;
	
	@Criterion(maxValue = "20")
	private Float mark;
	
	private List<Object> l;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getAge() {
		return age;
	}

	public void setAge(Float age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [age=" + age + ", name=" + name + ", date=" + date + ", note="+mark+"]";
	}
	
}
