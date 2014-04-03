package com.itravel.server.interfaces.dal.managers;

import java.util.Date;
import java.util.List;

import com.itravel.server.interfaces.dal.ITravelNote;

public interface ITravelNoteManager extends IDaoManager<ITravelNote> {

	List<ITravelNote> getByTimeRange(Date fromDate, Date toDate);
	
}
