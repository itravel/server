package com.itravel.server.interfaces.dal.managers;

import java.util.List;

import com.itravel.server.interfaces.dal.IAttractions;

public interface IAttractionsManager extends IManager<IAttractions> {

	boolean batchSave(List<IAttractions> list);
}