package io.omarben1.list.generation;

import java.util.List;

import org.junit.Test;

import io.omarben1.list.generation.beans.Book;
import io.omarben1.list.generation.beans.Product;
import io.omarben1.list.generation.beans.Student;

public class ListGeneratorTest {
	
	
	public boolean checkStudentFieldsNotNull(List<Student> students){
		
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
		assert checkStudentFieldsNotNull(students);
		assert checkGeneratedStringsMatchRegex(students);
		students.forEach(System.out::println);
		assert checkGeneratedStringsMatchCustomRegex(students);
	}
	
	public boolean checkProductFieldsNotNull(List<Product> products) {
		
		return 	products.stream()
						.allMatch(product -> !product.getName().isEmpty()
									   	   && product.getPrice()!=null
									       && product.getQuantity()!=null);
	}
	@Test
	public void defaultAnnotationValuesTest() {
		List<Product> products = ListGenerator.getList(Product.class, 11);
		assert checkProductFieldsNotNull(products);
	}
	
	public boolean checkIgnoredFiledsAreNull(List<Book> books) {
		return books.stream()
					.allMatch(book -> book.getName()==null);
	}
	
	@Test
	public void ignoreAnnotationTest() {
		List<Book> books = ListGenerator.getList(Book.class, 5);
		assert checkIgnoredFiledsAreNull(books);
	}
}
