package io.omarben1.list.generation.beans;

import java.time.LocalDate;

import io.omarben1.list.generation.annotation.Criterion;
import io.omarben1.list.generation.enumeration.DataType;

public class Student {
	
	@Criterion(data = DataType.SIMPLE_NAME)
	private String firstName;
	
	@Criterion(data = DataType.SIMPLE_NAME)
	private String lastName;
	
	@Criterion(maxValue = "25")
	private Integer age;

	@Criterion(data = DataType.EMAIL)
	private String email;
	
	@Criterion(minDate = "2019-01-10", maxDate = "2021-01-11")
	private LocalDate registrationDate;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", email=" + email
				+ ", registrationDate=" + registrationDate + "]";
	}

}
