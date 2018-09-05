package com.hp.shopping.business.handler;

import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookRequest;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookResponse;

/**
 * @author warhokar
 *
 */
public interface DialogFlowRequestHandler {
	
	public GoogleCloudDialogflowV2WebhookResponse handleDialogFlowV2Request(GoogleCloudDialogflowV2WebhookRequest dialogflowV2WebhookRequest);

}
