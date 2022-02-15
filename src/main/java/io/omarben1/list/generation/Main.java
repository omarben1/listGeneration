package io.omarben1.list.generation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.curiousoddman.rgxgen.RgxGen;

import io.omarben1.list.generation.annotation.Criterion;
import io.omarben1.list.generation.util.Defaults;


public class Main {
	
	public static void main(String[] args) throws Exception {
		List<Student> t = ListGenerator.getList(Student.class,10);
		t.forEach(System.out::println);
	
//		Exception e = new Exception();
//		Map<Object,String> l = new HashMap<Object, String>();
//		l.put(e, "hello");
//	System.out.println(l.get(e));
		
		
		
	}
}
