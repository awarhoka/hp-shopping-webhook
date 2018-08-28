package com.hp.shopping.api.model;

import java.io.Serializable;
import java.util.Map;

public class DialogflowV2OriginalDetectIntentRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> payload;
	private java.lang.String source;
	
	public Map<String, Object> getPayload() {
		return payload;
	}

	public void setPayload(Map<String, Object> payload) {
		this.payload = payload;
	}

	public java.lang.String getSource() {
		return source;
	}

	public void setSource(java.lang.String source) {
		this.source = source;
	}


}
