/**
 * 
 */
package com.hp.shopping.business.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2OriginalDetectIntentRequest;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2QueryResult;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookRequest;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hp.shopping.api.AppConstants;

/**
 * @author warhokar
 *
 */
@Service(value = "DialogFlowRequestHandler")
public class DialogFlowRequestHandlerImpl implements DialogFlowRequestHandler {
    /*@Autowired
  	private Messenger messenger;*/
    
    final String FB_GRAPH_API_URL_USER = "https://graph.facebook.com/%s?fields=first_name,last_name&access_token=%s";
	 
    
    @Value("${messenger4j.pageAccessToken}") 
    private String pageAccessToken;
    
    public static final Gson gson=new Gson(); 
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hp.shopping.business.DialogFlowRequestHandler#handleDialogFlowV2Request(
	 * com.google.api.services.dialogflow.v2.model.
	 * GoogleCloudDialogflowV2WebhookRequest)
	 */
	@Override
	public GoogleCloudDialogflowV2WebhookResponse handleDialogFlowV2Request(
			GoogleCloudDialogflowV2WebhookRequest dialogflowV2WebhookRequest) {
		GoogleCloudDialogflowV2OriginalDetectIntentRequest origionalRequest =dialogflowV2WebhookRequest.getOriginalDetectIntentRequest();
		GoogleCloudDialogflowV2QueryResult queryResult =dialogflowV2WebhookRequest.getQueryResult();
		GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
		if(null != origionalRequest && origionalRequest.size() > 0 ) {
			switch (origionalRequest.getSource()) {
			case "facebook" :
				 String payloadString = gson.toJson(origionalRequest.getPayload());
				 JsonObject payload =  new JsonParser().parse(payloadString).getAsJsonObject();
				 JsonObject data = payload.get(AppConstants.DATA).getAsJsonObject();
				if(data != null && data.size() > 0 ) {
					JsonObject postback= data.get("postback") == null ? null : data.get("postback").getAsJsonObject();
					if(null != postback && postback.size() > 0) {
						String payloadVal = postback.get(AppConstants.PAYLOAD).getAsString();
						String titleVal =   postback.get("title").getAsString();;
						if(payloadVal.equalsIgnoreCase("first-handshake") && titleVal.equals("Get Started")) {
							JsonObject sender= data.get("sender").getAsJsonObject();
							String senderID= sender.get("id").getAsString();
							String requestUrl = String.format(FB_GRAPH_API_URL_USER,senderID, pageAccessToken);
							ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(requestUrl, String.class);
							
							if(null!= responseEntity && responseEntity.getStatusCode().equals(HttpStatus.OK)) {
								JsonObject userProfile =  new JsonParser().parse(responseEntity.getBody()).getAsJsonObject();
								System.out.println("Response Entity:   "+ userProfile);
								String firstName = userProfile.get("first_name").getAsString();
							    String lastName = userProfile.get("last_name").getAsString() ; 
							    response.setFulfillmentText("Hello " +firstName+" "+lastName+" Welcome Hp Shopping! How may i Help You?");
								System.out.println("USERINFO :"+firstName+" "+lastName);
							}
							
							
						    
							/*try {
								 String requestUrl = String.format(FB_GRAPH_API_URL_USER,senderID, pageAccessToken);
								 URL newURL = new URL(requestUrl);
								 System.out.println(requestUrl);
							     ByteArrayOutputStream baos = new ByteArrayOutputStream();
							     InputStream is = newURL.openStream();
							     int r;
							        while ((r = is.read()) != -1) {
							            baos.write(r);
							        }
							        String fbresponse = new String(baos.toByteArray());
							        JsonObject userProfile =  new JsonParser().parse(fbresponse).getAsJsonObject();
							        baos.close();
							        
								String firstName = userProfile.get("first_name").getAsString();
							    String lastName = userProfile.get("last_name").getAsString() ; 
								
							    
							    //List<GoogleCloudDialogflowV2Context> outputContexts =queryResult.getOutputContexts();
							    //GoogleCloudDialogflowV2Context welcomeintentcontext =null;
							    /*for (GoogleCloudDialogflowV2Context googleCloudDialogflowV2Context : outputContexts) {
							    	if(googleCloudDialogflowV2Context.getName().contains("welcomeintentcontext")) {
							    		welcomeintentcontext=googleCloudDialogflowV2Context;
							    		
							    		break;
							    	}
								}
							    outputContexts.remove(welcomeintentcontext);
							    Map<String, Object> parameters = new HashMap<>();
								parameters.put(senderID, firstName+" "+lastName);
								welcomeintentcontext.setParameters(parameters);
								outputContexts.add(welcomeintentcontext);
								response.setOutputContexts(outputContexts);*/
					    		
							    /*if(null != outputContexts &&  outputContexts.size() >0 ) {
							    	 GoogleCloudDialogflowV2Context context outputContexts.get(index);
							    }
							    for (GoogleCloudDialogflowV2Context googleCloudDialogflowV2Context : outputContexts) {
							    	Map<String, Object> parameters = googleCloudDialogflowV2Context.getParameters();
								}
							    
							    GoogleCloudDialogflowV2Context welcomeContext outputContexts.get(index);
								welcomeContext.setName("WelcomeIntentContext");
								welcomeContext.setLifespanCount(5);
								Map<String, Object> parameters = new HashMap<>();
								parameters.put(senderID, firstName+" "+lastName);
								welcomeContext.setParameters(parameters);
								outputContexts.add(welcomeContext);
								response.setOutputContexts(outputContexts);								response.setFulfillmentText("Hello " +firstName+" "+lastName+" Welcome Hp Shopping! How may i Help You?");
								System.out.println("USERINFO :"+firstName+" "+lastName);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}*/			
						}
					}else {
					}

				}
				//final String querytext = queryResult.getQueryText();
				break;
			case "slack" :
				break;
			case "actions on google":
				break;
			case "google":
				break;
			}
			
		}
		
		//System.out.println(dialogflowV2WebhookRequest.toString());
		
		
		//List<GoogleCloudDialogflowV2IntentMessage> fulfillmentMessages = new ArrayList<>();
		//GoogleCloudDialogflowV2IntentMessage googleCloudDialogflowV2IntentMessage = new GoogleCloudDialogflowV2IntentMessage();
		/*GoogleCloudDialogflowV2IntentMessageCard card = new GoogleCloudDialogflowV2IntentMessageCard();
		
		java.util.List<GoogleCloudDialogflowV2IntentMessageCardButton> buttons = new ArrayList<>();
		GoogleCloudDialogflowV2IntentMessageCardButton button = new GoogleCloudDialogflowV2IntentMessageCardButton();
		button.setText("Test Button");
		button.setPostback("http://www.google.com");
		buttons.add(button);
		card.setTitle("This Is HP test Card");
		card.setButtons(buttons);
		card.setImageUri(
				"http://www.www8-hp.com/us/en/images/i_pro_02_probook_450_tcm245_2192435_tcm245_2193324_tcm245-2192435.jpg");
		card.setSubtitle("This is Subtitle");
		googleCloudDialogflowV2IntentMessage.setCard(card);*/
		/*GoogleCloudDialogflowV2IntentMessageCarouselSelect carouselSelect = new GoogleCloudDialogflowV2IntentMessageCarouselSelect();
		
		List<GoogleCloudDialogflowV2IntentMessageCarouselSelectItem> items =new ArrayList<>();
		GoogleCloudDialogflowV2IntentMessageCarouselSelectItem item1 = new GoogleCloudDialogflowV2IntentMessageCarouselSelectItem();
		item1.setTitle("Carousel Select Item1");
		item1.setDescription("Carousel Select Item1 Description");
		GoogleCloudDialogflowV2IntentMessageImage image1 = new GoogleCloudDialogflowV2IntentMessageImage();
		image1.setAccessibilityText("This is Image Accessibility Text");
		image1.setImageUri("http://www.www8-hp.com/us/en/images/i_pro_02_probook_450_tcm245_2192435_tcm245_2193324_tcm245-2192435.jpg");
		item1.setImage(image1);
		GoogleCloudDialogflowV2IntentMessageSelectItemInfo info1 = new GoogleCloudDialogflowV2IntentMessageSelectItemInfo();
		info1.setKey("Key 1");
		List<String> synonyms1 = new ArrayList<>();
		synonyms1.add("Key 1  Synonym 1");
		synonyms1.add("Key 1  Synonym 2");
		synonyms1.add("Key 1  Synonym 3");
		info1.setSynonyms(synonyms1);
		item1.setInfo(info1);
		items.add(item1);
		GoogleCloudDialogflowV2IntentMessageCarouselSelectItem item2 = new GoogleCloudDialogflowV2IntentMessageCarouselSelectItem();
		item2.setTitle("Carousel Select Item2");
		item2.setDescription("Carousel Select Item2 Description");
		GoogleCloudDialogflowV2IntentMessageImage image2 = new GoogleCloudDialogflowV2IntentMessageImage();
		image2.setAccessibilityText("This is Image Accessibility Text");
		image2.setImageUri("https://ssl-product-images.www8-hp.com/digmedialib/prodimg/lowres/c05903985.png");
		item2.setImage(image2);
		GoogleCloudDialogflowV2IntentMessageSelectItemInfo info2 = new GoogleCloudDialogflowV2IntentMessageSelectItemInfo();
		info2.setKey("Key 2");
		List<String> synonyms2 = new ArrayList<>();
		synonyms2.add("Key 2  Synonym 1");
		synonyms2.add("Key 2  Synonym 2");
		synonyms2.add("Key 2  Synonym 3");
		info2.setSynonyms(synonyms2);
		
		item2.setInfo(info2);
		items.add(item2);
		carouselSelect.setItems(items);
		googleCloudDialogflowV2IntentMessage.setCarouselSelect(carouselSelect);
		fulfillmentMessages.add(googleCloudDialogflowV2IntentMessage);
		
		Map<String, Object> origionalRequestPayload = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		data.put("facebook", carouselSelect);
		data.put("google", carouselSelect);
		data.put("slack", carouselSelect);
		origionalRequestPayload.put("data", data);*/
		
		
		//originalDetectIntentRequest.setPayload(origionalRequestPayload);
		
		//response.setPayload(origionalRequestPayload);
		//response.setFulfillmentMessages(fulfillmentMessages);
		System.out.println("This is generated response"+ response);
		return response;
	}

}
