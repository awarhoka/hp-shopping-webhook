package com.hp.shopping.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2OriginalDetectIntentRequest;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2QueryResult;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookRequest;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookResponse;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.hp.shopping.api.AppConstants;
import com.hp.shopping.api.DialogFlowService;
import com.hp.shopping.business.DialogFlowRequestHandler;
/*
@author warhokar
*/
@RestController
public class DialogFlowServiceImpl implements DialogFlowService, AppConstants{
   public static final Gson gson=new Gson(); 
   @Autowired
   DialogFlowRequestHandler dialogFlowRequestHandler;
	@Override
	//public GoogleCloudDialogflowV2WebhookResponse dialogflowFulfillmentV2(HttpServletRequest request) {
	public GoogleCloudDialogflowV2WebhookResponse dialogflowFulfillmentV2(HttpEntity<String> httpEntity) {
	    final HttpHeaders httpHeaders= httpEntity.getHeaders();
	    System.out.println("Dialogflow Request Headers: "+ httpHeaders.toString());
	    final String requestBody= httpEntity.getBody();
	    System.out.println("Dialogflow Request Body : "+ requestBody);
	    GoogleCloudDialogflowV2WebhookRequest dialogflowV2WebhookRequestObj = this.buildDialogFlowRequest(requestBody);
	    return dialogFlowRequestHandler.handleDialogFlowV2Request(dialogflowV2WebhookRequestObj);
	}
	@Override
	public GoogleCloudDialogflowV2WebhookResponse dialogflowFulfillmentV2v1(@RequestBody GoogleCloudDialogflowV2WebhookRequest jsonObject) {
		System.out.println("JSON OBJECT : "+jsonObject);
		return new GoogleCloudDialogflowV2WebhookResponse().setFulfillmentText("Hi There");
	}
	
	private GoogleCloudDialogflowV2WebhookRequest buildDialogFlowRequest(String requestBody) {
		GoogleCloudDialogflowV2WebhookRequest googleCloudDialogflowV2WebhookRequest =new GoogleCloudDialogflowV2WebhookRequest();
		 try {
			JsonObject reqJson = new JsonParser().parse(requestBody).getAsJsonObject();
			googleCloudDialogflowV2WebhookRequest.setResponseId(reqJson.get(RESPONSEID).getAsString());
			googleCloudDialogflowV2WebhookRequest.setSession(reqJson.get(SESSION).getAsString());
			googleCloudDialogflowV2WebhookRequest.setQueryResult(this.getGoogleCloudDialogflowV2QueryResult(reqJson.getAsJsonObject(QUERYRESULT)));
			googleCloudDialogflowV2WebhookRequest.setOriginalDetectIntentRequest(this.getOrigionalDetectIntentRequest(reqJson.getAsJsonObject(ORIGIONAL_DETECT_INTENT_REQUEST)));

		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return googleCloudDialogflowV2WebhookRequest;
	}
	private GoogleCloudDialogflowV2QueryResult getGoogleCloudDialogflowV2QueryResult(JsonObject queryResult) {
		GoogleCloudDialogflowV2QueryResult  dialogflowV2QueryResult = new GoogleCloudDialogflowV2QueryResult();
		dialogflowV2QueryResult.setQueryText(queryResult.get(QUERYTEXT).getAsString());
		dialogflowV2QueryResult.setAction(queryResult.get(ACTION).getAsString());
		dialogflowV2QueryResult.setParameters(this.getQueryResultParameters(queryResult.getAsJsonObject(PARAMETERS)));
		dialogflowV2QueryResult.setAllRequiredParamsPresent(queryResult.get(ALL_REQUIREF_PARAMS_PRESENT).getAsBoolean());
		dialogflowV2QueryResult.setLanguageCode(queryResult.get(LANGUAGE_CODE).getAsString());
		dialogflowV2QueryResult.setIntentDetectionConfidence(queryResult.get(INTENT_DETECT_CONFIDENCE).getAsFloat());
		return dialogflowV2QueryResult;
	}
	/**
	 * @return Map<String, Object>
	 * @param parameters
	 */
	private Map<String, Object> getQueryResultParameters(JsonObject parameters) {
		Map<String, Object> dialogflowV2QueryResultParams =new HashMap<>();
		Set<String> paramKeys =parameters.keySet();
		if(paramKeys!=null &&  paramKeys.size()> 0) {
			Iterator<String> keysItr = paramKeys.iterator();
			while(keysItr.hasNext()) {
				String keyStr = keysItr.next();
				String value = parameters.get(keyStr).getAsString();
				dialogflowV2QueryResultParams.put(keyStr, value);
			}
		}
		return dialogflowV2QueryResultParams;
	}
	
	/**
	 * @param originalRequest
	 */
	private GoogleCloudDialogflowV2OriginalDetectIntentRequest getOrigionalDetectIntentRequest(JsonObject originalRequest) {
		GoogleCloudDialogflowV2OriginalDetectIntentRequest originalDetectIntentRequest = new GoogleCloudDialogflowV2OriginalDetectIntentRequest();
		if(null != originalRequest &&  originalRequest.size() > 1 ) {
			String origionalRequestSource =originalRequest.get(SOURCE).getAsString();
			originalDetectIntentRequest.setSource(origionalRequestSource);
			JsonObject payload = originalRequest.getAsJsonObject(PAYLOAD);
			Map<String, Object> origionalRequestPayload = new HashMap<>();
			origionalRequestPayload.put(SOURCE, payload.get(SOURCE).getAsString());	
			origionalRequestPayload.put(DATA, payload.get(DATA));
			originalDetectIntentRequest.setPayload(origionalRequestPayload);
		}
		return originalDetectIntentRequest;
	}
	
	 /*
	@Override
	public GoogleCloudDialogflowV2beta1WebhookResponse dialogflowFulfillmentV2beta1(JsonObject jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}
	  @Override
	public GoogleCloudDialogflowV2beta1WebhookResponse dialogflowFulfillmentV2beta1v1(
			GoogleCloudDialogflowV2beta1WebhookRequest jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GoogleCloudDialogflowV2WebhookResponse dialogflowFulfillmentV2v2(@RequestBody GenericJson jsonObject) {
		Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
		String json = gson1.toJson(jsonObject);
    	System.out.println("Object String :"+json);
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new StringReader(jsonObject.toString()));
		reader.setLenient(true);
		JsonFactory factory= jsonObject.getFactory();
		JsonObjectParser parser= factory.createJsonObjectParser();
		try {
			parser.parseAndClose(new StringReader(jsonObject.toString()), GenericJson.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GoogleCloudDialogflowV2WebhookRequest request = gson.fromJson(reader, GoogleCloudDialogflowV2WebhookRequest.class);
		
		//GoogleCloudDialogflowV2WebhookRequest request = gson.fromJson(jsonObject.toString(), GoogleCloudDialogflowV2WebhookRequest.class);
		
		if(null != request) {
			gson1 = new GsonBuilder().setPrettyPrinting().create();
	    	json = gson1.toJson(request);
	    	System.out.println("Object String :"+json);
		}
		System.out.println("Action is :"+request.getResponseId()+" "+request.getSession());
		
		return new GoogleCloudDialogflowV2WebhookResponse().setFulfillmentText("Hi There");
	}*/
}
