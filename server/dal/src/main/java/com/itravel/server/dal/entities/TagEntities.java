package com.itravel.server.dal.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: TagEntities
 *
 */
@Entity
@Table(name="tags")

public class TagEntities implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	private long id ;
	@Column(name="tag")
	private String tag;

	@Column(name="category")
	private int category;
	
	@Column(name="category_value")
	private String categoryValue;
	
	public TagEntities() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getCategoryValue() {
		return categoryValue;
	}

	public void setCategoryValue(String categoryValue) {
		this.categoryValue = categoryValue;
	}
	
	
   
}
