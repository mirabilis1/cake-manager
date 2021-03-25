package com.waracle.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "title", "desc", "image" })
public class CakeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4862052362376895909L;

	private Long cakeId;
	private String title;
	private String desc;
	private String image;

}
