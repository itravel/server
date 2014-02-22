package com.weiminw.business.spatial;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoubleField;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queries.function.ValueSource;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.spatial.prefix.PrefixTreeStrategy;
import org.apache.lucene.spatial.prefix.RecursivePrefixTreeStrategy;
import org.apache.lucene.spatial.prefix.tree.GeohashPrefixTree;
import org.apache.lucene.spatial.prefix.tree.SpatialPrefixTree;
import org.apache.lucene.spatial.query.SpatialArgs;
import org.apache.lucene.spatial.query.SpatialOperation;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.shape.Point;
import com.weiminw.business.managers.HotelManager;
import com.weiminw.travel.interfaces.daos.IHotelLocation;
import com.weiminw.travel.interfaces.managers.IHotelManager;


public class HotelSpatial {
	private static final Logger logger = LogManager.getLogger(HotelSpatial.class);
	/**
	 * 索引内存
	 */
	private static final RAMDirectory directory = new RAMDirectory();
	private static final SpatialPrefixTree grid;
	private static final PrefixTreeStrategy strategy;
	private static final int MAX_TOP = 3000;
	static {
		grid = new GeohashPrefixTree(SpatialContext.GEO, 11);
		strategy = new RecursivePrefixTreeStrategy(grid, "myGeoField");
	}
	
	private static Document createHotelLntLatPoint(IHotelLocation hotel) {
	    Document doc = new Document();
	    doc.add(new LongField("id", hotel.getId(), Field.Store.YES));
	    Point shape = SpatialContext.GEO.makePoint(hotel.getLongitude(), hotel.getLatitude());
	    for (Field f : strategy.createIndexableFields(shape)) {
	    	doc.add(f);
	    	doc.add(new DoubleField("lnt",  shape.getX(),Field.Store.YES));
	    	doc.add(new DoubleField("lat",  shape.getY(),Field.Store.YES));
	    }
	    return doc;
	  }

	
	public static List<Long> search(double lnt,double lat,int radius){
		try {
			Point pt = SpatialContext.GEO.makePoint(lnt, lat);
			SpatialArgs args = new SpatialArgs(SpatialOperation.IsWithin, SpatialContext.GEO.makeCircle(lnt, lat, DistanceUtils.dist2Degrees(radius, DistanceUtils.EARTH_MEAN_RADIUS_KM)));
			Filter filter = strategy.makeFilter(args);
			
			double degToM = DistanceUtils.degrees2Dist(1, DistanceUtils.EARTH_MEAN_RADIUS_KM);	
		    ValueSource valueSource = strategy.makeDistanceValueSource(pt);//the distance (in m)
			IndexReader indexReader = DirectoryReader.open(directory);
			IndexSearcher indexSearcher = new IndexSearcher(indexReader);
			Sort distSort = new Sort(valueSource.getSortField(false)).rewrite(indexSearcher);//false=asc dist

			TopDocs docs = indexSearcher.search(new MatchAllDocsQuery(), filter, MAX_TOP, distSort);
			List<Long> hotelIds = Lists.newArrayListWithExpectedSize(docs.totalHits);
			for(ScoreDoc doc:docs.scoreDocs){
				hotelIds.add(indexSearcher.doc(doc.doc).getField("id").numericValue().longValue());
				double lnt_hotel =  indexSearcher.doc(doc.doc).getField("lnt").numericValue().doubleValue();
				double lat_hotel =  indexSearcher.doc(doc.doc).getField("lat").numericValue().doubleValue();
				Point hotelPoint = SpatialContext.GEO.makePoint(lnt_hotel, lat_hotel);
				double distance = SpatialContext.GEO.getDistCalc().distance(hotelPoint, lnt, lat);
				logger.debug(hotelPoint + " distance = "+ distance);
				
			}
			return hotelIds;
		}
		catch(IOException e){
			return ImmutableList.of();
		}
		
	}
	
	public static void main(String[] args) throws IOException {
//		HotelSpatial.initIndex();
		List<Long> ids = HotelSpatial.search(116.406887,39.98207, 5);
		System.out.println(ids.toString());
	}
	
	/**
	 * 初始化索引
	 */
	public static void initIndex(List<IHotelLocation> hotelLocations){
		try {
	    	IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_46,null));
	    	for(IHotelLocation hotel:hotelLocations){
	    		indexWriter.addDocument(createHotelLntLatPoint(hotel));
	    	}

			indexWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	public static void buildIndex(List<IHotel> hotels) {
//		try {
//	    	IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_46,null));
//	    	for(IHotel hotel:hotels){
//	    		indexWriter.addDocument(createHotelLntLatPoint(hotel));
//	    	}
//
//			indexWriter.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
}
