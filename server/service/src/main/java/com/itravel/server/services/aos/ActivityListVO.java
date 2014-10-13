package com.itravel.server.services.aos;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itravel.server.services.rest.utils.JsonFactory;

public class ActivityListVO {
	
	public static class Organizer {
		private long id;
		private String avatar;
		private String userName;
		public Organizer() {
			// TODO Auto-generated constructor stub
			this.avatar = "";
			this.userName = "";
			
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getAvatar() {
			return avatar;
		}
		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		
		
	}
	private long id;
	private String title;
	private String content;
	private List<String> images;
	private String duration;
	private String scenerySpot;
	private int fee;
	private Organizer organizer;
	public ActivityListVO() {
		// TODO Auto-generated constructor stub
		this.title = "";
		this.content = "";
		this.duration = "";
		this.scenerySpot = "";
		this.organizer = new Organizer();
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getScenerySpot() {
		return scenerySpot;
	}
	public void setScenerySpot(String scenerySpot) {
		this.scenerySpot = scenerySpot;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public Organizer getOrganizer() {
		return organizer;
	}
	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivityListVO [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", images=");
		builder.append(images);
		builder.append(", duration=");
		builder.append(duration);
		builder.append(", scenerySpot=");
		builder.append(scenerySpot);
		builder.append(", fee=");
		builder.append(fee);
		builder.append(", organizer=");
		builder.append(organizer);
		builder.append("]");
		return builder.toString();
	}
	
	public static void main(String[] args) throws JsonProcessingException {
		ActivityListVO vo = new ActivityListVO();
		System.out.println(JsonFactory.getMapper().writeValueAsString(vo));
		
	}
	
}
