package com.hp.shopping;

import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.github.messenger4j.Messenger;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { JacksonAutoConfiguration.class })
public class HpShoppingWebhookApplication {

	public static void main(String[] args) {
        //System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", "./default-account-credentials.json");
		final String dir = System.getProperty("user.dir");
		URL location = (HpShoppingWebhookApplication.class).getProtectionDomain().getCodeSource().getLocation();
        System.out.println("App current dir = " + dir);
        System.out.println("Current File path :"+location.getPath());
        System.out.println("Current File path :"+location.getFile());
        
		SpringApplication.run(HpShoppingWebhookApplication.class, args);
	}
	
	@Bean
    public Messenger messenger(@Value("${messenger4j.pageAccessToken}") String pageAccessToken,
                               @Value("${messenger4j.appSecret}") final String appSecret,
                               @Value("${messenger4j.verifyToken}") final String verifyToken) {
        return Messenger.create(pageAccessToken, appSecret, verifyToken);
    }
}
