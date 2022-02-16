package io.omarben1.list.generation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.google.common.collect.ImmutableMap;

import io.omarben1.list.generation.annotation.Criterion;
import io.omarben1.list.generation.annotation.Ignore;
import io.omarben1.list.generation.util.Defaults;
import io.omarben1.list.generation.util.Generators;
import io.omarben1.list.generation.util.WrapperTypes;


/**
 * ListGenerator class to generate a list for a given type with randomized values.
 * 
 * @author o.benchennouf
 *
 */
public class ListGenerator {

	//Map each wrapper type to its generator Function
	public static final Map<Class<?>, Function<Criterion, ?>> TYPES_TO_GENERATORS = new ImmutableMap.Builder<Class<?>, Function<Criterion, ?>>()
			.put(Double.class, (Criterion criterion) -> Generators.generateRandomNumber(criterion))
			.put(Float.class, (Criterion criterion) -> {
				return (float) Generators.generateRandomNumber(criterion);
			})
			.put(Integer.class, (Criterion criterion) -> {
				return (int) Generators.generateRandomNumber(criterion);
			})
			.put(Long.class, (Criterion criterion) -> {
				return (long) Generators.generateRandomNumber(criterion);
			})
			.put(String.class, (Criterion criterion) -> Generators.generateRandomString(criterion) )
			
			.put(LocalDate.class, (Criterion criterion) -> Generators.generateRandomDate(criterion))
			.put(Boolean.class, (Criterion criterion) -> Generators.generateRandomBoolean(criterion))
			.put(Character.class, (Criterion criterion) -> Generators.generateRandomChar(criterion))
			.build();

	
	
	/**
	 * @param <T>
	 * @param clazz the type to generate the list for.
	 * @param listLength the length of the list to be generated.
	 * @return list of the given type.
	 */
	public static <T> List<T> getList(Class<T> clazz, int listLength) {

		List<T> list = new ArrayList<T>();

		Constructor<T> con = null;
		try {
			con = clazz.getConstructor();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		}

		T obj = null;

		//Get all declared fields in clazz type
		Field[] allFields = clazz.getDeclaredFields();
		
		//Get fileds that are not ignored through ignore annotation
		//and their types have a generator method in the map of generators
		List<Field> notIgnoredFields = Arrays.asList(allFields).stream()
				.filter(field -> !field.isAnnotationPresent(Ignore.class))
				.filter(field -> isFieldTypeHasGenerator(field))
				.collect(Collectors.toList());
		
		for (int i = 0; i < listLength; i++) {
			//Create "listLength" times an instance of clazz type
			try {
				obj = con.newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e1) {
				e1.printStackTrace();
			}
			//Set randomized values to all fields that are not ignored
			for(Field field : notIgnoredFields) {
				field.setAccessible(true);
				try {
					field.set(obj, getRandomValueBasedOnCriterion(field));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			list.add(obj);
		}

		return list;
	}

	private static Object getRandomValueBasedOnCriterion(Field field) {
		return getValue(WrapperTypes.wrap(field.getType()), field.getAnnotation(Criterion.class));
	}

	/**
	 * check if the type of a given field contained in the map which map each type to 
	 * its generator function
	 * @param field the field to be checked
	 * @return boolean 
	 */
	private static boolean isFieldTypeHasGenerator(Field field) {
		return TYPES_TO_GENERATORS.containsKey(WrapperTypes.wrap(field.getType()));
	}
	

	/**
	 * Get the Criterion annotation instance if it's present otherwise return 
	 * an instance with default values
	 * @param <T>
	 * @param clazz
	 * @param criterion
	 * @return
	 */
	private static <T> T getValue(Class<T> clazz, Criterion criterion) {
		criterion = criterion == null ? Defaults.of(Criterion.class) : criterion;
		return (T) TYPES_TO_GENERATORS.get(clazz).apply(criterion);
	}

	
}
