package com.itravel.server.dal.spatial;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoubleField;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queries.function.ValueSource;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TermQuery;
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
import com.itravel.server.dal.managers.AttractionsManager;
import com.itravel.server.interfaces.dal.EntityType;
import com.itravel.server.interfaces.dal.IAttractions;
import com.itravel.server.interfaces.dal.managers.IAttractionsManager;
import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.shape.Point;

public abstract class AbstractSpatialManager<T> {
	protected static final RAMDirectory directory = new RAMDirectory();
	private static final SpatialPrefixTree grid;
	private static final PrefixTreeStrategy strategy;
	static {
		grid = new GeohashPrefixTree(SpatialContext.GEO, 11);
		strategy = new RecursivePrefixTreeStrategy(grid, "myGeoField");
	}

	public AbstractSpatialManager() {
		// TODO Auto-generated constructor stub
	}

	
	protected Document createLntLatPoint(long id,double latitude,double longitude,EntityType type) {
		Document doc = new Document();
		doc.add(new LongField("id", id, Field.Store.YES));
		doc.add(new StringField("type",String.valueOf(type.ordinal()), Field.Store.YES));
		Point shape = SpatialContext.GEO.makePoint(longitude,
				latitude);
		doc.add(new DoubleField("lnt", shape.getX(), Field.Store.YES));
		doc.add(new DoubleField("lat", shape.getY(), Field.Store.YES));
		for (Field f : strategy.createIndexableFields(shape)) {
			doc.add(f);
		}
		return doc;
	}

	public List<Long> search(EntityType type,double lnt, double lat, int radius) {
		try {
			//创建Query
			Query query = new TermQuery(new Term("type",String.valueOf(type.ordinal())));
			
			//创建fileter
			Point pt = SpatialContext.GEO.makePoint(lnt, lat);
			SpatialArgs args = new SpatialArgs(SpatialOperation.IsWithin,
					SpatialContext.GEO.makeCircle(lnt, lat, DistanceUtils
							.dist2Degrees(radius,
									DistanceUtils.EARTH_MEAN_RADIUS_KM)));
			Filter filter = strategy.makeFilter(args);
			//创建Sort
			double degToM = DistanceUtils.degrees2Dist(1,
					DistanceUtils.EARTH_MEAN_RADIUS_KM);
			ValueSource valueSource = strategy.makeDistanceValueSource(pt);
			IndexReader indexReader = DirectoryReader.open(directory);
			IndexSearcher indexSearcher = new IndexSearcher(indexReader);
			Sort distSort = new Sort(valueSource.getSortField(false))
					.rewrite(indexSearcher);// false=asc dist
			
			TopDocs docs = indexSearcher.search(query,
					filter, 200, distSort);
			List<Long> poiIds = Lists
					.newArrayListWithExpectedSize(docs.totalHits);
			for (ScoreDoc doc : docs.scoreDocs) {
				poiIds.add(indexSearcher.doc(doc.doc).getField("id")
						.numericValue().longValue());
				// double lnt_hotel =
				// indexSearcher.doc(doc.doc).getField("lnt").numericValue().doubleValue();
				// double lat_hotel =
				// indexSearcher.doc(doc.doc).getField("lat").numericValue().doubleValue();
				// Point hotelPoint = SpatialContext.GEO.makePoint(lnt_hotel,
				// lat_hotel);
				// double distance =
				// SpatialContext.GEO.getDistCalc().distance(hotelPoint, lnt,
				// lat);
				// logger.debug(hotelPoint + " distance = "+ distance);
//				System.out.println(indexSearcher.doc(doc.doc));

			}
			return poiIds;
		} catch (IOException e) {
			return ImmutableList.of();
		}

	}
	
	public abstract void addIndex(T poi);
	
	public abstract void addIndex(Collection<T> pois);
	
	public abstract void deleteIndex(T poi);
	
	public abstract void deleteIndex(Collection<T> pois);

//	public static void initIndex(List<IAttractions> attractions) {
//		try {
//			IndexWriter indexWriter = new IndexWriter(directory,
//					new IndexWriterConfig(Version.LUCENE_46, null));
//			for (IAttractions attraction : attractions) {
//				indexWriter.addDocument(createHotelLntLatPoint(attraction));
//			}
//
//			indexWriter.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//	public static void main(String[] args) throws IOException {
//		IAttractionsManager manager = new AttractionsManager();
//		// manager.getAll();
//		Pois.initIndex(manager.getAll());
//		List<Long> ids = Pois.search(116.406887, 39.98207, 5);
//		System.out.println(ids.toString());
//	}

}
