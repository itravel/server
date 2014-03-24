package com.itravel.server.dal.asyn;

import java.util.List;
import java.util.concurrent.Callable;

import com.itravel.server.interfaces.dal.IAttractions;
import com.itravel.server.interfaces.dal.managers.IAttractionsManager;

public final class FetchAttractionTask implements Callable<List<IAttractions>> {

	private int offset;
	private int count;
	private IAttractionsManager manager;
	public FetchAttractionTask(int offset,int count,IAttractionsManager manager){
		this.offset = offset;
		this.count = count;
		this.manager = manager;
	}
	@Override
	public List<IAttractions> call() throws Exception {
		// TODO Auto-generated method stub
		
		return this.manager.getRange(offset, count);
	}

}
