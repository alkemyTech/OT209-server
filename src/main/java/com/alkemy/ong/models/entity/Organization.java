package com.alkemy.ong.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@AllArgsConstructor
@Table
@NoArgsConstructor
@Data
@Entity
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@NonNull
	@NotEmpty(message = "the Name  can't be null")
	@NotBlank(message = "the Name can't  be blank")
	@Column(nullable = false)
	private String name;

	private String image;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private Integer phone;

	@NotEmpty(message = "Email cannot be null")
	@Column(nullable = false)
	private String email;

	@NotEmpty(message = "Welcome text cannot be null")
	@Column(nullable = false)
	private String welcomeText;

	@Column(nullable = false)
	@NotEmpty(message = "About text cannot be null")
	@NonNull
	private String aboutUsText;

	@CreationTimestamp
	private Timestamp timestamp;

	private Boolean softDelete;

	private String urlInstagram;
	private String urlFacebook;
	private String urlLinkedin;

	public Organization(String _name, String _image, String _address, Integer _phone, String _email,
			String _welcomeText, String _aboutUs, String _urlInstagram, String _urlFacebook, String _urlLinkedin) {
		this.setName(_name);
		this.setImage(_image);
		this.setAddress(_address);
		this.setPhone(_phone);
		this.setEmail(_email);
		this.setWelcomeText(_welcomeText);
		this.setAboutUsText(_aboutUs);
		this.setUrlInstagram(_urlInstagram);
		this.setUrlFacebook(_urlFacebook);
		this.setUrlLinkedin(_urlLinkedin);
	}
}
