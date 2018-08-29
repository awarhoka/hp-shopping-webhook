/**
 * 
 */
package com.hp.shopping.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2IntentMessage;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2IntentMessageCarouselSelect;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2IntentMessageCarouselSelectItem;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2IntentMessageImage;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2IntentMessageSelectItemInfo;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookRequest;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookResponse;

/**
 * @author warhokar
 *
 */
@Service(value = "DialogFlowRequestHandler")
public class DialogFlowRequestHandlerImpl implements DialogFlowRequestHandler {

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
		System.out.println(dialogflowV2WebhookRequest.toString());
		GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
		response.setFulfillmentText("Hello Dialogflow");
		List<GoogleCloudDialogflowV2IntentMessage> fulfillmentMessages = new ArrayList<>();
		GoogleCloudDialogflowV2IntentMessage googleCloudDialogflowV2IntentMessage = new GoogleCloudDialogflowV2IntentMessage();
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
		GoogleCloudDialogflowV2IntentMessageCarouselSelect carouselSelect = new GoogleCloudDialogflowV2IntentMessageCarouselSelect();
		
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
		response.setFulfillmentMessages(fulfillmentMessages);
		return response;
	}

}
