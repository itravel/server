package com.itravel.server.services.interfaces;

import com.itravel.server.interfaces.dal.IFilter;

public interface QueryFilterGenerator {
	IFilter createFilterChain();
}
