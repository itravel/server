package com.itravel.server.dal.managers;

import java.util.List;

import javax.persistence.EntityManager;

import com.itravel.server.dal.entities.AreaEntity;

public class AreaManager extends AbstractManager {
	
	public List<AreaEntity> getAreasByType(int type){
		EntityManager manager = this.emf.createEntityManager();
		List<AreaEntity> areas = manager.createQuery("select A from AreaEntity A where A.type = :type", AreaEntity.class).setParameter("type", type).getResultList();
		return areas;
	}
	
	public List<AreaEntity> getAreasByParent(long parentId,int type){
		EntityManager manager = this.emf.createEntityManager();
		List<AreaEntity> areas = manager.createQuery("select A from AreaEntity A where A.type = :type and A.parentId = :parentId", AreaEntity.class).setParameter("type", type).setParameter("parentId", parentId).getResultList();
		return areas;
	}
	
	public AreaEntity getAreaById(long id){
		EntityManager manager = this.emf.createEntityManager();
		AreaEntity area = manager.find(AreaEntity.class, id);
		return area;
	}
	
}
