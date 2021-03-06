package com.itravel.server.dal.managers;

import java.util.List;

import javax.persistence.EntityManager;

import com.google.common.base.Preconditions;
import com.itravel.server.dal.entities.TagEntity;

public class TagManager extends AbstractManager {

	public void save(TagEntity entity) {
		// TODO Auto-generated method stub
		Preconditions.checkArgument(entity!=null);
		EntityManager manager = this.emf.createEntityManager();
		manager.getTransaction().begin();
		if(entity.getId() >0){
			TagEntity old = entity = manager.find(TagEntity.class, entity.getId());
			TagEntity newEntity = manager.merge(entity);
			manager.persist(newEntity);
			manager.getTransaction().commit();
		}
		else {
			manager.persist(entity);
			manager.getTransaction().commit();
			System.out.println(entity);
		}
		manager.close();
	
	}
	
	public List<TagEntity> getAll(){
		EntityManager manager = this.emf.createEntityManager();
		List<TagEntity> tags = manager.createQuery("select T from TagEntity T ").getResultList();
		manager.close();
		return tags;
	}

	public boolean delete(long id) {
		// TODO Auto-generated method stub
		EntityManager manager = this.emf.createEntityManager();
		manager.getTransaction().begin();
		TagEntity entity = manager.find(TagEntity.class, id);
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
	
	public TagEntity get(long id){
		EntityManager manager = this.emf.createEntityManager();
		TagEntity entity = manager.find(TagEntity.class,id);
		manager.close();
		return entity;
	}
	
}
