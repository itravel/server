package com.itravel.server.client.dos;

import java.util.Date;

public interface IEventObject {
	public long getId() ;
	public void setId(long id) ;

	public Date getGmt_create();

	public void setGmt_create(Date gmt_create) ;

	public Date getGmt_modified();

	public void setGmt_modified(Date gmt_modified);

	public String getTitle();

	public void setTitle(String title) ;

	public String getContent() ;

	public void setContent(String content) ;

	public Date getStartTime() ;

	public void setStartTime(Date startTime) ;

	public Date getEndTime() ;

	public void setEndTime(Date endTime);
}
