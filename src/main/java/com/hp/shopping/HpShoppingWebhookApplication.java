package com.hp.shopping;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.dozer.classmap.generator.GeneratorUtils;
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
        //
		final String dir = System.getProperty("user.dir");
		//URL location = (HpShoppingWebhookApplication.class).getProtectionDomain().getCodeSource().getLocation();
        //System.out.println("App current dir = " + dir);
        //System.out.println("Current File path :"+location.getPath());
        //System.out.println("Current File path :"+location.getFile());*/
		BufferedWriter bufferedWriter = null;
        try {
            String strContent = "{\n" + 
            		"  \"type\": \"service_account\",\n" + 
            		"  \"project_id\": \"productsapp-13c11\",\n" + 
            		"  \"private_key_id\": \"de7042767d7428fc2ba27652fa556ae373030799\",\n" + 
            		"  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDgSV4c7uRC7gW6\\nSOfj9zUYtSfh1Zgly4JmXlPqy0FbR2Kif36sm85hrHX7vjjBsVxpAFMA5Fp+0cpp\\nsAtUeMlHWiOPB2f4lskztzXhwxXkBjitiQsrdBIYsNvl2cNxwPAzXIeo+O4Rqwpl\\npGTGDkCIY1duGSTmR5L6eYvMgU3qeexrDzacx9lZAuE1ZXyVEpluUtexevd0bI4t\\ne1OvHQqiHMrjxICspufRuIsMXpNHAbyI3hvM0O0Hnuvm91kqmeYpuUDoSYaNHrWf\\nNcS7UhNfJMTXw/03h2Wy+gAtf00SwLLVLLJVA/OGMnsRNtHVx4f/TmD7srYf0ZKY\\nsOV3QO+LAgMBAAECggEAB4/BqgUN0gnXTJf/pWpksmANJ6p9VizdyRi+CIjwu6SU\\n+TiGK0iXdSiM079uh2002d9mbP3F5UeqZNTIFQF7e7nYwA42XaWrw+AV43Yhy03A\\nZG1d4ImpwIZZ645jbfbcwkaI1lQKJrNXBuU7doftegurW21ilpbsKCCn622ilFiW\\nygB9nlofOSZ+kgiZTjsz4oZsHGAuv4yUkUbhfKJfF7K+R+DdTwDsrL/d6Zztt86Z\\n6WLIe0IEEOpRVy6B4qQZH7vQMk55BzlY+idH+rTRIDfbIfpRU11qQpqeP86uOVHE\\nCnNUZOPKABjYkjqvaNuqdhBJtApHjbVG9T95wuwNTQKBgQD5LGNUCAWgeZLX4tBx\\ngV4nz9t+lR732akgeUA0t9B959wKpCUKpA4aqWWgkxB/JYIWK33qad5+kEeNJIsn\\nepfoDmOlWMm238BA00NIzVFcS6OlAA1OeacGZ6fI3oxs+CpgPgV/bKkT972YzNrz\\ngQP9iBfV4MUzHnG/0aGERESO9wKBgQDmbm7A0z0zzRskFXHW5nWTE74CS2x+ny+h\\noJkdDXIBVf4EIV2hCL5g3PMxrJfJi6Z/JYHPA4irL8BQC334qmPsoYLrFjqLS4lr\\nEGR6bMfnVEYqz5viDEm8RbdED96Jq3m4d5hqUqNqmwupFGtvnmLftui10hwT86oD\\nuhdQs1N7DQKBgFY80JvSsu/ZY8MoEvBVz9qDi/oBblhlkq1Uia41OAl7Z6KfMNq/\\n8Mm1eyDaBUmcF6vInam1vnWbRchJr4eYXF7KLB6b3iRWyFknRRA7Tz9cRmTi4bgZ\\nTn9e/Kj+rSdBtJjenlR9rP7mLU5nCBhivJjQsz8ordeFX+T9jqMSZY/9AoGAJDjr\\n1AfgsQnm4E82q+9iQBJ0ZevxTB494IxcRULdzvPzkDamfy/erV9OI1rcKWR4cbg1\\npZMKos5Izsxfqv0t4/6nk8jUvxzKpzfWc6Ax9F09AnHMMUZ5OM1Et8A+3SlFYwf5\\nv4zp/1IMDTIWiV8+b6hTAHHc1A4Tha51B8yIrOkCgYEA2qByOmDp8cn/tresHK/y\\nFCVAxsDSWIfCSQ39Yi31Wy9XGVZcThivSuV9ok4D6A1B+7tzAt3eeWZ8linZU+ne\\no9pfL/XAXD+/2/QmWYnLL4zwysdPu5XRaA8jyO7zG19SyoPnE960UtMEFbir0kPX\\nlpux2Wei+Htg3I51WCRt3Qw=\\n-----END PRIVATE KEY-----\\n\",\n" + 
            		"  \"client_email\": \"dialogflow-service-account@productsapp-13c11.iam.gserviceaccount.com\",\n" + 
            		"  \"client_id\": \"106922849311268061435\",\n" + 
            		"  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" + 
            		"  \"token_uri\": \"https://accounts.google.com/o/oauth2/token\",\n" + 
            		"  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" + 
            		"  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/dialogflow-service-account%40productsapp-13c11.iam.gserviceaccount.com\"\n" + 
            		"}";
            File myFile = new File(dir+"/default-account-credentials.json");
            // check if file exist, otherwise create the file before writing
            if (!myFile.exists()) {
                myFile.createNewFile();
           
            Writer writer = new FileWriter(myFile);
            bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(strContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(bufferedWriter != null) bufferedWriter.close();
            } catch(Exception ex){
                 
            }
        }
        
		
		final File folder = new File(dir);
		HpShoppingWebhookApplication obj = new HpShoppingWebhookApplication();
		obj.listFilesForFolder(folder);
		System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", dir+"/default-account-credentials.json");
		SpringApplication.run(HpShoppingWebhookApplication.class, args);
	}
	
	@Bean
    public Messenger messenger(@Value("${messenger4j.pageAccessToken}") String pageAccessToken,
                               @Value("${messenger4j.appSecret}") final String appSecret,
                               @Value("${messenger4j.verifyToken}") final String verifyToken) {
        return Messenger.create(pageAccessToken, appSecret, verifyToken);
    }
	
	public void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            System.out.println("file path : 1"+fileEntry.getName()+" 2  "+fileEntry.getAbsolutePath()+" 3 "+fileEntry.getPath() +" 4 "+fileEntry.getPath());
	        }
	    }
	}
}
