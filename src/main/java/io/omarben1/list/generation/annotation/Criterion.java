package io.omarben1.list.generation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.omarben1.list.generation.enumeration.DataType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Criterion {
	DataType data() default DataType.SIMPLE_NAME;
	String regex() default "";
	String length() default "5";
	String minValue() default "0";
	String maxValue() default "";
	String minDate() default "";
	String maxDate() default "";
}
