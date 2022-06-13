package com.alkemy.ong.utility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class PaginationHelper {
	
	public Map<String, String> generarpaginadorespuesta(UriComponentsBuilder uriComponentsBuilder, String path, String uriNextPage, String uriPrevPage, Page<?> dataPage, int offsetBase10) {
		uriComponentsBuilder.path(path);
		if(dataPage.hasNext()) {
			uriNextPage = uriComponentsBuilder.replaceQueryParam("page", offsetBase10 + 1).build().encode().toUriString();			
		}
		if(dataPage.hasPrevious()) {
			uriPrevPage = uriComponentsBuilder.replaceQueryParam("page", offsetBase10 - 1).build().encode().toUriString();			
		}
		Map<String, String> response = new HashMap<>();
		response.put("prev", uriPrevPage);
		response.put("next", uriNextPage);
		return response;
		
	}
}
