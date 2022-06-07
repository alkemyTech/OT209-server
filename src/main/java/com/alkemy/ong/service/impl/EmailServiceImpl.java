package com.alkemy.ong.service.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.sendgrid.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.alkemy.ong.service.EmailService;
import com.fasterxml.jackson.annotation.JsonProperty;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private Environment env;

	@Override
	public String sendTextEmail(String toNewUser) throws IOException {

		String apikey = env.getProperty("API_KEY");
		String template_id = env.getProperty("TEMPLATE_ID");
		String email_sender = env.getProperty("EMAIL_SENDER");
		log.info("entrando a sendTextEmail");
		Email from = new Email(email_sender);
		String subject = "SomosMás";
		Email to = new Email(toNewUser);
		Content content = new Content("text/plain", "Bienvenido a SomosMás");
		Mail mail = new Mail(from, subject, to, content);

		DynamicTemplatePersonalization personalization = new DynamicTemplatePersonalization();
		personalization.addTo(to);
		personalization.addDynamicTemplateData("Maxi", "hamdi");
		mail.addPersonalization(personalization);
		mail.setTemplateId(template_id);
		log.info("antes");
		SendGrid sg = new SendGrid(apikey);
		log.info("despues");
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			log.info(response.getBody());
			return response.getBody();
		} catch (IOException ex) {
			throw ex;
		}

	}

	public void send() throws IOException {
		/*
		 * The sender email should be the same as we used to Create a Single Sender
		 * Verification
		 */
		Email from = new Email("mdlprofesional@gmail.com");
		Email to = new Email("mdlprofesional@gmail.com");
		Mail mail = new Mail();
		DynamicTemplatePersonalization personalization = new DynamicTemplatePersonalization();
		personalization.addTo(to);
		mail.setFrom(from);
		mail.setSubject("esto sale con template");
		// this is the dynamic value of first_name variable on our template
		// feel free to create a variable firstName passed with the send method
		personalization.addDynamicTemplateData("Maxi", "hamdi");
		mail.addPersonalization(personalization);
		mail.setTemplateId("d-f1170ca2ca5342c184383730f1f006cf");
		// feel free to save this varible on the env
		SendGrid sg = new SendGrid("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");
		Request request = new Request();

		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			response.getBody();
		} catch (IOException ex) {
			throw ex;
		}
	}

	// This class handel the dynamic data for the template
	// Feel free to customise this class our to putted on other file
	private static class DynamicTemplatePersonalization extends Personalization {

		@JsonProperty(value = "dynamic_template_data")
		private Map<String, String> dynamic_template_data;

		@JsonProperty("dynamic_template_data")
		public Map<String, String> getDynamicTemplateData() {
			if (dynamic_template_data == null) {
				return Collections.<String, String>emptyMap();
			}
			return dynamic_template_data;
		}

		public void addDynamicTemplateData(String key, String value) {
			if (dynamic_template_data == null) {
				dynamic_template_data = new HashMap<String, String>();
				dynamic_template_data.put(key, value);
			} else {
				dynamic_template_data.put(key, value);
			}
		}

	}
}
