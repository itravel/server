package com.weiminw.travel.dao.impls;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.weiminw.travel.dao.IPersistence;
import com.weiminw.travel.dao.impls.pos.HotelEntity;
import com.weiminw.travel.interfaces.daos.IUser;

public final class MySqlPersistence<T> implements IPersistence<T> {
	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("travel");
	
	private MySqlPersistence(){
	}
	public static final <T> MySqlPersistence<T> create(){
		return new MySqlPersistence<T>();
	}
	@Override
	public T getPersistenceObject(Class<? extends T> typeCLass,long id) {
		EntityManager manager = factory.createEntityManager();
		T result = manager.find(typeCLass, id);
		manager.close();
		return result;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getPersistenceObjects(String query) {
		EntityManager manager = factory.createEntityManager();
		Query namedQuery= manager.createNamedQuery(query);
		List<T> results = namedQuery.getResultList();
		manager.close();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getPersistenceObjects(String query,Map.Entry<String,?>... parameters) {
		EntityManager manager = factory.createEntityManager();
		Query namedQuery= manager.createNamedQuery(query);
//		namedQuery.setMaxResults(10);
		for(Map.Entry<String,?> entry:parameters){
			namedQuery.setParameter(entry.getKey(), entry.getValue());
		}
		List<T> results = namedQuery.getResultList();
		manager.close();
		return results;
	}
	
	public boolean insertPersistenceObjects(T object){
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(object);
		manager.getTransaction().commit();
		return true;
	}
	
	public boolean updatePersistenceObject(T object){
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		T merged = manager.merge(object);
		manager.getTransaction().commit();
		manager.close();
		return true;
		
	}
	public static void main(String[] args) {
		EntityManager manager = factory.createEntityManager();
		HotelEntity po = manager.find(HotelEntity.class, 10000893L);
		po.setLongitude(8);
		manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.merge(po);
		manager.getTransaction().commit();
	}
	
	
	
}
