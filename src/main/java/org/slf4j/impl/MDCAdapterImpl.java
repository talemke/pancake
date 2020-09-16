package org.slf4j.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.spi.MDCAdapter;

class MDCAdapterImpl implements MDCAdapter {
	private Map<String, String> map = new HashMap<>();

	@Override
	public void put(String key, String val) {
		map.put(key, val);
	}

	@Override
	public String get(String key) {
		return map.get(key);
	}

	@Override
	public void remove(String key) {
		map.remove(key);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Map<String, String> getCopyOfContextMap() {
		return new HashMap<>(map);
	}

	@Override
	public void setContextMap(Map contextMap) {
		this.map = contextMap;
	}

}
