package com.itravel.server.dal.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Entity implementation class for Entity: TagEntities
 *
 */
@Entity
@Table(name="tags")

public class TagEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id ;
	@Column(name="tag")
	private String tag;

	@ManyToOne
	@JoinColumn(name="category_id",referencedColumnName="id",nullable = false)
	private TagCategoryEntity category;
	
	public TagEntity() {
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
		this.tag = tag.trim();
	}

	public TagCategoryEntity getCategory() {
		return category;
	}

	public void setCategory(TagCategoryEntity category) {
		this.category = category;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}

	
   
}
