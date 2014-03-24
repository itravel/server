package com.itravel.server.services.aos;

import com.itravel.server.interfaces.dal.EntityType;

public class InformationStream {
	private EntityType type;
	private Object content;
	
	public InformationStream() {
		// TODO Auto-generated constructor stub
	}
	
	public InformationStream(EntityType type, Object content) {
		super();
		this.type = type;
		this.content = content;
	}

	public EntityType getType() {
		return type;
	}
	public void setType(EntityType type) {
		this.type = type;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
}
