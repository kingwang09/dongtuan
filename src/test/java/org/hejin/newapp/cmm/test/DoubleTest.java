package org.hejin.newapp.cmm.test;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.junit.Test;

public class DoubleTest {
	@Test
	public void test() throws ParseException{
		Double value = 3.3333333333;
		DecimalFormat formatter = new DecimalFormat("#.##");
		System.out.println(formatter.format(value));
		System.out.println(formatter.parse(value.toString()));
	}
}
