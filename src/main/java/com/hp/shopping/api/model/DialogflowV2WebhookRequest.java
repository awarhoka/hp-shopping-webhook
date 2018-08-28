package com.hp.shopping.api.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class DialogflowV2WebhookRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@SerializedName("responseId")
	private String responseId;
	
	@SerializedName("session")
	private String session;
	
	@SerializedName("queryResult")
	private DialogflowV2QueryResult queryResult;
	
	@SerializedName("originalDetectIntentRequest")
	private DialogflowV2OriginalDetectIntentRequest originalDetectIntentRequest;

	public String getResponseId() {
		return responseId;
	}

	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public DialogflowV2QueryResult getQueryResult() {
		return queryResult;
	}

	public void setQueryResult(DialogflowV2QueryResult queryResult) {
		this.queryResult = queryResult;
	}

	public DialogflowV2OriginalDetectIntentRequest getOriginalDetectIntentRequest() {
		return originalDetectIntentRequest;
	}

	public void setOriginalDetectIntentRequest(DialogflowV2OriginalDetectIntentRequest originalDetectIntentRequest) {
		this.originalDetectIntentRequest = originalDetectIntentRequest;
	}

}
