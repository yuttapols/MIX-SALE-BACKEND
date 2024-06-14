package com.enterprise.mixsale.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FunctionUtil {

	public static String genarateEmpNo(Long departmentId, Long userId) {

		StringBuilder result = new StringBuilder();
		
		Calendar today = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		String date = format1.format(today.getTime());

		result.append(Constants.INITIALS_NAME_PROJECT);
		result.append(String.format("%03d", Integer.parseInt(departmentId.toString())));
		result.append(date);
		result.append(String.format("%03d", Integer.parseInt(userId.toString())));
		
		return result.toString();
	}
}
