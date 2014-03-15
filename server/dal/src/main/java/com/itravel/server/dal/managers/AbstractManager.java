package com.itravel.server.dal.managers;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractManager {
	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("dal");
}
