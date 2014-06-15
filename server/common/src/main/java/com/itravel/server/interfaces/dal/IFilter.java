package com.itravel.server.interfaces.dal;

import java.util.List;

import com.itravel.server.interfaces.dal.repos.IDataRepository;

public interface IFilter<T> {
	List<T> doFilter(IDataRepository<T> upcomingEventsRepository);
}
