package com.qsense.util;

import java.util.List;

public class MathUtils {
	public static double average(List<Double> list) {
		int count = 0;
		if ((count = count(list)) > 0) {
			return sum(list) / count;
		}
		return 0;
	}

	public static double sum(List<Double> list) {
		double sum = 0;
		for (Double d : list) {
			sum += d;
		}
		return sum;
	}

	public static int count(List<Double> list) {
		return list.size();
	}
}
