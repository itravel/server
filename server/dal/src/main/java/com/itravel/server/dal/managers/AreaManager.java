package com.itravel.server.dal.managers;

import java.util.List;

import javax.persistence.EntityManager;

import com.itravel.server.dal.entities.AreaEntity;

public class AreaManager extends AbstractManager {
	
	public List<AreaEntity> getAreasByType(int type){
		EntityManager manager = this.emf.createEntityManager();
		List<AreaEntity> areas = manager.createQuery("select A from AreaEntity A where A.type = :type", AreaEntity.class).setParameter("type", type).getResultList();
		manager.close();
		return areas;
	}
	
	public List<AreaEntity> getAreasByParent(long parentId,int type){
		EntityManager manager = this.emf.createEntityManager();
		List<AreaEntity> areas = manager.createQuery("select A from AreaEntity A where A.type = :type and A.parentId = :parentId", AreaEntity.class).setParameter("type", type).setParameter("parentId", parentId).getResultList();
		manager.close();
		return areas;
	}
	
	public AreaEntity getAreaById(long id){
		EntityManager manager = this.emf.createEntityManager();
		AreaEntity area = manager.find(AreaEntity.class, id);
		manager.close();
		return area;
	}

	public AreaEntity getAreaByNameAndType(String name, int type) {
		// TODO Auto-generated method stub
		EntityManager manager = this.emf.createEntityManager();
		List<AreaEntity> areas = manager.createQuery("select A from AreaEntity A where A.type = :type and A.abbName = :abbName", AreaEntity.class).setParameter("type", type).setParameter("abbName", name).getResultList();
		manager.close();
		return areas.size()>0? areas.get(0):null;
	}
	
}
