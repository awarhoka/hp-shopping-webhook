/**
 * 
 */
package com.hp.shopping.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2IntentMessage;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2IntentMessageCard;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2IntentMessageCardButton;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2OriginalDetectIntentRequest;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookRequest;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookResponse;
import com.google.cloud.dialogflow.v2.WebhookRequest;
import com.google.cloud.dialogflow.v2.WebhookResponse;
import com.google.gson.Gson;
import com.hp.shopping.api.model.AIResponse;
import com.hp.shopping.api.model.EmployeeModel;
import com.hp.shopping.api.model.Fulfillment;


/**
 * @author warhokar
 *
 */
@RestController
public class DialogFlowService {

	  private final Gson gson = GsonFactory.getDefaultFactory().getGson();

	@PostMapping("/webhook")
	public ResponseEntity<String> webHookFullfillment(HttpEntity<String> httpEntity) throws JSONException {
		JSONObject obj = new JSONObject();
		String response = "Hello from Java";
		obj.put("fulfillmentText", response);
		
		return new ResponseEntity<String>(obj.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/webhook1", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> webHookFullfillment1(HttpEntity<String> httpEntity) throws JSONException {
		Fulfillment output = new Fulfillment();
		HttpHeaders headers = httpEntity.getHeaders();
		
		AIWebhookRequest input = gson.fromJson(httpEntity.getBody(), AIWebhookRequest.class);
		System.out.println(gson.toJson(input));
	    output.setFulfillmentText("Hello java");
	    ResponseEntity<String> entity = new ResponseEntity<String>(gson.toJson(output),HttpStatus.OK);
	    return entity;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/fulfillment2", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getFulfillment(HttpEntity<String> httpEntity) {
		GoogleCloudDialogflowV2OriginalDetectIntentRequest intent =gson.fromJson(httpEntity.getBody(), GoogleCloudDialogflowV2OriginalDetectIntentRequest.class);
		//System.out.println("Response ID Here :"+intent.getPayload().toString());
		GoogleCloudDialogflowV2WebhookResponse response =new GoogleCloudDialogflowV2WebhookResponse();
		response.setFulfillmentText("Hello Dialogflow");
		java.util.List<GoogleCloudDialogflowV2IntentMessage> fulfillmentMessages =new ArrayList<>();
		GoogleCloudDialogflowV2IntentMessage googleCloudDialogflowV2IntentMessage =new GoogleCloudDialogflowV2IntentMessage();
		GoogleCloudDialogflowV2IntentMessageCard card = new GoogleCloudDialogflowV2IntentMessageCard();
		java.util.List<GoogleCloudDialogflowV2IntentMessageCardButton> buttons =new ArrayList<>();
		GoogleCloudDialogflowV2IntentMessageCardButton button = new GoogleCloudDialogflowV2IntentMessageCardButton();
		button.setText("Test Button");
		button.setPostback("http://www.google.com");
		buttons.add(button);
		
		card.setTitle("This Is HP test Card");
		card.setButtons(buttons);
		card.setImageUri("http://www.www8-hp.com/us/en/images/i_pro_02_probook_450_tcm245_2192435_tcm245_2193324_tcm245-2192435.jpg");
		card.setSubtitle("This is Subtitle");
		googleCloudDialogflowV2IntentMessage.setCard(card);
		fulfillmentMessages.add(googleCloudDialogflowV2IntentMessage);
		response.setFulfillmentMessages(fulfillmentMessages);
		return new ResponseEntity<String>(gson.toJson(response),HttpStatus.OK);
	}
	
	/*@RequestMapping(method = RequestMethod.POST, path = "/webhook2", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> webHookFullfillment2(HttpEntity<String> httpEntity) throws JSONException {
		WebhookResponse  webhookResponse  =WebhookResponse.getDefaultInstance();
		Builder builder= webhookResponse.newBuilder().setFulfillmentText("Hello java");
		WebhookRequest input = gson.fromJson(httpEntity.getBody(), WebhookRequest.class);
		System.out.println(gson.toJson(input));
	    output.setFulfillmentText("Hello java");
	    ResponseEntity<String> entity = new ResponseEntity<String>(gson.toJson(output),HttpStatus.OK);
	    return entity;
	}*/
	
	@RequestMapping(method = RequestMethod.POST, path = "/test", consumes = MediaType.APPLICATION_JSON_VALUE)
	public WebhookResponse getTest1(WebhookRequest request) {

		System.out.println("Here is My request :" + request.toString());
		return WebhookResponse.newBuilder().setFulfillmentText("How  Dialogflow !!").build();

	}

	// @PreAuthorize("#oauth2.hasScope('test_read') and
	// hasAuthority('ROLE_OPERATOR','USER')")
	// @PreAuthorize("#oauth2.hasScope('test_read')")
	@RequestMapping("/employees")
	public ArrayList<EmployeeModel> getEmployeeData() {
		ArrayList<EmployeeModel> employees = new ArrayList<>();

		EmployeeModel employee1 = new EmployeeModel();
		employee1.setfName("AAA");
		employee1.setlName("ZZZ");
		employee1.setAge(10);
		employees.add(employee1);

		EmployeeModel employee2 = new EmployeeModel();
		employee2.setfName("BBB");
		employee2.setlName("YYY");
		employee2.setAge(20);

		employees.add(employee2);

		EmployeeModel employee3 = new EmployeeModel();
		employee3.setfName("CCC");
		employee3.setlName("XXX");
		employee3.setAge(30);
		employees.add(employee2);
		return employees;
	}
	@RequestMapping(method = RequestMethod.POST, path = "/fulfillment", consumes = MediaType.APPLICATION_JSON_VALUE)
	public GoogleCloudDialogflowV2WebhookResponse getFulfillment(GoogleCloudDialogflowV2WebhookRequest request) {
		System.out.println("How  Dialogflow !!");
		GoogleCloudDialogflowV2WebhookResponse response =new GoogleCloudDialogflowV2WebhookResponse();
		
		response.setFulfillmentText("How  Dialogflow");
		return response; 
	}
	
	protected static class AIWebhookRequest extends AIResponse {
	    private static final long serialVersionUID = 1L;

	    private OriginalDetectIntentRequest originalDetectIntentRequest;

	    /**
	     * Get original request object
	     * @return <code>null</code> if original request undefined in
	     * request object
	     */
	    public OriginalDetectIntentRequest getOriginalDetectIntentRequest() {
	      return originalDetectIntentRequest;
	    }
	  }
	  /**
	   * Original request model class
	   */
	  protected static class OriginalDetectIntentRequest implements Serializable {
	    private static final long serialVersionUID = 1L;
	    private String source;
	    private Map<String, ?> data;

	    /**
	     * Get source description string
	     * @return <code>null</code> if source isn't defined in a request
	     */
	    public String getSource() {
	      return source;
	    }

	    /**
	     * Get data map
	     * @return <code>null</code> if data isn't defined in a request
	     */
	    public Map<String, ?> getData() {
	      return data;
	    }
	  }

}
