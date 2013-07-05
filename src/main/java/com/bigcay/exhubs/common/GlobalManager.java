package com.bigcay.exhubs.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;

public class GlobalManager {

	public static final int DEFAULT_PAGE_SIZE = 5;

	public static Map<String, Object> getGlobalPageableMap(Page<?> page) {
		return GlobalManager.getGlobalPageableMap(page, "");
	}
	
	public static Map<String, Object> getGlobalPageableMap(Page<?> page, String identifier) {

		Map<String, Object> pageableMap = new HashMap<String, Object>();

		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, page.getTotalPages());
		int totalPages = page.getTotalPages();

		pageableMap.put("currentIndex" + identifier, current);
		pageableMap.put("beginIndex" + identifier, begin);
		pageableMap.put("endIndex" + identifier, end);
		pageableMap.put("totalPages" + identifier, totalPages);

		return pageableMap;
	}
	
}
