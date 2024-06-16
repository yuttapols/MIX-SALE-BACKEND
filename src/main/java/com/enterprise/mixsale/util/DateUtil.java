package com.enterprise.mixsale.util;

import java.sql.Timestamp;

public class DateUtil {

	public static Timestamp createTimestmapNow() {
		
		return new Timestamp(System.currentTimeMillis());
	}
}
