
package com.jju.util;

import java.util.UUID;


public class StringUtil {
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("\\-", "");
	}
}
