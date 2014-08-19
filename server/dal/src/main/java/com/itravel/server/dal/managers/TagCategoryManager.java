package com.itravel.server.dal.managers;

import java.util.List;

import javax.persistence.EntityManager;

import com.google.common.base.Preconditions;
import com.itravel.server.dal.entities.TagCategoryEntity;
import com.itravel.server.dal.entities.TagEntity;

public class TagCategoryManager extends AbstractManager {
	public  void save(TagCategoryEntity entity){
		Preconditions.checkArgument(entity!=null);
		EntityManager manager = this.emf.createEntityManager();
		manager.getTransaction().begin();
		if(entity.getId() >0){
			TagCategoryEntity old = entity = manager.find(TagCategoryEntity.class, entity.getId());
			TagCategoryEntity newEntity = manager.merge(entity);
			manager.persist(newEntity);
			manager.getTransaction().commit();
		}
		else {
			manager.persist(entity);
			manager.getTransaction().commit();
		}
		manager.close();
	}
	public List<TagCategoryEntity> getAll(){
		EntityManager manager = this.emf.createEntityManager();
		List<TagCategoryEntity> tagCategories = manager.createQuery("select T from TagCategoryEntity T ").getResultList();
		manager.close();
		return tagCategories;
	}
	public TagCategoryEntity get(long id){
		EntityManager manager = this.emf.createEntityManager();
		TagCategoryEntity tagCategory = manager.find(TagCategoryEntity.class, id);
		manager.close();
		return tagCategory;
	}
	public boolean delete(long id) {
		EntityManager manager = this.emf.createEntityManager();
		manager.getTransaction().begin();
		TagCategoryEntity entity = manager.find(TagCategoryEntity.class, id);
		if(entity == null){
			return true;
		}
		else {
			manager.remove(entity);
		}
		manager.getTransaction().commit();
		manager.close();
		return true;
	}
}
