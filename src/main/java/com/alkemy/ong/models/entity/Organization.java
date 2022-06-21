package com.alkemy.ong.models.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Builder;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@ApiModel("Model Organization")
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@NonNull
	@NotEmpty(message = "the Name  can't be null")
	@NotBlank(message = "the Name can't  be blank")
	@Column(nullable = false)
        @ApiModelProperty("name of the organization")
	private String name;
        @ApiModelProperty("Image of the organization")
	private String image;
        @ApiModelProperty("Address of the organization")
	@Column(nullable = false)
	private String address;
        @ApiModelProperty("Phone of the organization")
	@Column(nullable = false)
	private Integer phone;
        @ApiModelProperty("Email of the organization")
	@NotEmpty(message = "Email cannot be null")
	@Column(nullable = false)
	private String email;
        @ApiModelProperty("Welcome message organization")
	@NotEmpty(message = "Welcome text cannot be null")	
	private String welcomeText;	
	@NotEmpty(message = "About text cannot be null")
	@NonNull
	private String aboutUsText;

	@CreationTimestamp
	private Timestamp timestamp;        
	private Boolean softDelete;
        @ApiModelProperty("url the instagran organization")
	private String urlInstagram;
        @ApiModelProperty("url the Facebook organization")
	private String urlFacebook;
        @ApiModelProperty("url the Linkedin organization")
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
