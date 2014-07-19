package com.itravel.server.dal.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Entity implementation class for Entity: TagCategoryEntity
 *
 */
@Entity
@Table(name="tag_category")
public class TagCategoryEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(name="category")
	private String category;
	
	public TagCategoryEntity() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category.trim();
	}
   
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}
