package org.myproject.orderservice.utils;

import java.util.UUID;
import lombok.experimental.UtilityClass;

/**
 * @author nguyenle
 * @since 1:30 PM Wed 12/18/2024
 */
@UtilityClass
public class UUIDUtils {

	public static String getRandomUUID() {
		return UUID.randomUUID().toString();
	}

}
