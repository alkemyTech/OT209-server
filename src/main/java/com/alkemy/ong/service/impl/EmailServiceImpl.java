package com.alkemy.ong.service.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alkemy.ong.service.EmailService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Personalization;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Service
public class EmailServiceImpl implements EmailService {

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
