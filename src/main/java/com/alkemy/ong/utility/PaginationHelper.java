package com.alkemy.ong.utility;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaginationHelper {
	private UriComponentsBuilder uriComponentsBuilder;
	private int totalPages;
	private int offset;
	
	public String getUriNext() {
		if(offset < totalPages) {
			return uriComponentsBuilder.replaceQueryParam("page", (this.offset +1)).build().encode().toUriString();
		}
		return "";
	}
	public String getUriPrev() {
		if(offset > 1) {
			return uriComponentsBuilder.replaceQueryParam("page", (this.offset -1)).build().encode().toUriString();			
		}
		return "";
	}

}
