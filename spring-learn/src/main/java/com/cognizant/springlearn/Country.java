package com.cognizant.springlearn;

import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;


public class Country {
	
	@NotNull
	@Size(min=2, max=2, message="Country code should be 2 characters")
	private String code;
	private String name;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);
	
	public Country() {
		LOGGER.debug("Inside Country Constructor");
	}

	public String getCode() {
		LOGGER.debug("Getting code : " +code);
		return code;
	}

	public void setCode(String code) {
		LOGGER.debug("Setting code : " +code);
		this.code = code;
	}

	public String getName() {
		LOGGER.debug("Getting name : " +name);
		return name;
	}

	public void setName(String name) {
		LOGGER.debug("Getting name : " +name);
		this.name = name;
	}

	@Override
	public String toString() {
		return "Country [code=" + code + ", name=" + name + "]";
	}
}
