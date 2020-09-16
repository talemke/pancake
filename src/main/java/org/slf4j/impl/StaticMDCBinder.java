package org.slf4j.impl;

import org.slf4j.spi.MDCAdapter;

public class StaticMDCBinder {
	public static final StaticMDCBinder SINGLETON = new StaticMDCBinder();
	private final MDCAdapter mdcAdapter = new MDCAdapterImpl();

	private StaticMDCBinder() {
	}

	public MDCAdapter getMDCA() {
		return mdcAdapter;
	}

}
