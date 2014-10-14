package com.itravel.server.dal.managers;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;

import com.itravel.server.dal.entities.ActivityCommentEntity;

public class ActivityCommentManager extends AbstractManager {

	public List<ActivityCommentEntity> getCommentsByActivityId(long activityId) {
		EntityManager manager = this.emf.createEntityManager();
		List<ActivityCommentEntity> comments = manager
				.createQuery(
						"select AC from ActivityCommentEntity AC where AC.activityId = :activityId",ActivityCommentEntity.class)
				.setParameter("activityId", activityId).getResultList();
		manager.close();
		return comments;
	}

	public ActivityCommentEntity getCommentById(long id) {
		EntityManager manager = this.emf.createEntityManager();
		ActivityCommentEntity comment = manager.find(
				ActivityCommentEntity.class, id);
		manager.close();
		return comment;
	}

	public boolean saveComment(ActivityCommentEntity comment) {
		boolean success = false;
		EntityManager manager = this.emf.createEntityManager();
		manager.getTransaction().begin();
		try {
			if (comment.getId() > 0L) {
				ActivityCommentEntity _oldComment = manager.find(
						ActivityCommentEntity.class, comment.getId());
				if (_oldComment != null) {
					manager.merge(comment);
				} else {
					manager.persist(comment);
				}
			} else {
				manager.persist(comment);
			}
		
			manager.getTransaction().commit();
			success = true;

		} catch (EntityExistsException e) {
			manager.getTransaction().rollback();
		} catch (IllegalArgumentException e) {
			manager.getTransaction().rollback();

		} catch (TransactionRequiredException e) {
			manager.getTransaction().rollback();

		} finally {
			manager.close();
		}
		return success;
	}
	
	public boolean removeComment(long id){
		boolean success = false;
		EntityManager manager = this.emf.createEntityManager();
		manager.getTransaction().begin();
		
		try{
			ActivityCommentEntity comment = manager.find(ActivityCommentEntity.class, id);
			manager.remove(comment);
			manager.getTransaction().commit();
			success = true;
		}
		catch (IllegalArgumentException e) {
			manager.getTransaction().rollback();

		} catch (TransactionRequiredException e) {
			manager.getTransaction().rollback();

		} finally {
			manager.close();
		}
		return success;
	}
}
