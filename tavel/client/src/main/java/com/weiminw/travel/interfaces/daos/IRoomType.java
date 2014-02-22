package com.weiminw.travel.interfaces.daos;

import java.io.Serializable;

public interface IRoomType extends Serializable{
	public final static IRoomType NULL = new Null();
	
	public static final class Null implements IRoomType {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5494589594680651093L;
		private final long id = -1L;
		private final long hid = -1L;
		private final String name = "";
		private final String bedType = "";
		private final String description = "";
		public Null() {
			// TODO Auto-generated constructor stub
		}
		public long getId() {
			return id;
		}
		public long getHid() {
			return hid;
		}
		public String getName() {
			return name;
		}
		public String getBedType() {
			return bedType;
		}
		
	}
	public long getId() ;
	public long getHid();
	public String getName();
	public String getBedType();
}
