package com.itravel.server.dal.managers;

import java.util.concurrent.Executors;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public abstract class AbstractManager {
	protected static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("dal");
	protected static final ObjectMapper mapper = new ObjectMapper();
	protected final Logger logger = LogManager.getLogger(this.getClass());
	
}
