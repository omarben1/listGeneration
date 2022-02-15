package io.omarben1.list.generation.util;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class Types {
	
	//Map each primitive to its wrapper type
	private static final Map<Class<?>, Class<?>> PRIMITIVES_TO_WRAPPERS = new ImmutableMap.Builder<Class<?>, Class<?>>()
			.put(boolean.class, Boolean.class)
			.put(byte.class, Byte.class)
			.put(char.class, Character.class)
			.put(double.class, Double.class)
			.put(float.class, Float.class)
			.put(int.class, Integer.class)
			.put(long.class, Long.class)
			.put(short.class, Short.class)
			.put(void.class, Void.class)
			.build();

	//Get the wrapper type if the given type is primitive otherwise return it
	public static <T> Class<T> wrap(Class<T> c) {
		return c.isPrimitive() ? (Class<T>) PRIMITIVES_TO_WRAPPERS.get(c) : c;
	}
}
