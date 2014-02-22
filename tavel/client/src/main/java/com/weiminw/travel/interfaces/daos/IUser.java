package com.weiminw.travel.interfaces.daos;


public interface IUser {
	public static final IUser NONE = new Null();

	public static final class Null implements IUser {

		@Override
		public long getId() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getCellPhone() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getCloudPushCk() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getCloudPushTag() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getCloudPushUk() {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getNick() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IUser setId(long id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IUser setCellPhone(String cellPhone) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IUser setCloudPushCk(String cloudPushCk) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IUser setCloudPushTag(String cloudPushTag) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IUser setCloudPushUk(String cloudPushUk) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IUser setName(String name) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IUser setPassword(String password) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IUser setNick(String nick) {
			// TODO Auto-generated method stub
			return null;
		}

		
		
	}
	
	public long getId() ;

	public String getCellPhone();

	public String getCloudPushCk();

	public String getCloudPushTag();

	public String getCloudPushUk() ;

	public String getName() ;
	
	public String getPassword();
	
	public String getNick();
	
	public IUser setId(long id);
	
	public IUser setCellPhone(String cellPhone);
	
	public IUser setCloudPushCk(String cloudPushCk);
	
	public IUser setCloudPushTag(String cloudPushTag);
	
	public IUser setCloudPushUk(String cloudPushUk);
	
	public IUser setName(String name);
	
	public IUser setPassword(String password);
	
	public IUser setNick(String nick);
}
