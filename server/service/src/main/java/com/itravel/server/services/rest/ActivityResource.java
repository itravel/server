package com.itravel.server.services.rest;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.itravel.server.dal.entities.ActivityEntity;
import com.itravel.server.dal.managers.ActivityManager;
import com.itravel.server.services.aos.ActivityListVO;
import com.itravel.server.services.aos.ActivityListVO.Organizer;
import com.itravel.server.services.json.serializers.ActivityJourneySimpleSerializer;
import com.itravel.server.services.json.serializers.ActivitySimpleSerializer;
import com.itravel.server.services.json.serializers.ActivityTagSimpleSerializer;
import com.itravel.server.services.rest.utils.JsonFactory;

@Path("/activities")
public class ActivityResource {
	@Context
	UriInfo uriInfo;
	protected static final ActivityManager activityManager = new ActivityManager();
	protected static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new SimpleModule().addSerializer(new ActivityJourneySimpleSerializer()).addSerializer(new ActivityTagSimpleSerializer())).setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	protected static final ObjectMapper listObjectMapper = new ObjectMapper().registerModule(new SimpleModule().addSerializer(new ActivitySimpleSerializer())).setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	private static final Logger logger = LogManager.getLogger(ActivityResource.class);
//	private static final LoadingCache<Long,TagEntity> tagCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.HOURS).build(new CacheLoader<Long,TagEntity>(){
//		private final TagManager tagManager = new TagManager();
//		
//		@Override
//		public TagEntity load(Long id) throws Exception {
//			return tagManager.get(id);
//		}});
	
	private static final Function<ActivityEntity,ActivityListVO> TO_LISTVO = new Function<ActivityEntity,ActivityListVO>(){

		@Override
		public ActivityListVO apply(ActivityEntity input) {
			ActivityListVO vo = new ActivityListVO();
			vo.setId(input.getId());
			vo.setTitle(input.getTitle());
			vo.setContent(vo.getContent());
//			vo.setDuration(input.get);
			vo.setFee(input.getFee());
//			vo.setImages();
			vo.setScenerySpot(input.getScenerySpot());
			Organizer organizer = new Organizer();
			organizer.setId(input.getOrganizer().getId());
			organizer.setAvatar(input.getOrganizer().getAvatar());
			organizer.setUserName(input.getOrganizer().getUserName());
			vo.setOrganizer(organizer);
			return vo;
		}
		
	};
	/**
	 * 获取活动信息
	 * @param start
	 * @param number
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response getActivities(@QueryParam(value = "start") int start,@QueryParam(value="number") int number){
		List<ActivityEntity> _activites = activityManager.getActivities(start, number,true);
		List<ActivityListVO> activites = FluentIterable.from(_activites).transform(TO_LISTVO).toList();
		String activityJsonStr="";
		try {
			activityJsonStr = JsonFactory.getMapper().writeValueAsString(activites);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		return Response.ok().entity(activityJsonStr).build();
	}
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response getActivities(@PathParam("id") long id){
		ActivityEntity activity = this.activityManager.getActivity(id);
		String activityJsonStr="";
		try {
			activityJsonStr = objectMapper.writeValueAsString(activity);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		return Response.ok().entity(activityJsonStr).build();
	}
	

	
	
}
