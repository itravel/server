package com.itravel.server.dal.spatial;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.util.Version;

import com.itravel.server.interfaces.dal.IAttractions;

public final class AttrationsSpatialManager extends AbstractSpatialManager<IAttractions> {

	public AttrationsSpatialManager(){
		
	}

	@Override
	public void initIndex(List<IAttractions> pois) {
		// TODO Auto-generated method stub
		try {
			IndexWriter indexWriter = new IndexWriter(directory,
					new IndexWriterConfig(Version.LUCENE_46, null));
			for (IAttractions attraction : pois) {
				indexWriter.addDocument(this.createLntLatPoint(attraction.getId(), attraction.getLatitude(), attraction.getLongitude()));
			}

			indexWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
