package com.alkemy.ong.service;

import java.io.IOException;

public interface EmailService {

	String sendTextEmail(String toNewUser) throws IOException;

	public void send() throws IOException;
}
