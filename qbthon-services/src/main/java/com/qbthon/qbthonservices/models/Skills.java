package com.qbthon.qbthonservices.models;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "skills")

public class Skills {
	
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
