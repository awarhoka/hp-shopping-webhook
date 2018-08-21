/**
 * 
 */
package com.hp.shopping.restservice;

import java.util.ArrayList;

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

import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookRequest;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookResponse;
import com.google.cloud.dialogflow.v2.WebhookRequest;
import com.google.cloud.dialogflow.v2.WebhookResponse;
import com.google.gson.Gson;
import com.hp.shopping.model.EmployeeModel;


/**
 * @author warhokar
 *
 */
@RestController
public class DialogFlowService {

	@RequestMapping(method = RequestMethod.POST, path = "/test", consumes = MediaType.APPLICATION_JSON_VALUE)
	public WebhookResponse getTest1(WebhookRequest request) {

		System.out.println("Here is My request :" + request.toString());
		return WebhookResponse.newBuilder().setFulfillmentText("How  Dialogflow !!").build();

	}

	/*
	 * @Autowired private ResourceServerProperties ssoID;
	 * 
	 * @Bean public ResourceServerTokenServices customUserInfoTokenServices() {
	 * System.out.println("User info URI : "+ssoID.getUserInfoUri()+"Client ID : "
	 * +ssoID.getClientId()); return new
	 * CustomUserInfoTokenServices(ssoID.getUserInfoUri(),ssoID.getClientId()); }
	 */
	/*
	 * @PostMapping("test1t") public String getTest1(HttpEntity<String> httpEntity)
	 * {
	 * 
	 * String reqObject = httpEntity.getBody();
	 * System.out.println("request json object = "+reqObject);
	 * 
	 * //Get the action
	 * 
	 * JSONObject obj = new JSONObject(reqObject); String action =
	 * obj.getJSONObject("result").getString("action");
	 * 
	 * //Get the parameters JSONObject params =
	 * obj.getJSONObject("result").getJSONObject("parameters"); String response =
	 * "Hello from Java."; return
	 * "{'speech': '"+response+"', 'fulfillmentText':'"+response+"'}"; }
	 */

	@RequestMapping(value = "/hello_old ", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> helloWorld2() {

		String message1 = "{\r\n" + "  \"fulfillmentText\": \"This is a text response\",\r\n"
				+ "  \"fulfillmentMessages\": [\r\n" + "    {\r\n" + "      \"card\": {\r\n"
				+ "        \"title\": \"card title\",\r\n" + "        \"subtitle\": \"card text\",\r\n"
				+ "        \"imageUri\": \"https://assistant.google.com/static/images/molecule/Molecule-Formation-stop.png\",\r\n"
				+ "        \"buttons\": [\r\n" + "          {\r\n" + "            \"text\": \"button text\",\r\n"
				+ "            \"postback\": \"https://assistant.google.com/\"\r\n" + "          }\r\n"
				+ "        ]\r\n" + "      }\r\n" + "    }\r\n" + "  ],\r\n" + "  \"source\": \"example.com\",\r\n"
				+ "  \"payload\": {\r\n" + "    \"google\": {\r\n" + "      \"expectUserResponse\": true,\r\n"
				+ "      \"richResponse\": {\r\n" + "        \"items\": [\r\n" + "          {\r\n"
				+ "            \"simpleResponse\": {\r\n"
				+ "              \"textToSpeech\": \"this is a simple response\"\r\n" + "            }\r\n"
				+ "          }\r\n" + "        ]\r\n" + "      }\r\n" + "    },\r\n" + "    \"facebook\": {\r\n"
				+ "      \"text\": \"Hello, Facebook!\"\r\n" + "    },\r\n" + "    \"slack\": {\r\n"
				+ "      \"text\": \"This is a text response for Slack.\"\r\n" + "    }\r\n" + "  },\r\n"
				+ "  \"outputContexts\": [\r\n" + "    {\r\n"
				+ "      \"name\": \"projects/${PROJECT_ID}/agent/sessions/${SESSION_ID}/contexts/context name\",\r\n"
				+ "      \"lifespanCount\": 5,\r\n" + "      \"parameters\": {\r\n"
				+ "        \"param\": \"param value\"\r\n" + "      }\r\n" + "    }\r\n" + "  ],\r\n"
				+ "  \"followupEventInput\": {\r\n" + "    \"name\": \"event name\",\r\n"
				+ "    \"languageCode\": \"en-US\",\r\n" + "    \"parameters\": {\r\n"
				+ "      \"param\": \"param value\"\r\n" + "    }\r\n" + "  }\r\n" + "}";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<String> entity = new ResponseEntity<String>(message1, headers, HttpStatus.OK);
		return entity;

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
	public GoogleCloudDialogflowV2WebhookResponse getFulfillment(
			@RequestBody GoogleCloudDialogflowV2WebhookRequest request) {
		System.out.println("How  Dialogflow !!");
		GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();

		response.setFulfillmentText("How  Dialogflow");
		return response;
	}

	/*
	 * @RequestMapping(method = RequestMethod.POST, path = "/hello1", consumes =
	 * MediaType.APPLICATION_JSON_VALUE) public WebhookResponse
	 * getResponse(WebhookRequest request) { WebhookResponse response =
	 * WebhookResponse.getDefaultInstance(); Builder builder =
	 * response.newBuilder(); return response; }
	 */
	private static final Gson gson = new Gson();

	@PostMapping("/test2")
	public String getTest1(HttpEntity<String> httpEntity) throws JSONException {

		String reqObject = httpEntity.getBody();
		System.out.println("request json object = " + reqObject);

		// Get the action
		JSONObject obj = new JSONObject(reqObject);
		String action = obj.getJSONObject("queryResult").getString("action");
		System.out.println("request json object = " + action);
		// Get the parameters
		// JSONObject params = obj.getJSONObject("result").getJSONObject("parameters");
		String response = "Text defined in Dialogflow's console for the intent that was matched";

		return "{'fulfillmentText':'" + response + "'}";
	}

	@RequestMapping(value = "/test4", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> getTest3(HttpEntity<String> httpEntity) throws JSONException {
		String reqObject = httpEntity.getBody();
		System.out.println("request json object = " + reqObject);
		JSONObject obj = new JSONObject(reqObject);
		String action = obj.getJSONObject("queryResult").getString("action");
		return ResponseEntity.ok(gson.toJson("This is a String"));
	}

	@PostMapping("/test5")
	public ResponseEntity<String> addWebHook(HttpEntity<String> httpEntity) throws JSONException {
		JSONObject obj = new JSONObject();
		String response = "Text defined in Dialogflow's console for the intent that was matched";
		obj.put("fulfillmentText", response);
		
		return new ResponseEntity<String>(obj.toString(), HttpStatus.OK);
	}
}
