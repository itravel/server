package com.itravel.admin.dal.managers;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.common.base.Joiner;
import com.itravel.admin.dal.entities.LvyeActivityEntity;

public class LvyeDataManager {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("dal");
	
	public void save(LvyeActivityEntity lvye){
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		if(lvye.getId()>0){
			manager.find(LvyeActivityEntity.class, lvye.getId());
			manager.merge(lvye);
			
		}
		else {
			manager.persist(lvye);
		}
		manager.getTransaction().commit();
		manager.close();
	}

	/**
	 * 获取全部数据
	 * @return
	 */
	public List<LvyeActivityEntity> getAll() {
		// TODO Auto-generated method stub
		EntityManager manager = emf.createEntityManager();
		List<LvyeActivityEntity> activities = manager.createQuery("select L from LvyeActivityEntity L",LvyeActivityEntity.class).getResultList();
		manager.close();
		return activities;
		
	}
	
	/**
	 * 获取部分数据
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<LvyeActivityEntity> getPart(int offset,int limit){
		EntityManager manager = emf.createEntityManager();
		List<LvyeActivityEntity> activities = manager.createQuery("select L from LvyeActivityEntity L order by L.id",LvyeActivityEntity.class).setFirstResult(offset).setMaxResults(limit).getResultList();
		manager.close();
		return activities;
	}
	
	/**
	 * 获取部分数据
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<LvyeActivityEntity> getUnEditPart(int offset,int limit){
		EntityManager manager = emf.createEntityManager();
		List<LvyeActivityEntity> activities = manager.createQuery("select L from LvyeActivityEntity L where L.editStatus = 0 order by L.id",LvyeActivityEntity.class).setFirstResult(offset).setMaxResults(limit).getResultList();
		manager.close();
		return activities;
	}
	/**
	 * 获取有效时间的未编辑数据
	 * @param offset
	 * @param limit
	 * @return
	 */
	
	public List<LvyeActivityEntity> getValidUnEditPart(int offset,int limit,Set<Long> editings){
		EntityManager manager = emf.createEntityManager();
		List<LvyeActivityEntity> activities = manager.createNativeQuery("select * from lvye_activity where STR_TO_DATE(end_time, '%Y年%c月%e日') > ?end and edit_status = 0 and id not in (?editings) order by id", LvyeActivityEntity.class).setParameter("editings", Joiner.on(",").join(editings)).setParameter("end", new Date()).setFirstResult(offset).setMaxResults(limit).getResultList();
		manager.close();
		return activities;
	}

	public LvyeActivityEntity get(long id) {
		// TODO Auto-generated method stub
		EntityManager manager = emf.createEntityManager();
		LvyeActivityEntity entity = manager.find(LvyeActivityEntity.class, id);
		manager.close();
		return entity;
	}
}
