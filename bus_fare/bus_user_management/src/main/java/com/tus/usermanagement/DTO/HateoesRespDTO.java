package com.tus.usermanagement.DTO;

import java.net.URI;
import java.util.ArrayList;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HateoesRespDTO {

	public Object response;
	public Map<String, ArrayList<URI>> _links;
}
