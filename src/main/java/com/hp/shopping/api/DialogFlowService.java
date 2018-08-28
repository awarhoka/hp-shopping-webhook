package com.hp.shopping.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookRequest;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookResponse;

/**
 * @author warhokar
 *
 */
public interface DialogFlowService {

	@RequestMapping(method = RequestMethod.POST, path = "/webhookV2")
	@ResponseBody
	public GoogleCloudDialogflowV2WebhookResponse dialogflowFulfillmentV2(HttpServletRequest request);
	
	@RequestMapping(method = RequestMethod.POST, path = "/webhookV2v1")
	@ResponseBody
	public GoogleCloudDialogflowV2WebhookResponse dialogflowFulfillmentV2v1(@RequestBody GoogleCloudDialogflowV2WebhookRequest jsonObject);
	
	/*@RequestMapping(method = RequestMethod.POST, path = "/webhookV2beta1")
	@ResponseBody
	public GoogleCloudDialogflowV2beta1WebhookResponse dialogflowFulfillmentV2beta1(@RequestBody JsonObject jsonObject);

	@RequestMapping(method = RequestMethod.POST, path = "/webhookV2beta1v1")
	@ResponseBody
	public GoogleCloudDialogflowV2beta1WebhookResponse dialogflowFulfillmentV2beta1v1(@RequestBody GoogleCloudDialogflowV2beta1WebhookRequest jsonObject);
	
	@RequestMapping(method = RequestMethod.POST, path = "/webhookV2v2")
	@ResponseBody
	public GoogleCloudDialogflowV2WebhookResponse dialogflowFulfillmentV2v2(@RequestBody GenericJson jsonObject);*/
}
