package com.alkemy.ong.utility;

import org.springframework.data.domain.Page;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaginationHelper {
	private UriComponentsBuilder uriComponentsBuilder;
	private Page<?> dataPage;
	private int offsetInitial;
	
	
	public String getUriNext() {
		if(this.dataPage.hasNext()) {
			return uriComponentsBuilder.replaceQueryParam("page", (this.offsetInitial +1)).build().encode().toUriString();
		}
		return "";
	}
	public String getUriPrev() {
		if(this.dataPage.hasPrevious()) {
			return uriComponentsBuilder.replaceQueryParam("page", (this.offsetInitial -1)).build().encode().toUriString();
		}
		return "";
	}

}
