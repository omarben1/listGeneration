package io.omarben1.list.generation.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.github.curiousoddman.rgxgen.RgxGen;

import io.omarben1.list.generation.annotation.Criterion;


/**
 * Generators class to get random value of a given type based on certain criterion.
 * 
 * @author o.benchennouf
 *
 */
public class Generators {
	
	public static LocalDate generateRandomDate(Criterion criterion){
		String minDate = criterion.minDate().isEmpty() ? "1900-01-01" : criterion.minDate();
		String maxDate = criterion.maxDate().isEmpty() ? "2030-01-01" : criterion.maxDate();
		long minDay = 0;
		long maxDay = 0;
		try {
			minDay = LocalDate.parse(minDate).toEpochDay();
		    maxDay = LocalDate.parse(maxDate).toEpochDay();
		}catch (DateTimeParseException e) {
			System.out.println("The date format is invalid, make sure to use this format "
					+ "yyyy-mm-dd, example : 1902-01-03");
		}
	    long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
	    LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
	    return randomDate;
	}
	
	public static double generateRandomNumber(Criterion criterion) {
		Double minValue = criterion.maxValue().isEmpty() ? Float.MIN_VALUE : Double.valueOf(criterion.minValue());
		Double maxValue = criterion.maxValue().isEmpty() ? Float.MAX_VALUE : Double.valueOf(criterion.maxValue());
		return ThreadLocalRandom.current().nextDouble(minValue, maxValue);
	}
	
	public static String generateRandomString(Criterion criterion) {
		String regex = criterion.regex().isEmpty() ? criterion.data().getRegex() : criterion.regex();
		String randomString = new RgxGen(regex).generate();
		return randomString;
	}
	
	public static Boolean generateRandomBoolean(Criterion criterion) {
		return Math.random() > 0.5;
	}
	
	public static Character generateRandomChar(Criterion criterion) {
		return (char) (new Random().nextInt(26) + 'a');
	}
}
