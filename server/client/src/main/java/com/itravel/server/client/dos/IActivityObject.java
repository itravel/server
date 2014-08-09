package com.itravel.server.client.dos;

public interface IActivityObject extends IEventObject{

	public abstract String getContact();

	public abstract void setContact(String contact);

	public abstract int getConvenience();

	public abstract void setConvenience(int convenience);

	public abstract String getDestination();

	public abstract void setDestination(String destination);

	public abstract int getFee();

	public abstract void setFee(int fee);

	public abstract String getImages();

	public abstract void setImages(String images);

	public abstract int getOriginality();

	public abstract void setOriginality(int originality);

	public abstract int getParticipationType();

	public abstract void setParticipationType(int participationType);

	public abstract int getPopularity();

	public abstract void setPopularity(int popularity);

	public abstract String getRecommender();

	public abstract void setRecommender(String recommender);

	public abstract int getScale();

	public abstract void setScale(int scale);

	public abstract String getSponsor();

	public abstract void setSponsor(String sponsor);

	public abstract String getTags();

	public abstract void setTags(String tags);

	public abstract String getWeb();

	public abstract void setWeb(String web);

	public abstract String getScenerySpot();

	public abstract void setScenerySpot(String scenerySpot);

	public abstract String getDepart();

	public abstract void setDepart(String depart);

}