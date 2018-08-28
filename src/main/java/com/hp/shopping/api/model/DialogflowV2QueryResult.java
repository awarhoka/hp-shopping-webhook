package com.hp.shopping.api.model;

import java.io.Serializable;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class DialogflowV2QueryResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@SerializedName("queryText")
	private String queryText;
	
	@SerializedName("action")
	private String action;
	
	@SerializedName("allRequiredParamsPresent")
	private boolean allRequiredParamsPresent;
	
	@SerializedName("languageCode")
	private String languageCode;
	
	@SerializedName("diagnosticInfo")
	private Map<String, Object> diagnosticInfo;
	
	@SerializedName("parameters")
	private Map<String, Object> parameters;
	
	@SerializedName("speechRecognitionConfidence")
	private Float speechRecognitionConfidence;
	
	@SerializedName("webhookPayload")
	private Map<String, Object> webhookPayload;
	
	@SerializedName("webhookSource")
	private String webhookSource;
	// private List<DialogflowV2IntentMessage> fulfillmentMessages;
	@SerializedName("fulfillmentText")
	private String fulfillmentText;
	// private DialogflowV2Intent intent;
	// private List<DialogflowV2Context> outputContexts;
	
	public String getQueryText() {
		return queryText;
	}
	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public boolean isAllRequiredParamsPresent() {
		return allRequiredParamsPresent;
	}
	public void setAllRequiredParamsPresent(boolean allRequiredParamsPresent) {
		this.allRequiredParamsPresent = allRequiredParamsPresent;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public Map<String, Object> getDiagnosticInfo() {
		return diagnosticInfo;
	}
	public void setDiagnosticInfo(Map<String, Object> diagnosticInfo) {
		this.diagnosticInfo = diagnosticInfo;
	}
	public Map<String, Object> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}
	public Float getSpeechRecognitionConfidence() {
		return speechRecognitionConfidence;
	}
	public void setSpeechRecognitionConfidence(Float speechRecognitionConfidence) {
		this.speechRecognitionConfidence = speechRecognitionConfidence;
	}
	public Map<String, Object> getWebhookPayload() {
		return webhookPayload;
	}
	public void setWebhookPayload(Map<String, Object> webhookPayload) {
		this.webhookPayload = webhookPayload;
	}
	public String getWebhookSource() {
		return webhookSource;
	}
	public void setWebhookSource(String webhookSource) {
		this.webhookSource = webhookSource;
	}
	public String getFulfillmentText() {
		return fulfillmentText;
	}
	public void setFulfillmentText(String fulfillmentText) {
		this.fulfillmentText = fulfillmentText;
	}
}
