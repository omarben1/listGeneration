package io.omarben1.list.generation.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import io.omarben1.list.generation.annotation.Criterion;

public class Defaults implements InvocationHandler{
	public static <A extends Criterion> A of(Class<A> annotation) {
	    return (A) Proxy.newProxyInstance(annotation.getClassLoader(),
	        new Class[] {annotation}, new Defaults());
	  }
	  public Object invoke(Object proxy, Method method, Object[] args)
	      throws Throwable {
	    return method.getDefaultValue();
	  }
}
