package com.itravel.server.dal.spatial;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.util.Version;

import com.itravel.server.dal.managers.AttractionsManager;
import com.itravel.server.interfaces.dal.EntityType;
import com.itravel.server.interfaces.dal.IAttractions;
import com.itravel.server.interfaces.dal.managers.IAttractionsManager;

public final class AttrationsSpatialManager extends AbstractSpatialManager<IAttractions> {
	public static final AttrationsSpatialManager instance = new AttrationsSpatialManager();
	public AttrationsSpatialManager(){
		
	}

	@Override
	public void addIndex(IAttractions attraction) {
		// TODO Auto-generated method stub
		try {
			IndexWriter indexWriter = new IndexWriter(directory,
					new IndexWriterConfig(Version.LUCENE_46, null));
			indexWriter.addDocument(this.createLntLatPoint(attraction.getId(), attraction.getLatitude(), attraction.getLongitude(),EntityType.attraction));

			indexWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void addIndex(Collection<IAttractions> pois) {
		// TODO Auto-generated method stub
		try {
			IndexWriter indexWriter = new IndexWriter(directory,
					new IndexWriterConfig(Version.LUCENE_46, null));
			for (IAttractions attraction : pois) {
//				if(attraction == null){
//					
//					System.out.println(attraction);
//					continue;
//				}
				indexWriter.addDocument(this.createLntLatPoint(attraction.getId(), attraction.getLatitude(), attraction.getLongitude(),EntityType.attraction));
			}

			indexWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static AttrationsSpatialManager getInstance() {
		// TODO Auto-generated method stub
		
		return instance;
	}

	@Override
	public void deleteIndex(IAttractions poi) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteIndex(Collection<IAttractions> pois) {
		// TODO Auto-generated method stub
		
	}

}
