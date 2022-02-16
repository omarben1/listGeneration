package io.omarben1.list.generation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.omarben1.list.generation.enumeration.DataType;


/**
 * Criterion annotation 
 * 
 * @author o.benchennouf
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Criterion {
	
	/**
	 * To specify the type of data which are defined as regEx in DataType enum.
	 * 
	 * @return DataType enum
	 */
	DataType data() default DataType.SIMPLE_NAME;
	
	/**
	 * To specify a custom regEx.
	 * 
	 * @return String
	 */
	String regex() default "";
	
	/**
	 * To specify the length of string.
	 * 
	 * @return String 
	 */
	String length() default "5";
	
	/**
	 * To specify the minimum value of a Number type (Integr, Float...).
	 * 
	 * @return String
	 */
	String minValue() default "0";
	
	/**
	 * To specify the maximum value of a Number type (Integr, Float...).
	 * 
	 * @return String
	 */
	String maxValue() default "";
	
	/**
	 * To specify the minimum date.
	 * 
	 * @return String
	 */
	String minDate() default "";
	
	/**
	 * To specify the maximum date.
	 * 
	 * @return String 
	 */
	String maxDate() default "";
}
