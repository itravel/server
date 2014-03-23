package com.itravel.server.dal.managers;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractManager {
	protected static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("dal");
	protected static final ObjectMapper mapper = new ObjectMapper();
}
