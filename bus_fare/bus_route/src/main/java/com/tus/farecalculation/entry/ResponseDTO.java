package com.tus.farecalculation.entry;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ResponseDTO {

	private String messgae;

	public ResponseDTO() {
	}

	public ResponseDTO(String messgae) {
		this.messgae = messgae;
	}
}
