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
	private String personalEmail;
	
	@Criterion(regex = "^[A-Z0-9._%+-]{5,6}@ump\\.[A-Z]{2,6}$")
	private String universityEmail;
	
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
	
	public String getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}
	
	public String getUniversityEmail() {
		return universityEmail;
	}

	public void setUniversityEmail(String universityEmail) {
		this.universityEmail = universityEmail;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", personalEmail="
				+ personalEmail + ", universityEmail=" + universityEmail + ", registrationDate=" + registrationDate
				+ "]";
	}



}
