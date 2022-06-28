package com.alkemy.ong.models.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class DateOrganizationRequest {

	@NonNull
	private String name;
	@NonNull
	private String image;
	@NonNull
	private String address;
	@NonNull
	private String phone;
	@NonNull
	private String email;
	@NonNull
	private String welcomeText;
	@NonNull
	private String aboutUsText;
	@NonNull
	private String urlInstagram;
	private String urlFacebook;
	private String urlLinkedin;
}
