package com.hp.shopping.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2IntentMessage;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2IntentMessageCard;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2IntentMessageCardButton;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookRequest;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hp.shopping.api.DialogFlowService;
/*
@author warhokar
*/
@RestController
public class DialogFlowServiceImpl implements DialogFlowService {
   public static final Gson gson=new Gson(); 
	@Override
	public GoogleCloudDialogflowV2WebhookResponse dialogflowFulfillmentV2(HttpServletRequest request) {
	    final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	    try {
	        JsonObject reqJson = new JsonParser().parse(request.getReader()).getAsJsonObject();
	        System.out.println(reqJson);
	      }
	      catch (Exception ex) {
	      }
		  GoogleCloudDialogflowV2WebhookResponse response =new GoogleCloudDialogflowV2WebhookResponse();
				  response.setFulfillmentText("Hello Dialogflow");
			List<GoogleCloudDialogflowV2IntentMessage> fulfillmentMessages =new ArrayList<>(); 
			GoogleCloudDialogflowV2IntentMessage googleCloudDialogflowV2IntentMessage =new GoogleCloudDialogflowV2IntentMessage();
				  GoogleCloudDialogflowV2IntentMessageCard card = new GoogleCloudDialogflowV2IntentMessageCard();
				  java.util.List<GoogleCloudDialogflowV2IntentMessageCardButton> buttons =new  ArrayList<>(); 
				  GoogleCloudDialogflowV2IntentMessageCardButton button = new  GoogleCloudDialogflowV2IntentMessageCardButton();
				  button.setText("Test Button"); 
				  button.setPostback("http://www.google.com");
				  buttons.add(button);
				  card.setTitle("This Is HP test Card"); card.setButtons(buttons);
				  card.setImageUri("http://www.www8-hp.com/us/en/images/i_pro_02_probook_450_tcm245_2192435_tcm245_2193324_tcm245-2192435.jpg"); 
				  card.setSubtitle("This is Subtitle");
				  googleCloudDialogflowV2IntentMessage.setCard(card);
				  fulfillmentMessages.add(googleCloudDialogflowV2IntentMessage);
				  response.setFulfillmentMessages(fulfillmentMessages);
		return response;
	}
	@Override
	public GoogleCloudDialogflowV2WebhookResponse dialogflowFulfillmentV2v1(@RequestBody GoogleCloudDialogflowV2WebhookRequest jsonObject) {
		System.out.println("JSON OBJECT : "+jsonObject);
		return new GoogleCloudDialogflowV2WebhookResponse().setFulfillmentText("Hi There");
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
