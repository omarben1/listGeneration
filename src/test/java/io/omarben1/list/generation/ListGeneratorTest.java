package io.omarben1.list.generation;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

import io.omarben1.list.generation.beans.Student;

public class ListGeneratorTest {
	
	
	public boolean checkFieldsNotNull(List<Student> students){
		
		return students.stream()
					   .allMatch(st -> !st.getFirstName().isEmpty() 
								    && !st.getLastName().isEmpty()
								    && !st.getPersonalEmail().isEmpty()
								    && st.getAge()!=null
								    && st.getRegistrationDate()!=null);
	}
	
	public boolean checkGeneratedStringsMatchRegex(List<Student> students) {
		String emailRegEx = "^[A-Z0-9._%+-]{5,6}@[A-Z0-9.-]{3,4}\\.[A-Z]{2,6}$";
		String nameRegEx  = "^[A-Z][a-z]{4,7}$";
		
		return students.stream()
				       .allMatch(st -> st.getPersonalEmail().matches(emailRegEx)
									&& st.getFirstName().matches(nameRegEx)
									&& st.getLastName().matches(nameRegEx));
	}
	
	public boolean checkGeneratedStringsMatchCustomRegex(List<Student> students) {
		String customRegEx = "^[A-Z0-9._%+-]{5,6}@ump\\.[A-Z]{2,6}$";
		
		return students.stream()
				       .allMatch(st -> st.getUniversityEmail().matches(customRegEx));
	}
	@Test
	public void annotationTest(){
		List<Student> students = ListGenerator.getList(Student.class, 10);
		assert checkFieldsNotNull(students);
		assert checkGeneratedStringsMatchRegex(students);
		students.forEach(System.out::println);
		assert checkGeneratedStringsMatchCustomRegex(students);
	}
	
	@Test
	public void defaultAnnotationValuesTest() {
		List<Student> students = ListGenerator.getList(Student.class, 10);
		assert checkFieldsNotNull(students);
	}
}
